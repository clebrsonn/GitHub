package training.osms.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CAT_CATEGORY")
public class Category implements Cloneable {

	private String name;
	private String description;
	private Integer id;
	private Category catPai;
	private List<Product> products;

	public Category() {
		products = new ArrayList<>();
	}

	@Size(min = 1, max = 100)
	@Column(name = "CAT_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CAT_DS")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAT_ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "CAT_PAI", nullable = true)
	public Category getCatPai() {
		return catPai;
	}

	public void setCatPai(Category catPai) {
		this.catPai = catPai;
	}

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category clone() {
		try {
			return (Category) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("A VM está louca");
		}
	}

}
