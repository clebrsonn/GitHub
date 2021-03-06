package training.osms.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USE_USER")
public class User implements Cloneable {

	private Integer id;
	private String name;
	private String email;
	private List<Product> products;
	private List<Pedido> pedidos;

	private List<Avaliacao> avaliacao;
	private List<PromoMail> promoMails;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USE_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "USE_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USE_EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "user")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	@OneToMany(mappedBy = "user")
	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setPromoMails(List<PromoMail> promoMails) {
		this.promoMails = promoMails;
	}

	@ManyToMany(mappedBy = "userMail")//cascade = CascadeType.REMOVE)
	public List<PromoMail> getPromoMails() {
		return promoMails;
	}

	@Override
	public User clone() {
		try {
			return (User) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(" A VM esta louca!");
		}
	}

}
