package dto;

import java.util.Date;

public class FinalExcursionDTO implements FinalProductDTO {

	private int id;
	
	private ProductDTO product;
	
	private Date date;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	
	public String getName() {
		return product.getName();
	}
	
	public String getDescription() {
		return product.getDescription();
	}
	
	public String getLocation() {
		return product.getLocation();
	}

	@Override
	public String getType() {
		return EXCURSION;
	}
}
