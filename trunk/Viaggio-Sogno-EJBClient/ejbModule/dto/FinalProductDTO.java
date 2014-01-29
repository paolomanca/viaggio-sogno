package dto;

public interface FinalProductDTO {
	
	public String FLIGHT = "FLIGHT";
	public String HOTEL = "HOTEL";
	public String EXCURSION = "EXCURSION";

	public int getId();
	
	public void setId(int id);
	
	public ProductDTO getProduct();
	
	public void setProduct(ProductDTO product);
	
	public String getType();
	
}
