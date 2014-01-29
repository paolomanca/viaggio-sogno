package dto;

import java.util.Date;

public class FinalHotelDTO implements FinalProductDTO {

	private int id;
	
	private ProductDTO product;
	
	private Date checkIn;
	
	private Date checkOut;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public String getName() {
		return product.getName();
	}

	public int getPrice() {
		return product.getPrice();
	}

	public String getLocation() {
		return product.getLocation();
	}

	public String getDescription() {
		return product.getDescription();
	}

	public int getRating() {
		return product.getRating();
	}

	@Override
	public String getType() {
		return HOTEL;
	}
}
