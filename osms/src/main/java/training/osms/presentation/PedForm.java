package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.pedido.Pedido;
import training.osms.business.product.ProdController;
import training.osms.business.product.ProdSearchOptions;
import training.osms.business.product.Product;

public class PedForm {

	private Pedido pedido;
	private List<Product> products;
	private double total;

	public PedForm() {
		pedido = new Pedido();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils
				.getWebApplicationContext(facesContext);

		ProdController controller = applicationContext
				.getBean(ProdController.class);
		products = controller.searchProd(new ProdSearchOptions());
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

	public void setProductId(Integer prodId) {
		if (prodId != null) {
			for (Product product : products) {
				System.out.println(pedido.getProducts());
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

	public List<String> getPemove() {
		List<String> prodIds = new ArrayList<>();
		for (Product product : pedido.getProducts()) {
			prodIds.add(product.getId().toString());
		}
		return prodIds;
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

}
