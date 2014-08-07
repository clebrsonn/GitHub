package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.FreController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewFrete {

	private @Autowired
	FreController controller;
	private FreteForm form;

	public NewFrete() {
		reset();
	}

	public void reset() {
		form = new FreteForm();
	}

	public FreController getController() {
		return controller;
	}

	public void setController(FreController controller) {
		this.controller = controller;
	}

	public FreteForm getForm() {
		return form;

	}

	public void setForm(FreteForm form) {
		this.form = form;
	}

	public void saveFrete() {
		FacesMessage message = new FacesMessage();
		try {
			controller.saveFrete(form.getFrete());

			message.setSummary("Frete was successfully saved");
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
