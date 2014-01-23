package dto;

import org.hibernate.validator.constraints.NotEmpty;

public class FinalPackageDTO  {
	
	private int id;	
    	
	@NotEmpty
	private PackageDTO originalPackage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public PackageDTO getOriginalPackage() {
		return originalPackage;
	}

	public void setOriginalPackage(PackageDTO originalPackage) {
		this.originalPackage = originalPackage;
	}

}
