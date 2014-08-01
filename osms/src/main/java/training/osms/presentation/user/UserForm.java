package training.osms.presentation.user;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.avaliacao.AvaController;
import training.osms.business.avaliacao.AvaSearchOptions;
import training.osms.business.avaliacao.Avaliacao;
import training.osms.business.pedido.PedController;
import training.osms.business.pedido.PedSearchOptions;
import training.osms.business.pedido.Pedido;
import training.osms.business.product.ProdController;
import training.osms.business.product.ProdSearchOptions;
import training.osms.business.product.Product;
import training.osms.business.user.User;

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
