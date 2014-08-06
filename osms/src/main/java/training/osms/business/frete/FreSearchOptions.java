package training.osms.business.frete;

import training.framework.business.AbstractEntitySearchOptions;

public class FreSearchOptions extends AbstractEntitySearchOptions {

	private Integer freteId;
	private boolean desc;
	private Integer pedidoId;
	private String tipoFrete;
	private Integer userId;

	public void setFreteId(Integer freteId) {
		this.freteId = freteId;
	}

	public Integer getFreteId() {
		return freteId;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public boolean getDesc() {
		return desc;
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}