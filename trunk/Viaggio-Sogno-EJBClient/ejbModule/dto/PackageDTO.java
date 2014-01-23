package dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PackageDTO {
	
	private int id;	
	
	@NotEmpty
    private String name;

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

}
