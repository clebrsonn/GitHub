package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
import training.osms.persistence.ProdDao;

@Component
public class ProdController {
	private @Autowired
	ProdDao dao;

	public void setDao(ProdDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void saveProd(Product prod) {
		if (prod.getPrice() < 0) {
			throw new BusinessException("the price can not be less than zero");
		} else if (prod.getUser() == null) {
			throw new BusinessException("Select a User");
		} else {
			dao.insertEntity(prod);
		}
	}

	public Integer searchProdCount(ProdSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public List<Product> searchProd(ProdSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void updateProd(Product prod) {
		if (prod.getPrice() < 0) {
			throw new BusinessException("Insert a valid price");
		} else {
			dao.updateEntity(prod);
		}
	}

	@Transactional
	public void deleteProd(Product product) {
		if (product.getPedidos().isEmpty()) {
			dao.deleteEntity(product);

		} else {
			dao.updateEntity(product);

		}
	}
}
