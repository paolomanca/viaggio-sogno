package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the final_package database table.
 * 
 */
@Entity
@Table(name="final_package")
@NamedQuery(name="FinalPackage.findAll", query="SELECT f FROM FinalPackage f")
public class FinalPackage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idFINAL_PACKAGE;

	//bi-directional many-to-one association to FinalExcursion
	@OneToMany(mappedBy="finalPackage")
	private List<FinalExcursion> finalExcursions;

	//bi-directional many-to-one association to FinalFlight
	@OneToMany(mappedBy="finalPackage")
	private List<FinalFlight> finalFlights;

	//bi-directional many-to-one association to FinalHotel
	@OneToMany(mappedBy="finalPackage")
	private List<FinalHotel> finalHotels;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_idCUSTOMER", nullable=false)
	private Customer customer;

	//bi-directional many-to-one association to Package
	@ManyToOne
	@JoinColumn(name="PACKAGE_idPACKAGE", nullable=false)
	private Package pkg;

	//bi-directional many-to-many association to Product
	@ManyToMany(mappedBy="finalPackages")
	private List<Product> products;

	public FinalPackage() {
	}

	public int getIdFINAL_PACKAGE() {
		return this.idFINAL_PACKAGE;
	}

	public void setIdFINAL_PACKAGE(int idFINAL_PACKAGE) {
		this.idFINAL_PACKAGE = idFINAL_PACKAGE;
	}

	public List<FinalExcursion> getFinalExcursions() {
		return this.finalExcursions;
	}

	public void setFinalExcursions(List<FinalExcursion> finalExcursions) {
		this.finalExcursions = finalExcursions;
	}

	public FinalExcursion addFinalExcursion(FinalExcursion finalExcursion) {
		getFinalExcursions().add(finalExcursion);
		finalExcursion.setFinalPackage(this);

		return finalExcursion;
	}

	public FinalExcursion removeFinalExcursion(FinalExcursion finalExcursion) {
		getFinalExcursions().remove(finalExcursion);
		finalExcursion.setFinalPackage(null);

		return finalExcursion;
	}

	public List<FinalFlight> getFinalFlights() {
		return this.finalFlights;
	}

	public void setFinalFlights(List<FinalFlight> finalFlights) {
		this.finalFlights = finalFlights;
	}

	public FinalFlight addFinalFlight(FinalFlight finalFlight) {
		getFinalFlights().add(finalFlight);
		finalFlight.setFinalPackage(this);

		return finalFlight;
	}

	public FinalFlight removeFinalFlight(FinalFlight finalFlight) {
		getFinalFlights().remove(finalFlight);
		finalFlight.setFinalPackage(null);

		return finalFlight;
	}

	public List<FinalHotel> getFinalHotels() {
		return this.finalHotels;
	}

	public void setFinalHotels(List<FinalHotel> finalHotels) {
		this.finalHotels = finalHotels;
	}

	public FinalHotel addFinalHotel(FinalHotel finalHotel) {
		getFinalHotels().add(finalHotel);
		finalHotel.setFinalPackage(this);

		return finalHotel;
	}

	public FinalHotel removeFinalHotel(FinalHotel finalHotel) {
		getFinalHotels().remove(finalHotel);
		finalHotel.setFinalPackage(null);

		return finalHotel;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Package getPackage() {
		return this.pkg;
	}

	public void setPackage(Package pkg) {
		this.pkg = pkg;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}