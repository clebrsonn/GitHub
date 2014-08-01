package training.osms.business.PromoMail;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import training.osms.business.product.Product;

@Entity
@Table(name = "PRM_PROMOMAIL")
public class PromoMail {

	private Date dateIni;
	private Date dateFim;
	private String name;
	private Integer id;

	private List<Product> productMail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRM_INI_DATE")
	public Date getDateIni() {
		return dateIni;
	}

	public void setDateIni(Date dateIni) {
		this.dateIni = dateIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRM_FIM_DATE")
	public Date getDateFim() {
		return dateFim;
	}

	public void setDateFim(Date dateFim) {
		this.dateFim = dateFim;
	}

	@Column(name = "PRM_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRM_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProductMail(List<Product> productMail) {
		this.productMail = productMail;
	}

	@ManyToMany
	@JoinTable(name = "PMC_PRODUCT_PROMOMAIL", joinColumns = @JoinColumn(name = "PRM_ID"), inverseJoinColumns = @JoinColumn(name = "PRO_ID"))
	public List<Product> getProductMail() {
		return productMail;
	}
}
