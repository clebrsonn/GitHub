package training.osms.presentation.user;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.user.UserController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewUser {

	private @Autowired
	UserController controller;
	private UserForm form;

	public NewUser() {
		form = new UserForm();
	}

	public UserForm getForm() {
		return form;
	}

	public void setForm(UserForm form) {
		this.form = form;
	}

	public void saveUser() {
		String clientId;
		FacesMessage message = new FacesMessage();
		try {
			controller.save(form.getUser());
			clientId = null;
			message.setSummary("User was successfully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (BusinessException e) {
			clientId = "form:user:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(clientId, message);
	}

}
