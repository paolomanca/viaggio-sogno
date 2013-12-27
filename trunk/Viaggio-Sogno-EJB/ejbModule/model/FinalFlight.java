package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the final_flight database table.
 * 
 */
@Entity
@Table(name="final_flight")
@NamedQuery(name="FinalFlight.findAll", query="SELECT f FROM FinalFlight f")
public class FinalFlight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idFINAL_FLIGHT;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date departure;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	//bi-directional many-to-one association to FinalPackage
	@ManyToOne
	@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
	private FinalPackage finalPackage;

	public FinalFlight() {
	}

	public int getIdFINAL_FLIGHT() {
		return this.idFINAL_FLIGHT;
	}

	public void setIdFINAL_FLIGHT(int idFINAL_FLIGHT) {
		this.idFINAL_FLIGHT = idFINAL_FLIGHT;
	}

	public Date getDeparture() {
		return this.departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public FinalPackage getFinalPackage() {
		return this.finalPackage;
	}

	public void setFinalPackage(FinalPackage finalPackage) {
		this.finalPackage = finalPackage;
	}

}