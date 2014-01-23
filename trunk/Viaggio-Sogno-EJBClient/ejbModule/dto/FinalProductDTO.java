package dto;

public interface FinalProductDTO {
	
	public int getId();
	
	public void setId(int id);
	
	public ProductDTO getProduct();
	
	public void setProduct(ProductDTO product);

	public abstract void setFinalPackage(FinalPackageDTO finalPackage);

	public abstract FinalPackageDTO getFinalPackage();
	
}
