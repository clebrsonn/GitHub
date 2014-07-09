package training.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import training.osms.business.ProdSearchOptions;
import training.osms.business.Product;

@Component
public class ProdDao {

	private @PersistenceContext
	EntityManager manager;

	public void insertProd(Product prod) {
		// TODO Auto-generated method stub

	}

	public Integer searchProdCount(ProdSearchOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> searchProds(ProdSearchOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateProd(Product prod) {
		// TODO Auto-generated method stub

	}

	public void deleteProd(Product prod) {
		// TODO Auto-generated method stub

	}

}
