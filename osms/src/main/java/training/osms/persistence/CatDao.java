package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.category.CatSearchOptions;
import training.osms.business.category.Category;

@Component
public class CatDao extends EntityDao<Category, CatSearchOptions> {

	public CatDao() {
		super(Category.class, "category");

	}

	@Override
	protected StringBuilder toPredicate(CatSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getId() != null) {
			predicate.append(" and category.id = :catId");
		}

		if (options.getCatPai() != null) {
			predicate.append(" and category.catPai.id = :catPai");
		}

		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and upper(category.name) like :catName");
		}

		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			predicate.append(" and upper(category.description) like :catDesc");

		}

		return predicate;
	}

	@Override
	protected void setParameters(CatSearchOptions options, TypedQuery<?> query) {

		if (options.getId() != null && options.getId() > 0) {
			query.setParameter("catId", options.getId());

		}

		if (options.getCatPai() != null) {
			query.setParameter("catPai", options.getCatPai());

		}

		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("catName", "%" + options.getName().toUpperCase()
					+ "%");
		}

		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			query.setParameter("catDesc", "%"
					+ options.getDescription().toUpperCase() + "%");
		}

	}

	@Override
	protected void appendOrder(StringBuilder predicate, CatSearchOptions options) {
		if (options.getOrder() != null) {
			predicate.append(" order by category.");
			predicate.append(options.getOrder().getValue());
			if (options.getDesc()) {
				predicate.append(" desc");
			}
		}

	}

	@Override
	protected String createQueryOne() {
		String query = "select category from Category category where upper(category.name) = :entityName";
		return query;
	}

}
