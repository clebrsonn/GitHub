package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
import training.osms.persistence.CatDao;

@Component
public class CatController {

	private @Autowired
	CatDao dao;

	public void setDao(CatDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void save(Category category) {
		if (dao.containsEntity(category.getName())) {
			throw new BusinessException("There is a category named "
					+ category.getName() + " already");
		} else {
			dao.insertEntity(category);

		}
	}

	public List<Category> searchCats(CatSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void delete(Category category) {
		if (category.getProducts().isEmpty()) {
			dao.deleteEntity(category);
		} else {

			throw new BusinessException("The category can't deleted. Probably "
					+ "have products!");
		}
	}

	@Transactional
	public void updateCategory(Category category) {
		Category databaseCat = dao.searchOneEntity(category.getName());
		if (databaseCat == null) {
			dao.updateEntity(category);
		} else {
			if (category.getId().equals(databaseCat.getId())) {
				dao.updateEntity(category);
			} else {
				throw new BusinessException("There is a named category "
						+ category.getName() + " allready");
			}

		}
	}

	public Integer searchCatCount(CatSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public Category searchOneCat(Integer catId) {
		return dao.searchOneEntity(catId);
	}

}
