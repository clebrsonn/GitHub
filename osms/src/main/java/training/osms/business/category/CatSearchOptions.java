package training.osms.business.category;

import training.framework.business.AbstractEntitySearchOptions;

public class CatSearchOptions extends AbstractEntitySearchOptions {

	public enum Order {
		NAME("name"), DESCRIPTION("description");

		private String value;

		private Order(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	private Integer id;
	private String name;
	private Integer catPai;
	private String description;
	private boolean desc;
	private Order order;

	public CatSearchOptions() {
		order = Order.NAME;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCatPai(Integer catPai) {
		this.catPai = catPai;
	}

	public Integer getCatPai() {
		return catPai;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public boolean getDesc() {
		return desc;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Id=" + id + "[name=" + name + ", description=" + description
				+ "]";
	}


}
