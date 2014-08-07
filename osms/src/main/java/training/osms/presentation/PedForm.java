package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.FreController;
import training.osms.business.FreSearchOptions;
import training.osms.business.Frete;
import training.osms.business.Pedido;
import training.osms.business.ProdController;
import training.osms.business.ProdSearchOptions;
import training.osms.business.Product;
import training.osms.business.User;
import training.osms.business.UserController;
import training.osms.business.UserSearchOptions;

public class PedForm {

	private Pedido pedido;
	private List<Product> products;
	private double total;
	private List<User> users;

	 private List<Frete> fretes;

	public PedForm() {
		pedido = new Pedido();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		UserController userController = applicationContext
				.getBean(UserController.class);

		users = userController.searchUser(new UserSearchOptions());

		ProdController controller = applicationContext
				.getBean(ProdController.class);
		products = controller.searchProd(new ProdSearchOptions());

		 FreController freteController = applicationContext
		 .getBean(FreController.class);
		 fretes = freteController.searchFrete(new FreSearchOptions());

	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;

	}

	 public void setFretes(List<Frete> fretes) {
	 this.fretes = fretes;
	 }
	
	 public List<Frete> getFretes() {
	 return fretes;
	 }

	public void setProductId(Integer prodId) {
		if (prodId != null) {
			for (Product product : products) {
				// System.out.println(pedido.getProducts());
				if (product.getId().equals(prodId)) {
					pedido.getProducts().add(product);
				}
			}
		}
	}

	public List<String> getProductId() {
		List<String> prodIds = new ArrayList<>();
		for (Product product : pedido.getProducts()) {
			prodIds.add(product.getId().toString());
		}
		return prodIds;
	}

	public void setRemove(Integer prodId) {
		if (prodId != null) {
			for (Product product : products) {
				if (product.getId().equals(prodId)) {
					pedido.getProducts().remove(product);
				}
			}
		}
	}

	public List<String> getRemove() {
		List<String> prodIds = new ArrayList<>();
		for (Product product : pedido.getProducts()) {
			prodIds.add(product.getId().toString());
		}
		return prodIds;
	}

	public Integer getTotalItens() {
		return pedido.getProducts().size();
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		total = 0;
		for (Product product : pedido.getProducts()) {
			total = total + product.getPrice();
		}
		return total;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		// System.out.println(users);
		return users;
	}

	public void setUserId(Integer userId) {
		if (userId == null) {
			pedido.setUser(null);

		} else {
			for (User user : users) {
				if (user.getId().equals(userId)) {
					pedido.setUser(user);
					break;
				}
			}
		}
	}

	public Integer getUserId() {
		User user = pedido.getUser();
		if (user == null) {
			return null;
		} else {
			return user.getId();
		}
	}
	
	public void setFreteId(Integer freteId) {
		if (freteId == null) {
			pedido.setFrete(null);
		} else {
			for (Frete frete : fretes) {
				if (frete.getId().equals(freteId)) {
					pedido.setFrete(frete);
					break;
				}
			}
		}
	}

	public Integer getFreteId() {
		Frete frete = pedido.getFrete();
		if (frete == null) {
			return null;
		} else {
			return frete.getId();
		}
	}
	
	
}
