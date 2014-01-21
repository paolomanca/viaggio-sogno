package dto;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import dto.ProductDTO;

public class FinalPackageDTO {
	
	@NotEmpty
	private int id;	
	
	@NotEmpty
    private String name;
    	
	private List<ProductDTO> products;

	
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

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}



}
