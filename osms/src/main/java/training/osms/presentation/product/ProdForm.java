package training.osms.presentation.product;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.avaliacao.AvaController;
import training.osms.business.avaliacao.AvaSearchOptions;
import training.osms.business.avaliacao.Avaliacao;
import training.osms.business.category.CatController;
import training.osms.business.category.CatSearchOptions;
import training.osms.business.category.Category;
import training.osms.business.pedido.PedController;
import training.osms.business.pedido.PedSearchOptions;
import training.osms.business.pedido.Pedido;
import training.osms.business.product.Product;
import training.osms.business.user.User;
import training.osms.business.user.UserController;
import training.osms.business.user.UserSearchOptions;

public class ProdForm {

	private Product product;
	private List<Category> categories;
	private List<Pedido> pedidos;
	private List<User> users;

	private List<Avaliacao> avaliacao;
//	private List<PromoMail> promoMails;

	public ProdForm() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		CatController controller = applicationContext
				.getBean(CatController.class);
		categories = controller.searchCats(new CatSearchOptions());

		PedController pedController = applicationContext
				.getBean(PedController.class);
		pedidos = pedController.searchPedido(new PedSearchOptions());

		AvaController avaController = applicationContext
				.getBean(AvaController.class);
		avaliacao = avaController.searchAvaliacao(new AvaSearchOptions());

		UserController userController = applicationContext
				.getBean(UserController.class);

		users = userController.searchUser(new UserSearchOptions());

		product = new Product();

	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

//	public void setPromoMails(List<PromoMail> promoMails) {
//		this.promoMails = promoMails;
//	}
//
//	public List<PromoMail> getPromoMails() {
//		return promoMails;
//	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setCatId(Integer catId) {
		if (catId == null) {
			product.setCategory(null);
		} else {
			for (Category category : categories) {
				if (category.getId().equals(catId)) {
					product.setCategory(category);
					break;
				}
			}
		}

	}

	public Integer getCatId() {
		Category category = product.getCategory();
		if (category == null) {
			return null;
		} else {
			return category.getId();
		}
	}

	public void setUserId(Integer userId) {
		if (userId == null) {
			product.setUser(null);

		} else {
			for (User user : users) {
				if (user.getId().equals(userId)) {
					product.setUser(user);
					break;
				}
			}
		}
	}

	public Integer getUserId() {
		User user = product.getUser();
		if (user == null) {
			return null;
		} else {
			return user.getId();
		}
	}
}
