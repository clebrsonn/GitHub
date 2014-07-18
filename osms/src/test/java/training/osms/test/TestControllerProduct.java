package training.osms.test;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import training.framework.business.BusinessException;
import training.osms.business.product.ProdController;
import training.osms.business.product.Product;
import training.osms.persistence.ProdDao;

public class TestControllerProduct {

	@Test
	public void testUnsaved() {
		
		Product product = new Product();
		product.setName("Testes");
		product.setDescription("123");
		product.setId(1);
		product.setPrice(-10);
		
		ProdDao dao = EasyMock.createMock(ProdDao.class);
		//EasyMock.expect(dao.containsEntity("Testes")).andReturn(false);

		EasyMock.replay(dao);
		
		ProdController controller = new ProdController();
		controller.setDao(dao);
		
		//controller.saveProd(product);
		
		try{
			controller.saveProd(product);
			Assert.fail("not levanted");
		}catch (BusinessException e){
			
		}
		
		EasyMock.verify(dao);

	}
	@Test 
	public void saved(){

		Product product = new Product();
		product.setName("Testes");
		product.setDescription("123");
		product.setId(1);
		product.setPrice(10);
		
		ProdDao dao = EasyMock.createMock(ProdDao.class);
		dao.insertEntity(product);

		EasyMock.replay(dao);
		
		ProdController controller = new ProdController();
		controller.setDao(dao);
		
		controller.saveProd(product);
		
//		try{
//			controller.saveProd(product);
//			Assert.fail("not levanted");
//		}catch (BusinessException e){
//			
//		}
		
		EasyMock.verify(dao);

	}
	
}
