package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalFlightDTO;

import java.util.Date;


/**
 * The persistent class for the FINAL_FLIGHT database table.
 * 
 */
@Entity
@Table(name="FINAL_FLIGHT")
@NamedQuery(name="FinalFlight.findAll", query="SELECT f FROM FinalFlight f")
public class FinalFlight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDFINAL_FLIGHT", unique=true, nullable=false)
	private int idfinalFlight;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date departure;

	//uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	//uni-directional many-to-one association to FinalPackage
	@ManyToOne
	@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
	private FinalPackage finalPackage;

	public FinalFlight() {
	}

	public FinalFlight(FinalFlightDTO fP) {
		this.idfinalFlight = fP.getId();
		this.departure = fP.getDeparture();
	}

	public int getIdfinalFlight() {
		return this.idfinalFlight;
	}

	public void setIdfinalFlight(int idfinalFlight) {
		this.idfinalFlight = idfinalFlight;
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