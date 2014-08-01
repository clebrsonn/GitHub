package training.osms.presentation.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.product.ProdController;
import training.osms.business.product.ProdSearchOptions;
import training.osms.business.product.Product;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowProducts {

	private static final int RESULTS_PER_PAGE = 9;

	private @Autowired
	ProdController controller;
	private Product product;
	private List<Product> products;
	private List<Integer> pages;
	private int page;
	private ProdSearchOptions options;
	private int prodId;
	private int catId;
	//private List<Avaliacao> avaliacoes;

	public ShowProducts() {
		products = null;
		//avaliacoes = new ;
		reset();
	}

	private void reset() {

		options = new ProdSearchOptions();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product prod = (Product) iterator.next();
			if (prod.getVisible() == false) {
				iterator.remove();

			}
		}
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setOptions(ProdSearchOptions options) {
		this.options = options;
	}

	public ProdSearchOptions getOptions() {
		return options;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void listProd() {
		int resultCount = controller.searchProdCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;

		if (resultCount % RESULTS_PER_PAGE > 0) {
			++pageCount;
		}
		pages = new ArrayList<Integer>();
		for (int page = 1; page <= pageCount; ++page) {
			pages.add(page);
		}
		goToPage(1);

	}

	public void goToPage(int page) {
		this.page = page;

		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);
		products = controller.searchProd(options);
	}

	public String getPageClass(int page) {
		if (page == this.page) {
			return "active";
		} else {
			return "";
		}
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;

		options.setProdId(prodId);
		List<Product> prods = controller.searchProd(options);
		if (prods.size() > 0) {
			product = prods.get(0);
		}

	}

	public int getProdId() {
		reset();
		return prodId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
		options.setCatId(catId);
		List<Product> prods = controller.searchProd(options);
		if (prods.size() > 0) {
			products = prods;
		}
	}

	public int getCatId() {
		reset();
		return catId;
	}

//	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
//		this.avaliacoes = avaliacoes;
//	}
//
//	public List<Avaliacao> getAvaliacoes() {
//		AvaController controllerAva = new AvaController();
//		AvaSearchOptions optionsAva = new AvaSearchOptions();
//		optionsAva.setProdId(prodId);
//		avaliacoes = controllerAva.searchAvaliacao(optionsAva);
//
//		return avaliacoes;
//	}

	public String getProdBody() {
		String escapedBody = StringEscapeUtils.escapeHtml(product
				.getDescription());

		StringBuilder body = new StringBuilder();
		body.append("<p>");
		escapedBody = escapedBody
				.replaceAll("[(\\n\\r)(\\n)(\\r)]+", "</p><p>");
		body.append(escapedBody);
		body.append("</p>");
		return body.toString();
	}

}
