package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@Table(name="PRODUCT")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idPRODUCT;

	@Column(length=45)
	private String description;

	@Column(length=45)
	private String depAirport;

	private int flightLength;

	@Column(length=45)
	private String location;

	@Column(length=45)
	private String name;

	private BigInteger price;

	private int rating;

	@Column(length=45)
	private String arrAirport;

	@Column(nullable=false, length=10)
	private String type;

	//bi-directional many-to-one association to FinalExcursion
	@OneToMany(mappedBy="product")
	private List<FinalExcursion> finalExcursions;

	//bi-directional many-to-one association to FinalFlight
	@OneToMany(mappedBy="product")
	private List<FinalFlight> finalFlights;

	//bi-directional many-to-one association to FinalHotel
	@OneToMany(mappedBy="product")
	private List<FinalHotel> finalHotels;

	//bi-directional many-to-many association to Package
	@ManyToMany(mappedBy="products")
	private List<Package> packages;

	//bi-directional many-to-many association to FinalPackage
	@ManyToMany
	@JoinTable(
		name="FINAL_PACKAGE_has_PRODUCT"
		, joinColumns={
			@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
			}
		)
	private List<FinalPackage> finalPackages;

	public Product() {
	}

	public int getIdPRODUCT() {
		return this.idPRODUCT;
	}

	public void setIdPRODUCT(int idPRODUCT) {
		this.idPRODUCT = idPRODUCT;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrom() {
		return this.depAirport;
	}

	public void setFrom(String depAirport) {
		this.depAirport = depAirport;
	}

	public int getLength() {
		return this.flightLength;
	}

	public void setLength(int length) {
		this.flightLength = length;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getPrice() {
		return this.price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTo() {
		return this.arrAirport;
	}

	public void setTo(String to) {
		this.arrAirport = to;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FinalExcursion> getFinalExcursions() {
		return this.finalExcursions;
	}

	public void setFinalExcursions(List<FinalExcursion> finalExcursions) {
		this.finalExcursions = finalExcursions;
	}

	public FinalExcursion addFinalExcursion(FinalExcursion finalExcursion) {
		getFinalExcursions().add(finalExcursion);
		finalExcursion.setProduct(this);

		return finalExcursion;
	}

	public FinalExcursion removeFinalExcursion(FinalExcursion finalExcursion) {
		getFinalExcursions().remove(finalExcursion);
		finalExcursion.setProduct(null);

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
		finalFlight.setProduct(this);

		return finalFlight;
	}

	public FinalFlight removeFinalFlight(FinalFlight finalFlight) {
		getFinalFlights().remove(finalFlight);
		finalFlight.setProduct(null);

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
		finalHotel.setProduct(this);

		return finalHotel;
	}

	public FinalHotel removeFinalHotel(FinalHotel finalHotel) {
		getFinalHotels().remove(finalHotel);
		finalHotel.setProduct(null);

		return finalHotel;
	}

	public List<Package> getPackages() {
		return this.packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public List<FinalPackage> getFinalPackages() {
		return this.finalPackages;
	}

	public void setFinalPackages(List<FinalPackage> finalPackages) {
		this.finalPackages = finalPackages;
	}

}