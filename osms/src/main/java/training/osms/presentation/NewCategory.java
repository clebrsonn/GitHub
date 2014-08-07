package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.CatController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewCategory {

	private @Autowired
	CatController controller;
	private CatForm form;

	public NewCategory() {
		reset();
	}

	public void reset() {
		form = new CatForm();
	}

	public void setCategory(CatForm form) {
		this.form = form;
	}

	public CatForm getForm() {
		return form;
	}

	public void saveCat() {
		String clientId;
		FacesMessage message = new FacesMessage();
		try {
			controller.save(form.getCategory());
			clientId = null;
			message.setSummary("Category was successfully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			clientId = "form:cat:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(clientId, message);
		reset();
	}

}
