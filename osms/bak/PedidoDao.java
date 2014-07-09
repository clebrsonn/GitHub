package training.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import training.osms.business.PedSearchOptions;
import training.osms.business.Pedido;

@Component
public class PedidoDao {

	private @PersistenceContext
	EntityManager manager;

	// private EntityDao<Pedido> dao;

	public boolean containsPedido(Integer pedId) {
		return searchPedido(pedId) != null;
	}

	public void insertPedido(Pedido pedido) {
		manager.persist(pedido);
	}

	public Integer searchPedidoCount(PedSearchOptions options) {
		StringBuilder predicate = toPredicate(options);
		String jpql = "select count(pedido) from Pedido pedido where "
				+ predicate;
		TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
		setParameters(options, query);
		Long result = query.getSingleResult();
		return result.intValue();
	}

	public List<Pedido> searchPedidos(PedSearchOptions options) {
		StringBuilder predicate = toPredicate(options);

		if (options.getOrder() != null) {
			predicate.append(" order by pedido.");
			predicate.append(options.getOrder().getValue());
			if (options.getDesc()) {
				predicate.append(" desc");
			}
		}

		String jpql = "select pedido from Pedido pedido where " + predicate;
		TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
		setParameters(options, query);

		if (options.getStartPosition() != null) {
			query.setFirstResult(options.getStartPosition());
		}
		if (options.getMaxResults() != null) {
			query.setMaxResults(options.getMaxResults());
		}

		List<Pedido> result = query.getResultList();
		return result;
	}

	public Pedido searchPedido(Integer pedId) {
		TypedQuery<Pedido> query = manager.createQuery(
				"select pedido from training.bms.business.Pedido pedido "
						+ "where pedido.id = :pedId", Pedido.class);
		query.setParameter("pedId", pedId);
		List<Pedido> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public void updatePedido(Pedido pedido) {
		manager.merge(pedido);

	}

	public void deletePedido(Pedido pedido) {
		Pedido delManaged = manager.find(Pedido.class, pedido.getId());
		manager.remove(delManaged);
	}

	private StringBuilder toPredicate(PedSearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");
		if (options.getPedidoId() != null && options.getPedidoId() > 0) {
			predicate.append(" and pedido.id = :pedidoId");
		}

		if (options.getDate() != null && options.getDateFim() != null) {
			predicate.append(" and pedido.dateBuy between :pedidoDate and :pedidoFim");
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

	private void setParameters(PedSearchOptions options, TypedQuery<?> query) {
		if (options.getPedidoId() != null) {
			query.setParameter("pedidoId", options.getPedidoId());
		}
		if (options.getDate() != null) {
			query.setParameter("pedidoDate", options.getDate());
		}

		if (options.getDateFim() != null) {
			query.setParameter("pedidoFim", options.getDateFim());
		}
	}

}
