package training.osms.presentation.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.product.ProdController;
import training.osms.business.product.ProdSearchOptions;
import training.osms.business.product.Product;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchProd {

	private static final int RESULTS_PER_PAGE = 10;

	private @Autowired
	ProdController controller;
	private ProdSearchOptions options;
	private ProdForm form;
	private List<Product> result;
	private boolean deleted;
	private List<Integer> pages;
	private int page;

	public SearchProd() {
		reset();
	}

	public void reset() {
		options = new ProdSearchOptions();
		result = null;
		page = 0;
		pages = null;
	}

	public ProdSearchOptions getOptions() {
		return options;
	}

	public void setOptions(ProdSearchOptions options) {
		this.options = options;
	}

	public ProdForm getForm() {
		return form;
	}

	public void setForm(ProdForm form) {
		this.form = form;
	}

	public List<Product> getResult() {
		return result;
	}

	public void setResult(List<Product> result) {
		this.result = result;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void search() {
		int resultCount = controller.searchProdCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;

		if (resultCount % RESULTS_PER_PAGE > 0) {
			++pageCount;
		}
		pages = new ArrayList<Integer>();
		for (int page = 1; page <= pageCount; ++page) {
			pages.add(page);
		}
		goToPage(1);

	}

	public void goToPage(int page) {
		this.page = page;

		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);
		result = controller.searchProd(options);
		if (result.isEmpty()) {
			FacesMessage message = new FacesMessage();
			message.setSummary("A sua pesquisa não retornou nenhum resultado");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		} else {
			for (Iterator<Product> iterator = result.iterator(); iterator
					.hasNext();) {
				Product prod = (Product) iterator.next();
				if (prod.getVisible() == false) {
					iterator.remove();

				}
			}
		}
	}

	public String getPageClass(int page) {
		if (page == this.page) {
			return "active";
		} else {
			return "";
		}
	}

	public String viewUpdateProduct(Product product) {
		Product prodAux = product.clone();
		this.form = new ProdForm();
		this.form.setProduct(prodAux);
		return "updateProduct";
	}

	public void confirmUpdateProduct() {
		FacesMessage message = new FacesMessage();
		try {

			controller.updateProd(form.getProduct());
			message.setSummary("Product was updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();

	}

	public String viewDeletedProduct(Product product) {
		this.form = new ProdForm();
		this.form.setProduct(product);
		this.deleted = false;
		return "deleteProduct";
	}

	public void deleteProduct() {
		ProdSearchOptions options = new ProdSearchOptions();
		options.setProdId(form.getProduct().getId());
		form.setProduct(controller.searchProd(options).get(0));

		form.getProduct().setVisible(false);
		controller.deleteProd(form.getProduct());
		this.deleted = true;
		
		FacesMessage message = new FacesMessage();
		
		message.setSummary("Product was successfully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();
	}

}
