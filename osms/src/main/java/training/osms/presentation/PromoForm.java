package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.ProdController;
import training.osms.business.ProdSearchOptions;
import training.osms.business.Product;
import training.osms.business.PromoMail;
import training.osms.business.User;
import training.osms.business.UserController;
import training.osms.business.UserSearchOptions;

public class PromoForm {

	private PromoMail promoMail;
	private List<User> users;
	private List<Product> products;

	public PromoForm() {
		promoMail = new PromoMail();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		UserController userController = applicationContext
				.getBean(UserController.class);

		users = userController.searchUser(new UserSearchOptions());

		ProdController prodController = applicationContext
				.getBean(ProdController.class);

		products = prodController.searchProd(new ProdSearchOptions());

	}

	public void setPromoMail(PromoMail promoMail) {
		this.promoMail = promoMail;
	}

	public PromoMail getPromoMail() {
		return promoMail;
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

	public void setUserIds(List<String> userIds) {
		promoMail.getUserMail().clear();
		for (String userId : userIds) {
			for (User user : users) {
				if (user.getId().toString().equals(userId)) {
					promoMail.getUserMail().add(user);
				}
			}
		}System.out.println(promoMail.getUserMail());
	}

	public List<String> getUserIds() {
		List<String> userIds = new ArrayList<>();
		for (User user : promoMail.getUserMail()) {
			userIds.add(user.getId().toString());
		}
		return userIds;
	}	
	
	public void setProductIds(List<String> productIds) {
		promoMail.getProductMail().clear();
		for (String productId : productIds) {
			for (Product product : products) {
				if (product.getId().toString().equals(productId)) {
					promoMail.getProductMail().add(product);
				}
			}
		}System.out.println(promoMail.getProductMail());
	}

	public List<String> getProductIds() {
		List<String> productIds = new ArrayList<>();
		for (Product product : promoMail.getProductMail()) {
			productIds.add(product.getId().toString());
		}
		return productIds;
	}
	
}
