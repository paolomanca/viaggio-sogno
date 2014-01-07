package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FINAL_EXCURSION database table.
 * 
 */
@Entity
@Table(name="FINAL_EXCURSION")
@NamedQuery(name="FinalExcursion.findAll", query="SELECT f FROM FinalExcursion f")
public class FinalExcursion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idFINAL_EXCURSION;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	//bi-directional many-to-one association to FinalPackage
	@ManyToOne
	@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
	private FinalPackage finalPackage;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	public FinalExcursion() {
	}

	public int getIdFINAL_EXCURSION() {
		return this.idFINAL_EXCURSION;
	}

	public void setIdFINAL_EXCURSION(int idFINAL_EXCURSION) {
		this.idFINAL_EXCURSION = idFINAL_EXCURSION;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public FinalPackage getFinalPackage() {
		return this.finalPackage;
	}

	public void setFinalPackage(FinalPackage finalPackage) {
		this.finalPackage = finalPackage;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}