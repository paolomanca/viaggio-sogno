package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the package_has_product database table.
 * 
 */
@Embeddable
public class PackageHasProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, nullable=false)
	private int PACKAGE_idPACKAGE;

	@Column(insertable=false, updatable=false, nullable=false)
	private int PRODUCT_idPRODUCT;

	public PackageHasProductPK() {
	}
	public int getPACKAGE_idPACKAGE() {
		return this.PACKAGE_idPACKAGE;
	}
	public void setPACKAGE_idPACKAGE(int PACKAGE_idPACKAGE) {
		this.PACKAGE_idPACKAGE = PACKAGE_idPACKAGE;
	}
	public int getPRODUCT_idPRODUCT() {
		return this.PRODUCT_idPRODUCT;
	}
	public void setPRODUCT_idPRODUCT(int PRODUCT_idPRODUCT) {
		this.PRODUCT_idPRODUCT = PRODUCT_idPRODUCT;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PackageHasProductPK)) {
			return false;
		}
		PackageHasProductPK castOther = (PackageHasProductPK)other;
		return 
			(this.PACKAGE_idPACKAGE == castOther.PACKAGE_idPACKAGE)
			&& (this.PRODUCT_idPRODUCT == castOther.PRODUCT_idPRODUCT);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.PACKAGE_idPACKAGE;
		hash = hash * prime + this.PRODUCT_idPRODUCT;
		
		return hash;
	}
}