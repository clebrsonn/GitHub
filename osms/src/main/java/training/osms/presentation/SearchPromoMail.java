package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.PromoController;
import training.osms.business.PromoMail;
import training.osms.business.PromoSearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchPromoMail {

	private static final int RESULTS_PER_PAGE = 10;

	private @Autowired
	PromoController controller;
	private PromoSearchOptions options;
	private PromoForm form;
	private List<PromoMail> result;
	private boolean isDeleted;
	private List<Integer> pages;
	private int page;

	public SearchPromoMail() {
		reset();
	}

	public void reset() {
		options = new PromoSearchOptions();
		result = null;
		page = 0;
		pages = null;
	}

	public PromoSearchOptions getOptions() {
		return options;
	}

	public void setOptions(PromoSearchOptions options) {
		this.options = options;
	}

	public PromoForm getForm() {
		return form;
	}

	public void setForm(PromoForm form) {
		this.form = form;
	}

	public List<PromoMail> getResult() {
		return result;
	}

	public void setResult(List<PromoMail> result) {
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
		int resultCount = controller.searchPromoCount(options);
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
		result = controller.searchPromo(options);
		if (options.getDateIni() != null && options.getDateFim() != null
				&& options.getDateIni().after(options.getDateFim())) {
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

	public String viewPromoMail(Integer promoId) {
		options.setId(promoId);
		List<PromoMail> pedAux = controller.searchPromo(options);
		if (pedAux.size() > 0) {
			this.form = new PromoForm();
			this.form.setPromoMail(pedAux.get(0));
			reset();
		}
		return "seePromo";
	}

	public String getPageClass(int page) {
		if (page == this.page) {
			return "active";
		} else {
			return "";
		}
	}

	public void confirmUpdatePromoMail() {
		FacesMessage message = new FacesMessage();
		try {

			controller.updatePromo(form.getPromoMail());
			message.setSummary("PromoMail was updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();

	}

	public String viewDeletedPromoMail(PromoMail promoMail) {
		this.form = new PromoForm();
		this.form.setPromoMail(promoMail);
		this.isDeleted = false;
		return "deletePromoMail";
	}

	public void deletePromoMail() {
		controller.deletePromo(form.getPromoMail());
		this.isDeleted = true;
		FacesMessage message = new FacesMessage();
		message.setSummary("PromoMail was successfully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();
	}

}
