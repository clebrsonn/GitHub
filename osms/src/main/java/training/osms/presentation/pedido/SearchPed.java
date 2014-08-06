package training.osms.presentation.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.pedido.PedController;
import training.osms.business.pedido.PedSearchOptions;
import training.osms.business.pedido.Pedido;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchPed {

	private static final int RESULTS_PER_PAGE = 10;

	private @Autowired
	PedController controller;
	private PedSearchOptions options;
	private PedForm form;
	private List<Pedido> result;
	private boolean isDeleted;
	private List<Integer> pages;
	private int page;

	public SearchPed() {
		reset();
	}

	public void reset() {
		options = new PedSearchOptions();
		result = null;
		page = 0;
		pages = null;
	}

	public PedSearchOptions getOptions() {
		return options;
	}

	public void setOptions(PedSearchOptions options) {
		this.options = options;
	}

	public PedForm getForm() {
		return form;
	}

	public void setForm(PedForm form) {
		this.form = form;
	}

	public List<Pedido> getResult() {
		return result;
	}

	public void setResult(List<Pedido> result) {
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
		int resultCount = controller.searchPedidoCount(options);
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
		result = controller.searchPedido(options);

		if (options.getDate() != null && options.getDateFim() != null
				&& options.getDate().after(options.getDateFim())) {
			message.setSummary("Período inválido");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} else {

			if (result.isEmpty()) {

				message.setSummary("A sua pesquisa"
						+ " não retornou nenhum resultado");
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, message);
			}
		}
	}

	public String viewPedido(Integer pedId) {
		options.setPedidoId(pedId);
		List<Pedido> pedAux = controller.searchPedido(options);
		if (pedAux.size() > 0) {
			this.form = new PedForm();
			this.form.setPedido(pedAux.get(0));
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

	// public void confirmUpdatePedido() {
	// FacesMessage message = new FacesMessage();
	// try {
	//
	// controller.updatePedido(form.getPedido());
	// message.setSummary("Pedido was updated");
	// message.setSeverity(FacesMessage.SEVERITY_INFO);
	//
	// } catch (BusinessException e) {
	// message.setSummary(e.getMessage());
	// message.setSeverity(FacesMessage.SEVERITY_ERROR);
	//
	// }
	// FacesContext context = FacesContext.getCurrentInstance();
	// context.addMessage(null, message);
	// reset();
	//
	// }
	//
	// public String viewDeletedPedido(Pedido product) {
	// this.form = new PedForm();
	// this.form.setPedido(product);
	// this.isDeleted = false;
	// return "deletePedido";
	// }
	//
	// public void deletePedido() {
	// controller.deletePedido(form.getPedido());
	// this.isDeleted = true;
	// FacesMessage message = new FacesMessage();
	// message.setSummary("Pedido was successfully deleted");
	// message.setSeverity(FacesMessage.SEVERITY_INFO);
	//
	// FacesContext context = FacesContext.getCurrentInstance();
	// context.addMessage(null, message);
	// reset();
	// }

}
