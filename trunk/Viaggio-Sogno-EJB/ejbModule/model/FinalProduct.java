package model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FinalProduct
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class FinalProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDFINAL_PRODUCT", unique=true, nullable=false)
	private int id;

	@Column(name="IDFINAL_PRODUCT_RELATIVE", nullable=false)
	private int idRelative;

	//uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	public FinalProduct() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRelative() {
		return idRelative;
	}

	public void setIdRelative(int idRelative) {
		this.idRelative = idRelative;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FinalProduct))
			return false;
		FinalProduct other = (FinalProduct) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
