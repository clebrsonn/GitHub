package training.osms.presentation;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.Avaliacao;
import training.osms.business.ProdController;
import training.osms.business.ProdSearchOptions;
import training.osms.business.Product;
import training.osms.business.User;
import training.osms.business.UserController;
import training.osms.business.UserSearchOptions;

public class AvaForm {

	private Avaliacao avaliacao;
	private List<User> users;
	private List<Product> products;

	public AvaForm() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		UserController userController = applicationContext
				.getBean(UserController.class);

		users = userController.searchUser(new UserSearchOptions());

		ProdController prodController = applicationContext
				.getBean(ProdController.class);

		products = prodController.searchProd(new ProdSearchOptions());

		avaliacao = new Avaliacao();
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setUserId(Integer userId) {
		if (userId == null) {
			avaliacao.setUser(null);

		} else {
			for (User user : users) {
				if (user.getId().equals(userId)) {
					avaliacao.setUser(user);
					break;
				}
			}
		}
	}

	public Integer getUserId() {
		User user = avaliacao.getUser();
		if (user == null) {
			return null;
		} else {
			return user.getId();
		}
	}

	public void setProdId(Integer prodId) {
		if (prodId == null) {
			avaliacao.setProduct(null);
		} else {
			for (Product product : products) {
				if (product.getId().equals(prodId)) {
					avaliacao.setProduct(product);
					break;
				}

			}
		}
	}

	public Integer getProdId() {
		Product product = avaliacao.getProduct();
		if (product == null) {
			return null;
		} else {
			return product.getId();
		}
	}
}
