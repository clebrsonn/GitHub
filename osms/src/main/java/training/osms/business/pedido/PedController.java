package training.osms.business.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.framework.business.BusinessException;
import training.osms.business.product.Product;
import training.osms.persistence.PedidoDao;

@Component
public class PedController {

	private @Autowired
	PedidoDao dao;

	public void setDao(PedidoDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void savePedido(Pedido pedido) {
		// if (dao.containsEntity(pedido.getId())) {
		// throw new BusinessException("There is a pedido of id: " +
		// pedido.getId() + " already");
		// }
		if (pedido.getUser() == null) {
			throw new BusinessException("There is a pedido need a user");
		} else {
			dao.insertEntity(pedido);
		}
	}

	public Integer searchPedidoCount(PedSearchOptions options) {
		return dao.searchEntityCount(options);
	}

	public List<Pedido> searchPedido(PedSearchOptions options) {
		return dao.searchEntity(options);
	}

	@Transactional
	public void updatePedido(Pedido pedido) {
		Pedido databasePedido = dao.searchOneEntity(pedido.getId());
		if (databasePedido == null) {
			dao.updateEntity(pedido);
		} else {
			if (pedido.getId().equals(databasePedido.getId())) {
				dao.updateEntity(pedido);
			} else {
				throw new BusinessException("There is a pedido id: "
						+ pedido.getId() + " already");
			}
		}
	}

	@Transactional
	public void deletePedido(Pedido pedido) {
		for (Product product : pedido.getProducts()) {
			/*
			 * boolean removed; do { removed =
			 * product.getPedidos().remove(pedido); } while (removed);
			 */

			while (product.getPedidos().contains(pedido)) {
				product.getPedidos().remove(pedido);
			}
		}
		dao.deleteEntity(pedido);
	}

}
