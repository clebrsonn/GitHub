package training.osms.business.PromoMail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
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
		if (promo.getDateIni().after(promo.getDateFim())) {
			throw new BusinessException("Insert a valid date");
		} else if (promo.getUserMail().equals(null)
				|| promo.getProductMail().equals(null)) {
			throw new BusinessException("User and product required");
		} else {

			dao.insertEntity(promo);
		}
	}

	public Integer searchPromoCount(PromoSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public List<PromoMail> searchPromo(PromoSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void updatePromo(PromoMail promo) {
		dao.updateEntity(promo);
	}

	@Transactional
	public void deletePromo(PromoMail promo) {
		dao.deleteEntity(promo);
	}

}
