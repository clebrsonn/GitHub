package training.osms.presentation;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.CatController;
import training.osms.business.CatSearchOptions;
import training.osms.business.Category;
import training.osms.business.PedController;
import training.osms.business.PedSearchOptions;
import training.osms.business.Pedido;
import training.osms.business.Product;

public class ProdForm {

	private Product product;
	private List<Category> categories;
	private List<Pedido> pedidos;

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



}
