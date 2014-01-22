package dto;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class PackageDTO {
	
	private int id;	
	
	@NotEmpty
    private String name;
    	
	private List<ProductDTO> firstChoices = new LinkedList<>();
	
	private List<ProductDTO> alternatives = new LinkedList<>();

	private boolean showcased;

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

	public boolean isShowcased() {
		return showcased;
	}

	public void setShowcased(boolean showcased) {
		this.showcased = showcased;
	}

	public List<ProductDTO> getFirstChoices() {
		return firstChoices;
	}

	public void setFirstChoices(List<ProductDTO> firstChoices) {
		this.firstChoices = firstChoices;
	}
	

	public List<ProductDTO> getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(List<ProductDTO> alternatives) {
		this.alternatives = alternatives;
	}

}
