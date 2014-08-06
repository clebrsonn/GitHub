package training.osms.persistence;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.framework.persistence.EntityDao;
import training.osms.business.pedido.PedSearchOptions;
import training.osms.business.pedido.Pedido;

@Component
public class PedidoDao extends EntityDao<Pedido, PedSearchOptions> {

	public PedidoDao() {
		super(Pedido.class, "pedido");
	}

	public boolean containsEntity(Integer pedId) {
		return super.searchOneEntity(pedId) != null;
	}

	@Override
	protected StringBuilder toPredicate(PedSearchOptions options) {
		StringBuilder predicate = new StringBuilder();

		if (options.getPedidoId() != null && options.getPedidoId() > 0) {
			predicate.append(" and pedido.id = :pedId");
		}
		

		if (options.getUserId() != null && options.getUserId() > 0) {
			predicate.append(" and pedido.user = :userId");
		}

		
		if (options.getDate() != null && options.getDateFim() != null) {
			predicate
					.append(" and pedido.dateBuy between :pedidoDate and :pedidoFim");
		} else {

			if (options.getDate() != null) {
				predicate.append(" and pedido.dateBuy >= :pedidoDate");
			}

			if (options.getDateFim() != null) {
				predicate.append(" and pedido.dateBuy <= :pedidoFim");
			}
		}

		return predicate;
	}

	@Override
	protected void setParameters(PedSearchOptions options, TypedQuery<?> query) {
		if (options.getDateFim() != null) {
			query.setParameter("pedidoFim", options.getDateFim());
		}
		if (options.getDate() != null) {
			query.setParameter("pedidoDate", options.getDate());
		}
		if (options.getPedidoId() != null && options.getPedidoId() > 0) {
			query.setParameter("pedId", options.getPedidoId());
		}
		if (options.getUserId() != null && options.getUserId() > 0) {
			query.setParameter("userId", options.getUserId());
		}
	}

	@Override
	protected void appendOrder(StringBuilder predicate, PedSearchOptions options) {
		if (options.getOrder() != null) {
			predicate.append(" order by pedido.");
			predicate.append(options.getOrder().getValue());
			if (options.getDesc()) {
				predicate.append(" desc");
			}
		}
	}

	@Override
	protected String createQueryOne() {
		String query = "select pedido from training.osms.business.Pedido pedido "
				+ "where pedido.id = :entityName";
		return query;
	}
}
