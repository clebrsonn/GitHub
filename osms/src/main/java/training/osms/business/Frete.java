package training.osms.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FRE_FRETE")
public class Frete implements Cloneable {

	private Integer id;
	private List<Pedido> pedidos;
	private String tipoFrete;

	public Frete() {
		pedidos = new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FRE_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(mappedBy="frete")
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	@Column(name="FRE_TIPO")
	public String getTipoFrete() {
		return tipoFrete;
	}

	@Override
	public Frete clone() {
		try {
			return (Frete) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error(" A VM está louca!");
		}
	}
}
