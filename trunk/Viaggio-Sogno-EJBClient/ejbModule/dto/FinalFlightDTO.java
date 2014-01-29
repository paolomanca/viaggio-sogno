package dto;

import java.util.Date;

public class FinalFlightDTO implements FinalProductDTO {

	private int id;
	
	private Date departure;
	
	private ProductDTO product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
	public int getPrice() {
		return product.getPrice();
	}
	
	public String getDepAirport() {
		return product.getDepAirport();
	}
	
	public String getArrAirport() {
		return product.getArrAirport();
	}
	
	public int getFlightLength() {
		return product.getFlightLength();
	}

	@Override
	public String getType() {
		return FLIGHT;
	}
	
}
