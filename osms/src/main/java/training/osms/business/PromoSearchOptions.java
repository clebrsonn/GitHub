package training.osms.business;

import java.util.Date;

import training.framework.business.AbstractEntitySearchOptions;

public class PromoSearchOptions extends AbstractEntitySearchOptions{

	private Integer prodId;
	private Date dateIni;
	private Date dateFim;
	private String name;
	private Integer id;

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Date getDateIni() {
		return dateIni;
	}

	public void setDateIni(Date dateIni) {
		this.dateIni = dateIni;
	}

	public Date getDateFim() {
		return dateFim;
	}

	public void setDateFim(Date dateFim) {
		this.dateFim = dateFim;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
