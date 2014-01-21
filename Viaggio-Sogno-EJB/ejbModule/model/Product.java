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
	private int idproduct;

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

	public Product() {
	}

	public Product(ProductDTO p) {
		this.idproduct = p.getId();
		this.type = p.getType();
		this.description = p.getDescription();
		
		this.arrAirport = p.getArrAirport();
		this.depAirport = p.getDepAirport();
		this.flightLength = p.getFlightLength();
		
		this.name = p.getName();
		this.location = p.getLocation();
		this.price = BigInteger.valueOf(p.getPrice());
		this.rating = p.getRating();
		
	}

	public int getIdproduct() {
		return this.idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
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
		out.setId(p.getIdproduct());
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