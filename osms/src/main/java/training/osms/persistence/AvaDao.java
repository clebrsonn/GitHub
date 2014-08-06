package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.avaliacao.AvaSearchOptions;
import training.osms.business.avaliacao.Avaliacao;

@Component
public class AvaDao extends EntityDao<Avaliacao, AvaSearchOptions> {

	public AvaDao() {
		super(Avaliacao.class, "avaliacao");
	}

	public boolean containsEntity(Integer avaId) {
		return super.searchOneEntity(avaId) != null;
	}

	@Override
	protected StringBuilder toPredicate(AvaSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getId() != null && options.getId() > 0) {
			predicate.append(" and avaliacao.id = :avaId");
		}
		if (options.getAvaliacao() != null
				&& options.getAvaliacao().length() > 0) {
			predicate.append(" and avaliacao.avaliacao like :avaliacao");
		}

		if (options.getUserId() != null && options.getUserId() > 0) {
			predicate.append(" and avaliacao.user.id = :userId");
		}

		if (options.getProdId() != null && options.getProdId() > 0) {
			predicate.append(" and avaliacao.product.id = :prodId");
		}

		return predicate;
	}

	@Override
	protected void setParameters(AvaSearchOptions options, TypedQuery<?> query) {
		if (options.getProdId() != null && options.getProdId() > 0) {
			query.setParameter("prodId", options.getProdId());
		}

		if (options.getUserId() != null) {
			query.setParameter("userId", options.getUserId());
		}

		if (options.getAvaliacao() != null
				&& options.getAvaliacao().length() > 0) {
			query.setParameter("avaliacao", "%"
					+ options.getAvaliacao().toUpperCase() + "%");

		}

		if (options.getId() != null && options.getId() > 0) {
			query.setParameter("avaId", options.getId());
		}

	}

	@Override
	protected void appendOrder(StringBuilder predicate, AvaSearchOptions options) {

	}

	@Override
	protected String createQueryOne() {
		String query = "select avaliacao from Avaliacao avaliacao "
				+ "where upper(avaliacao.avaliacao) = :avaliacao";
		return query;
	}

}
