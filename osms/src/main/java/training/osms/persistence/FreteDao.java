package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.frete.FreSearchOptions;
import training.osms.business.frete.Frete;

@Component
public class FreteDao extends EntityDao<Frete, FreSearchOptions> {

	public FreteDao() {
		super(Frete.class, "frete");
	}

	public boolean containsEntity(Integer pedId) {
		return super.searchOneEntity(pedId) != null;
	}

	@Override
	protected StringBuilder toPredicate(FreSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getFreteId() != null && options.getFreteId() > 0) {
			predicate.append(" and frete.id = :freteId");
		}

		if (options.getUserId() != null && options.getUserId() > 0) {
			predicate.append(" and frete.user.id = :userId");
		}
		if (options.getPedidoId() != null && options.getPedidoId() > 0) {
			predicate.append(" and frete.pedido.id = :pedidoId");
		}
		if (options.getTipoFrete() != null
				&& options.getTipoFrete().length() > 0) {
			predicate.append(" and frete.tipoFrete = :tipoFrete");
		}

		return predicate;
	}

	@Override
	protected void setParameters(FreSearchOptions options, TypedQuery<?> query) {
		if (options.getFreteId() != null && options.getFreteId() > 0) {
			query.setParameter("freteId", options.getFreteId());
		}
		if (options.getUserId() != null && options.getUserId() > 0) {
			query.setParameter("userId", options.getUserId());
		}
		if (options.getPedidoId() != null && options.getPedidoId() > 0) {
			query.setParameter("pedidoId", options.getPedidoId());
		}
		if (options.getTipoFrete() != null
				&& options.getTipoFrete().length() > 0) {
			query.setParameter("tipoFrete", options.getTipoFrete());
		}
		
	}

	@Override
	protected String createQueryOne() {
		String query = "select frete from training.osms.business.Frete frete "
				+ "where frete.id = :entityName";
		return query;
	}

	@Override
	protected void appendOrder(StringBuilder predicate, FreSearchOptions options) {
		// TODO Auto-generated method stub

	}
}
