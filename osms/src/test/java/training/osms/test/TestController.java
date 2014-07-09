package training.osms.test;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import training.framework.business.BusinessException;
import training.osms.business.CatController;
import training.osms.business.Category;
import training.osms.persistence.CatDao;

public class TestController {

	@Test
	public void testControllerPass() {

		Category category = new Category();
		category.setName("Cleberson");
		category.setDescription("Barbosa");
		CatDao dao = EasyMock.createMock(CatDao.class);
		EasyMock.expect(dao.containsEntity("Cleberson")).andReturn(false);
		dao.insertEntity(category);
		EasyMock.replay(dao);

		CatController controller = new CatController();
		controller.setDao(dao);
		controller.save(category);

		EasyMock.verify(dao);

	}
	
	@Test
	public void testControllerUnpass(){
		
		Category category = new Category();
		category.setName("Cleberson");
		category.setDescription("Barbosa");
		CatDao dao = EasyMock.createMock(CatDao.class);
		EasyMock.expect(dao.containsEntity("Cleberson")).andReturn(true);
		EasyMock.replay(dao);
		
		CatController controller = new CatController();
		controller.setDao(dao);
		try {
			controller.save(category);
			Assert.fail("Excessão não levantada");
		} catch (RuntimeException e) {
			EasyMock.verify(dao);
			
		}
	}
	
	@Test
	public void testUpdateNoDatab(){
		Category category = new Category();
		category.setId(1);
		category.setName("informatica");
		category.setDescription("Produtos de Informatica");
		
		CatDao dao = EasyMock.createMock(CatDao.class);
		EasyMock.expect(dao.searchOneEntity("informatica")).andReturn(null);
		dao.updateEntity(category);
		EasyMock.replay(dao);
		
		CatController controller = new CatController();
		controller.setDao(dao);
		controller.updateCategory(category);
		
		EasyMock.verify(dao);	
	}
	
	@Test
	public void testUpdateId(){
		Category category = new Category();
		category.setId(1);
		category.setName("informatica");
		category.setDescription("Produtos de Informatica");
		
		Category catData = new Category();
		catData.setId(1);
		catData.setName("informatica");
		catData.setDescription("Produtos de Informatica");
		
		CatDao dao = EasyMock.createMock(CatDao.class);
		EasyMock.expect(dao.searchOneEntity("informatica")).andReturn(catData);
		dao.updateEntity(category);
		EasyMock.replay(dao);
		
		CatController controller = new CatController();
		controller.setDao(dao);
		controller.updateCategory(category);
		
		EasyMock.verify(dao);		
	}
	
	@Test
	public void testUpdate(){
		Category category = new Category();
		category.setId(1);
		category.setName("informatica");
		category.setDescription("Produtos de Informatica");
		
		Category catData = new Category();
		catData.setId(2);
		catData.setName("informatica");
		catData.setDescription("Produtos de Informatica");
		
		CatDao dao = EasyMock.createMock(CatDao.class);
		EasyMock.expect(dao.searchOneEntity("informatica")).andReturn(catData);
		EasyMock.replay(dao);
		
		CatController controller = new CatController();
		controller.setDao(dao);
		try {
			controller.updateCategory(category);
			Assert.fail("Excessão não levantada");
			
		} catch (BusinessException e) {
			EasyMock.verify(dao);
		}
		
	}
	

}
