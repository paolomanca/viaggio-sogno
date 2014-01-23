package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalPackageDTO;
import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the FINAL_PACKAGE database table.
 * 
 */
@Entity
@Table(name="FINAL_PACKAGE")
@NamedQuery(name="FinalPackage.findAll", query="SELECT f FROM FinalPackage f")
public class FinalPackage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDFINAL_PACKAGE", unique=true, nullable=false)
	private int idfinalPackage;

	//uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_idUSER", nullable=false)
	private User user;
	
	//bi-directional many-to-one association to FinalExcursion
	@OneToMany(mappedBy="finalPackage", cascade=CascadeType.ALL)
	private List<FinalExcursion> finalExcursions;

	//bi-directional many-to-one association to FinalFlight
	@OneToMany(mappedBy="finalPackage", cascade=CascadeType.ALL)
	private List<FinalFlight> finalFlights;

	//bi-directional many-to-one association to FinalHotel
	@OneToMany(mappedBy="finalPackage", cascade=CascadeType.ALL)
	private List<FinalHotel> finalHotels;

	//uni-directional many-to-one association to Package
	@ManyToOne
	@JoinColumn(name="PACKAGE_idPACKAGE", nullable=false)
	private Package pkg;

	//uni-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(
		name="final_package_has_product"
		, joinColumns={
			@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
			}
		)
	private List<Product> products;

	public FinalPackage() {
	}

	public FinalPackage(FinalPackageDTO finalPkgDTO) {
		this.idfinalPackage = finalPkgDTO.getId();
		this.pkg = new Package(finalPkgDTO.getOriginalPackage());
		this.products = new LinkedList<>();
	}

	public int getIdfinalPackage() {
		return this.idfinalPackage;
	}

	public void setIdfinalPackage(int idfinalPackage) {
		this.idfinalPackage = idfinalPackage;
	}

	public List<FinalExcursion> getFinalExcursions() {
		return finalExcursions;
	}

	public void setFinalExcursions(List<FinalExcursion> finalExcursions) {
		this.finalExcursions = finalExcursions;
	}

	public List<FinalFlight> getFinalFlights() {
		return finalFlights;
	}

	public void setFinalFlights(List<FinalFlight> finalFlights) {
		this.finalFlights = finalFlights;
	}

	public List<FinalHotel> getFinalHotels() {
		return finalHotels;
	}

	public void setFinalHotels(List<FinalHotel> finalHotels) {
		this.finalHotels = finalHotels;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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