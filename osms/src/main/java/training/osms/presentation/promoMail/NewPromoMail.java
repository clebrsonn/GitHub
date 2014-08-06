package training.osms.presentation.promoMail;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.PromoMail.PromoController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewPromoMail {

	private @Autowired
	PromoController controller;
	private PromoForm form;

	public NewPromoMail() {
		reset();
	}

	public void reset() {
		form = new PromoForm();
	}

	public PromoController getController() {
		return controller;
	}

	public void setController(PromoController controller) {
		this.controller = controller;
	}

	public PromoForm getForm() {
		return form;
	}

	public void setForm(PromoForm form) {
		this.form = form;
	}

	public void savePromo() {
		FacesMessage message = new FacesMessage();
		try {
			controller.savePromo(form.getPromoMail());

			message.setSummary("PromoMail was successfully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			reset();

		} catch (BusinessException e) {

			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
}
