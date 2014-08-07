package training.osms.presentation;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.AvaController;
import training.osms.business.AvaSearchOptions;
import training.osms.business.Avaliacao;
import training.osms.business.PedController;
import training.osms.business.PedSearchOptions;
import training.osms.business.Pedido;
import training.osms.business.ProdController;
import training.osms.business.ProdSearchOptions;
import training.osms.business.Product;
import training.osms.business.User;

public class UserForm {

	private User user;
	private List<Product> products;
	private List<Pedido> pedidos;

	private List<Avaliacao> avaliacao;

	public UserForm() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		ProdController prodController = applicationContext
				.getBean(ProdController.class);
		products = prodController.searchProd(new ProdSearchOptions());

		PedController pedController = applicationContext
				.getBean(PedController.class);
		pedidos = pedController.searchPedido(new PedSearchOptions());

		AvaController avaController = applicationContext
				.getBean(AvaController.class);
		avaliacao = avaController.searchAvaliacao(new AvaSearchOptions());

		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

}
