package model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import dto.ProductDTO;


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

	public static final String FLIGHT = "FLIGHT";
	public static final String HOTEL = "HOTEL";
	public static final String EXCURSION = "EXCURSION";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idproduct;

	@Column(length=600)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idproduct;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (idproduct != other.idproduct)
			return false;
		return true;
	}

}