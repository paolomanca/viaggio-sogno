package dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class PackageDTO {
	
	@NotEmpty
	private int id;	
	
	@NotEmpty
    private String name;
    
	@NotEmpty
	private boolean showcased;
	
	private List<ProductDTO> products;

	public String getName() {
        return name;
    }
     
    public void setName(String name) {
        this.name = name;
    }

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isShowcased() {
		return showcased;
	}

	public void setShowcased(boolean showcased) {
		this.showcased = showcased;
	}

}
