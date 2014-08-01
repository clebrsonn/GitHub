package training.osms.presentation.avaliacao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.avaliacao.AvaController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewAvaliacao {

	private @Autowired
	AvaController controller;
	private AvaForm form;

	public NewAvaliacao() {
		reset();
	}

	public void reset() {
		form = new AvaForm();
	}

	public AvaController getController() {
		return controller;
	}

	public void setController(AvaController controller) {
		this.controller = controller;
	}

	public AvaForm getForm() {
		return form;
	}

	public void setForm(AvaForm form) {
		this.form = form;
	}

	public void saveAva() {
		FacesMessage message = new FacesMessage();
		try {
			controller.saveAvaliacao(form.getAvaliacao());

			message.setSummary("Avaliacao was successfully saved");
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
