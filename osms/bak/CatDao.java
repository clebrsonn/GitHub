package training.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.osms.business.CatSearchOptions;
import training.osms.business.Category;

@Component
public class CatDao {

	private @PersistenceContext
	EntityManager manager;

	// private EntityDao<Category> dao;

	public boolean containsCategory(String catName) {
		return searchCat(catName) != null;
	}

	public void insertCategory(Category category) {
		// dao.insertEntity(category);
		manager.persist(category);

	}

	public void deleteCategory(Category category) {
		Category catManaged = manager.find(Category.class, category.getId());
		manager.remove(catManaged);
	}

	public Category searchCat(String name) {
		TypedQuery<Category> query = manager
				.createQuery(
						"select category from Category category where upper(category.name) = :catName",
						Category.class);
		query.setParameter("catName", name.toUpperCase());
		List<Category> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public void updateCat(Category category) {
		// dao.updateEntity(category);
		manager.merge(category);
	}

	public List<Category> searchCats(CatSearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");
		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and upper(category.name) like :catName");
		}

		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			predicate.append(" and upper(category.description) like :catDesc");

		}
		String jpql = "select category from Category category where "
				+ predicate;
		TypedQuery<Category> query = manager.createQuery(jpql, Category.class);

		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("catName", "%" + options.getName().toUpperCase()
					+ "%");
		}

		if (options.getDescription() != null
				&& options.getDescription().length() > 0) {
			query.setParameter("catDesc", "%"
					+ options.getDescription().toUpperCase() + "%");
		}

		if (options.getStartPosition() != null) {
			query.setFirstResult(options.getStartPosition());
		}
		if (options.getMaxResults() != null) {
			query.setMaxResults(options.getMaxResults());
		}

		List<Category> result = query.getResultList();
		return result;
	}

}
