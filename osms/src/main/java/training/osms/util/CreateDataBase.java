package training.osms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import training.osms.business.category.Category;

public class CreateDataBase {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("OSMS");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Category category = new Category();
		Category catPai = new Category();
		category.setName("Cleberson");
		category.setDescription("Barbosa");

		catPai.setName("Cleberson1");
		catPai.setDescription("Barbosa1");

		category.setCatPai(catPai);
		transaction.begin();
		manager.persist(category);
		manager.persist(catPai);
		transaction.commit();

	}

}
