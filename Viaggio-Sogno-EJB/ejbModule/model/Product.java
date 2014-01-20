package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.ProductDTO;

import java.math.BigInteger;
import java.util.LinkedList;
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
	
	public static final String FIND_ALL = "Product.findAll";

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

	public Product(ProductDTO p) {
		this.idPRODUCT = p.getId();
		this.arrAirport = p.getArrAirport();
		this.depAirport = p.getDepAirport();
		this.description = p.getDescription();
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
	
	public static List<ProductDTO> convertProductsToDTOs(List<Product> products) {
		List<ProductDTO> out = new LinkedList<>();
		for(Product p : products){
			out.add(convertProductToDTO(p));
		}
		return out;
	}

	private static ProductDTO convertProductToDTO(Product p) {
		ProductDTO out = new ProductDTO();
		out.setArrAirport(p.getArrAirport());
		out.setDepAirport(p.getDepAirport());
		out.setDescription(p.getDescription());
		out.setFlightLength(p.getFlightLength());
		out.setId(p.getIdPRODUCT());
		out.setLocation(p.getLocation());
		out.setName(p.getName());
		if ( p.getPrice() != null )
			out.setPrice(p.getPrice().intValue());
		out.setRating(p.getRating());
		out.setType(p.getType());
		return out;
	}

	public String getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}

	public int getFlightLength() {
		return flightLength;
	}

	public void setFlightLength(int flightLength) {
		this.flightLength = flightLength;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}

	public ProductDTO getDTO() {
		return convertProductToDTO(this);
	}     

}