package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalHotelDTO;

import java.util.Date;


/**
 * The persistent class for the FINAL_HOTEL database table.
 * 
 */
@Entity
@Table(name="FINAL_HOTEL")
@NamedQuery(name="FinalHotel.findAll", query="SELECT f FROM FinalHotel f")
public class FinalHotel extends FinalProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TYPE = Product.HOTEL;
	
	@Temporal(TemporalType.DATE)
	@Column(name="check_in", nullable=false)
	private Date checkIn;

	@Temporal(TemporalType.DATE)
	@Column(name="check_out", nullable=false)
	private Date checkOut;

	//uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	//uni-directional many-to-one association to FinalPackage
	@ManyToOne
	@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
	private FinalPackage finalPackage;

	public FinalHotel() {
	}

	public FinalHotel(FinalHotelDTO fHDTO) {
		this.setId(fHDTO.getId());
		this.checkIn = fHDTO.getCheckIn();
		this.checkOut = fHDTO.getCheckOut();
	}

	public Date getCheckIn() {
		return this.checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
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