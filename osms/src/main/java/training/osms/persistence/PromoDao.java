package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.PromoMail;
import training.osms.business.PromoSearchOptions;

@Component
public class PromoDao extends EntityDao<PromoMail, PromoSearchOptions> {

	public PromoDao() {
		super(PromoMail.class, "promoMail");

	}

	@Override
	protected StringBuilder toPredicate(PromoSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getId() != null && options.getId() > 0) {
			predicate.append(" and promoMail.id = :promoId");
		}

		if (options.getName() != null && options.getName().length() > 0) {
			predicate.append(" and upper(promoMail.name) like :promoName");
		}

		if (options.getProdId() != null && options.getProdId() > 0) {
			predicate.append(" and promoMail.product.id = :prodId");
		}

		if (options.getDateIni() != null) {
			predicate.append(" and promoMail.dateIni >= :promoIni");
		}

		if (options.getDateFim() != null) {
			predicate.append(" and pedido.dateFim <= :promoFim");
		}

		return predicate;
	}

	@Override
	protected void setParameters(PromoSearchOptions options, TypedQuery<?> query) {
		if (options.getDateFim() != null) {
			query.setParameter("promoFim", options.getDateFim());
		}
		if (options.getDateIni() != null) {
			query.setParameter("promoIni", options.getDateIni());
		}
		if (options.getId() != null && options.getId() > 0) {
			query.setParameter("promoId", options.getId());
		}

		if (options.getProdId() != null && options.getProdId() > 0) {
			query.setParameter("prodId", options.getProdId());
		}

		if (options.getName() != null && options.getName().length() > 0) {
			query.setParameter("promoName", "%"
					+ options.getName().toUpperCase() + "%");
		}

	}

	@Override
	protected void appendOrder(StringBuilder predicate,
			PromoSearchOptions options) {
	}

	@Override
	protected String createQueryOne() {
		String query = "select promoMail from PromoMail promoMail "
				+ "where upper(promoMail.name) = :promoName";
		return query;
	}

}
