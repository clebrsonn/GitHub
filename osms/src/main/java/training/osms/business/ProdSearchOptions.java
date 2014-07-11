package training.osms.business;

import training.framework.business.AbstractEntitySearchOptions;

public class ProdSearchOptions extends AbstractEntitySearchOptions {

	public enum Order {
		NAME("name"), PRICE("price");

		private Order(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}
	}

	private String name;
	private String description;
	private double price;
	private double priceIni;

	private Order order;
	private boolean desc;
	private Integer catId;
	private Integer prodId;
	private Integer useId;

	public ProdSearchOptions() {
		order = Order.NAME;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean getDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setPriceIni(double priceIni) {
		this.priceIni = priceIni;
	}

	public double getPriceIni() {
		return priceIni;
	}

	public void setUseId(Integer useId) {
		this.useId = useId;
	}

	public Integer getUseId() {
		return useId;
	}
}
