package training.osms.business.PromoMail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.osms.persistence.PromoDao;

@Component
public class PromoController {

	private @Autowired
	PromoDao dao;

	public void setDao(PromoDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void savePromo(PromoMail promo) {
		dao.insertEntity(promo);
	}

	public Integer searchProdCount(PromoSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public List<PromoMail> searchProd(PromoSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void updateProd(PromoMail promo) {
		dao.updateEntity(promo);
	}

	@Transactional
	public void deleteProd(PromoMail promo) {
		dao.deleteEntity(promo);
	}

}
