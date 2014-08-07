package training.osms.presentation;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.Frete;
import training.osms.business.PedController;
import training.osms.business.PedSearchOptions;
import training.osms.business.Pedido;

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

	public Integer getTotalItens(Integer pedId) {
		Integer totalItens = 0;
		for (Pedido pedido : pedidos) {
			if (pedido.getId().equals(pedId)) {
				totalItens = pedido.getProducts().size();
			}
		}

		return totalItens;
	}

}
