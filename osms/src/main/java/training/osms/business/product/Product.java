package training.osms.business.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import training.osms.business.PromoMail.PromoMail;
import training.osms.business.avaliacao.Avaliacao;
import training.osms.business.category.Category;
import training.osms.business.pedido.Pedido;
import training.osms.business.user.User;

@Entity
@Table(name = "PRO_PRODUCT")
public class Product implements Cloneable {

	private String name;
	private String description;
	private String image;
	private double price;
	private Integer Id;
	private boolean visible;

	private Category category;
	private List<Pedido> pedidos;

	private User user;
	private List<Avaliacao> avaliacao;
	private List<PromoMail> promoMails;

	@Column(name = "PRO_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PRO_DS")
	@Size(max = 1000000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRO_IMAGE")
	public String getImage() {
		if (image == null)
			return "http://placehold.it/235x235";
		else
			return image;

	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "PRO_PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID")
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "CAT_ID")
	public Category getCategory() {
		return category;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@ManyToMany(mappedBy = "products")
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Column(name = "PRO_VISIBLE")
	public boolean getVisible() {
		return visible;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	@OneToMany(mappedBy = "product")
	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "USE_ID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setPromoMails(List<PromoMail> promoMails) {
		this.promoMails = promoMails;
	}

	@ManyToMany(mappedBy="productMail")
	public List<PromoMail> getPromoMails() {
		return promoMails;
	}

	public Product clone() {
		try {
			return (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("A VM está louca");
		}
	}

}
