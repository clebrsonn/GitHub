package training.osms.business.pedido;

import java.util.Date;

import training.framework.business.AbstractEntitySearchOptions;

public class PedSearchOptions extends AbstractEntitySearchOptions {
	public enum Order {
		ID("id"), DATE_BUY("dateBuy");

		private String value;

		private Order(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	private Integer pedidoId;
	private Date date;
	private Order order;
	private boolean desc;
	private Integer prodId;
	private Date dateFim;

	public PedSearchOptions() {
		order = Order.DATE_BUY;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public boolean getDesc() {
		return desc;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setDateFim(Date dateFim) {
		this.dateFim = dateFim;
	}

	public Date getDateFim() {
		return dateFim;
	}
}