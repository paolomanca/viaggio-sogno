package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the final_hotel database table.
 * 
 */
@Entity
@Table(name="final_hotel")
@NamedQuery(name="FinalHotel.findAll", query="SELECT f FROM FinalHotel f")
public class FinalHotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idFINAL_HOTEL;

	@Temporal(TemporalType.DATE)
	@Column(name="check_in", nullable=false)
	private Date checkIn;

	@Temporal(TemporalType.DATE)
	@Column(name="check_out", nullable=false)
	private Date checkOut;

	//bi-directional many-to-one association to FinalPackage
	@ManyToOne
	@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
	private FinalPackage finalPackage;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	public FinalHotel() {
	}

	public int getIdFINAL_HOTEL() {
		return this.idFINAL_HOTEL;
	}

	public void setIdFINAL_HOTEL(int idFINAL_HOTEL) {
		this.idFINAL_HOTEL = idFINAL_HOTEL;
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