package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: SharedPackage
 *
 */
@Entity

public class SharedPackage implements Serializable {

	@OneToOne(mappedBy="sharedPackage")
	private FinalPackage finalPackage;   
	
	@Id
	private String uniqueIdentifier;
	private static final long serialVersionUID = 1L;

	public SharedPackage() {
		super();
	}   
	public FinalPackage getFinalPackage() {
		return this.finalPackage;
	}

	public void setFinalPackage(FinalPackage finalPackage) {
		this.finalPackage = finalPackage;
	}   
	public String getUniqueIdentifier() {
		return this.uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
   
}
