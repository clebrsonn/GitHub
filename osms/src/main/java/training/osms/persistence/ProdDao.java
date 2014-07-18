package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.product.ProdSearchOptions;
import training.osms.business.product.Product;

@Component
public class ProdDao extends EntityDao<Product, ProdSearchOptions> {

	public ProdDao() {
		super(Product.class, "product");
	}

	@Override
	protected StringBuilder toPredicate(ProdSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getCatId() != null) {
			predicate.append(" and product.category.id = :catId");
		}

		if (options.getUseId() != null) {
			predicate.append(" and product.user.id = :userId");
		}

		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and upper(product.name) like :productName");

		}
		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			predicate
					.append(" and upper(product.description) like :productDescription");
		}
		if (options.getPrice() > 0) {
			predicate.append(" and product.price <= :productPrice");
		}

		if (options.getPriceIni() > 0) {
			predicate.append(" and product.price >= :productPriceIni");
		}
		if (options.getProdId() != null && options.getProdId() > 0) {
			predicate.append(" and product.id = :productId");
		}
		return predicate;
	}

	@Override
	protected void setParameters(ProdSearchOptions options, TypedQuery<?> query) {
		if (options.getCatId() != null) {
			query.setParameter("catId", options.getCatId());
		}

		if (options.getUseId() != null) {
			query.setParameter("userId", options.getUseId());
		}

		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("productName", "%"
					+ options.getName().toUpperCase() + "%");

		}

		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			query.setParameter("productDescription", "%"
					+ options.getDescription().toUpperCase() + "%");
		}
		if (options.getPrice() > 0) {
			query.setParameter("productPrice", options.getPrice());
		}

		if (options.getPriceIni() > 0) {
			query.setParameter("productPriceIni", options.getPriceIni());
		}

		if (options.getProdId() != null && options.getProdId() > 0) {
			query.setParameter("productId", options.getProdId());
		}
	}

	@Override
	protected void appendOrder(StringBuilder predicate,
			ProdSearchOptions options) {
		if (options.getOrder() != null) {
			predicate.append(" order by product.");
			predicate.append(options.getOrder().getValue());
			if (options.getDesc()) {
				predicate.append(" desc");
			}
		}

	}

	@Override
	protected String createQueryOne() {
		String query = "select product from Product product "
				+ "where upper(product.name) = :productName";
		return query;
	}
}
