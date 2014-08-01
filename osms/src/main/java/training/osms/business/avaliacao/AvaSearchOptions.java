package training.osms.business.avaliacao;

import training.framework.business.AbstractEntitySearchOptions;

public class AvaSearchOptions extends AbstractEntitySearchOptions {

	private Integer userId;
	private Integer prodId;
	private String avaliacao;
	private Integer id;

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
