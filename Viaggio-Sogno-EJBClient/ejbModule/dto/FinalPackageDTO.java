package dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class FinalPackageDTO {
	
	private int id;	
    	
	@NotEmpty
	private PackageDTO originalPackage;
	
	private List<ProductDTO> products;
	
	private List<FinalProductDTO> finalProducts;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public PackageDTO getOriginalPackage() {
		return originalPackage;
	}

	public void setOriginalPackage(PackageDTO originalPackage) {
		this.originalPackage = originalPackage;
	}



}
