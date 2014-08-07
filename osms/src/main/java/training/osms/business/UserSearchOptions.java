package training.osms.business;

import training.framework.business.AbstractEntitySearchOptions;

public class UserSearchOptions extends AbstractEntitySearchOptions {
	public enum Order {
		ID("id"), NAME("name");

		private String value;

		private Order(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	private Order order;
	private boolean desc;
	private Integer useId;
	private String name;
	private String email;

	public UserSearchOptions() {
		order = Order.NAME;
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

	public Integer getUseId() {
		return useId;
	}

	public void setUseId(Integer useId) {
		this.useId = useId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
