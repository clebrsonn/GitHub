package training.osms.presentation.frete;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.frete.FreController;
import training.osms.business.frete.FreSearchOptions;
import training.osms.business.frete.Frete;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchFrete {

	private static final int RESULTS_PER_PAGE = 10;

	private @Autowired
	FreController controller;
	private FreSearchOptions options;
	private FreteForm form;
	private List<Frete> result;
	private boolean isDeleted;
	private List<Integer> pages;
	private int page;

	public SearchFrete() {
		reset();
	}

	public void reset() {
		options = new FreSearchOptions();
		result = null;
		page = 0;
		pages = null;
	}

	public FreSearchOptions getOptions() {
		return options;
	}

	public void setOptions(FreSearchOptions options) {
		this.options = options;
	}

	public FreteForm getForm() {
		return form;
	}

	public void setForm(FreteForm form) {
		this.form = form;
	}

	public List<Frete> getResult() {
		return result;
	}

	public void setResult(List<Frete> result) {
		this.result = result;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void search() {
		int resultCount = controller.searchFreteCount(options);
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

		FacesMessage message = new FacesMessage();

		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);
		result = controller.searchFrete(options);
		if (result.isEmpty()) {
				message.setSummary("A sua pesquisa"
						+ " não retornou nenhum resultado");
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
		}

	public String viewFrete(Integer freteId) {
		options.setFreteId(freteId);
		List<Frete> pedAux = controller.searchFrete(options);
		if (pedAux.size() > 0) {
			this.form = new FreteForm();
			this.form.setFrete(pedAux.get(0));
			reset();
		}
		return "seeOrder";
	}

	public String getPageClass(int page) {
		if (page == this.page) {
			return "active";
		} else {
			return "";
		}
	}

	public void confirmUpdateFrete() {
		FacesMessage message = new FacesMessage();
		try {

			controller.updateFrete(form.getFrete());
			message.setSummary("Frete was updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();

	}

	public String viewDeletedFrete(Frete frete) {
		this.form = new FreteForm();
		this.form.setFrete(frete);
		this.isDeleted = false;
		return "deleteFrete";
	}

	public void deleteFrete() {
		controller.deleteFrete(form.getFrete());
		this.isDeleted = true;
		FacesMessage message = new FacesMessage();
		message.setSummary("Frete was successfully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();
	}

}
