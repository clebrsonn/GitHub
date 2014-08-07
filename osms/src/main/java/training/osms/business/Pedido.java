package training.osms.business;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PED_PEDIDO")
public class Pedido implements Cloneable {

	private Date dateBuy;
	private Integer id;
	private List<Product> products;
	private User user;
	private Frete frete;

	public Pedido() {
		products = new ArrayList<>();
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PED_DATE_BUY")
	public Date getDateBuy() {
		return dateBuy;
	}

	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PED_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToMany
	@JoinTable(name = "PRP_PRODUCT_PEDIDO", joinColumns = @JoinColumn(name = "PED_ID"), inverseJoinColumns = @JoinColumn(name = "PRO_ID"))
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "USE_ID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setFrete(Frete frete) {
		this.frete = frete;
	}

	@ManyToOne
	@JoinColumn(name = "FRE_ID", nullable=false)
	public Frete getFrete() {
		return frete;
	}

	@Override
	public Pedido clone() {
		try {
			return (Pedido) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(" A VM está louca!");
		}
	}
}
