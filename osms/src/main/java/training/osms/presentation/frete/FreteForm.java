package training.osms.presentation.frete;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.frete.Frete;
import training.osms.business.pedido.PedController;
import training.osms.business.pedido.PedSearchOptions;
import training.osms.business.pedido.Pedido;

public class FreteForm {

	private Frete frete;
	private List<Pedido> pedidos;

	public FreteForm() {
		frete = new Frete();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		PedController pedController = applicationContext
				.getBean(PedController.class);

		pedidos = pedController.searchPedido(new PedSearchOptions());

	}

	public void setFrete(Frete frete) {
		this.frete = frete;
	}

	public Frete getFrete() {
		return frete;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

}
