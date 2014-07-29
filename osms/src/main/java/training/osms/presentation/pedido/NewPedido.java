package training.osms.presentation.pedido;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.framework.business.BusinessException;
import training.osms.business.pedido.PedController;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class NewPedido {

	private @Autowired
	PedController controller;
	private PedForm form;

	public NewPedido() {
		reset();
	}

	public void reset() {
		form = new PedForm();
	}

	public void setForm(PedForm form) {
		this.form = form;
	}

	public PedForm getForm() {
		return form;
	}

	public void save() {
		FacesMessage message = new FacesMessage();
		try {
			form.getPedido().setDateBuy(new Date());
			controller.savePedido(form.getPedido());
			message.setSummary("Pedido was successfully saved");
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
