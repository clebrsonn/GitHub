package training.osms.presentation.product;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.product.ProdController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewProduct {

	private @Autowired
	ProdController controller;
	private ProdForm form;

	public NewProduct() {
		reset();
	}

	public void reset() {
		form = new ProdForm();
	}

	public void setForm(ProdForm form) {
		this.form = form;
	}

	public ProdForm getForm() {
		return form;
	}

	public void save() {
		String clientId;
		FacesMessage message = new FacesMessage();
		try {
			form.getProduct().setVisible(true);
			controller.saveProd(form.getProduct());
			clientId = null;
			message.setSummary("Product was successfully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			reset();

		} catch (BusinessException e) {
			clientId = "form:prod:name";
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(clientId, message);
		
	}

}
