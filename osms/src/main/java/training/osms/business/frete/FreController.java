package training.osms.business.frete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
import training.osms.persistence.FreteDao;

@Component
public class FreController {

	private @Autowired
	FreteDao dao;

	public void setDao(FreteDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void saveFrete(Frete frete) {
		// if (dao.containsEntity(frete.getId())) {
		// throw new BusinessException("There is a frete of id: " +
		// frete.getId() + " already");
		// }
		if (frete != null) {
			dao.insertEntity(frete);
		}
		else{
			throw new BusinessException("Select a frete Type");
		}
	}

	public Integer searchFreteCount(FreSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public List<Frete> searchFrete(FreSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void updateFrete(Frete frete) {
		Frete databaseFrete = dao.searchOneEntity(frete.getId());
		if (databaseFrete == null) {
			dao.updateEntity(frete);
		} else {
			if (frete.getId().equals(databaseFrete.getId())) {
				dao.updateEntity(frete);
			} else {
				throw new BusinessException("There is a frete id: "
						+ frete.getId() + " already");
			}
		}
	}

	@Transactional
	public void deleteFrete(Frete frete) {
		// for (Product product : frete.getProducts()) {
		// /*
		// * boolean removed; do { removed =
		// * product.getFretes().remove(frete); } while (removed);
		// */
		//
		// while (product.getFretes().contains(frete)) {
		// product.getFretes().remove(frete);
		// }
		// }
		dao.deleteEntity(frete);
	}

}
