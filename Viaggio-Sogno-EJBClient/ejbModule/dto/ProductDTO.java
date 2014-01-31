package dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductDTO {
	
	public final static String FLIGHT = "FLIGHT";
	public final static String HOTEL = "HOTEL";
	public final static String EXCURSION = "EXCURSION";
	
	private int id;
	
	@NotEmpty
    private String name;
	
    private int price;

	@NotEmpty
    private String type;
	
	@NotEmpty
    private String description;
	
    private String arrAirport;
	
    private String depAirport;

	private int flightLength;

	private String location;

	private int rating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProductDTO))
			return false;
		ProductDTO other = (ProductDTO) obj;
		System.out.println("this ID: "+id+" other ID: "+ other.id);
		if (id != other.id)
			return false;
		return true;
	}
	
}
