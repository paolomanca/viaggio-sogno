package model;

import java.io.Serializable;

import javax.persistence.*;

import model.Package;
import model.Product;


/**
 * The persistent class for the package_has_product database table.
 * 
 */
@Entity
@Table(name="package_has_product")
@NamedQuery(name="PackageHasProduct.findAll", query="SELECT p FROM PackageHasProduct p")
public class PackageHasProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "PackageHasProduct.findAll";

	@EmbeddedId
	private PackageHasProductPK id;

	@Column(name="FIRST_CHOICE", nullable=false)
	private boolean firstChoice;

	//bi-directional many-to-one association to Package
	@ManyToOne
	@JoinColumn(name="PACKAGE_idPACKAGE", nullable=false)
	private Package pkg;

	//uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
	private Product product;

	public PackageHasProduct() {
	}

	public PackageHasProduct(Package pkg, Product product) {
		this.pkg = pkg;
		this.product = product;
		this.id = new PackageHasProductPK();
		this.id.setPACKAGE_idPACKAGE(pkg.getIdpackage());
		this.id.setPRODUCT_idPRODUCT(product.getIdproduct());
	}

	public PackageHasProductPK getId() {
		return this.id;
	}

	public void setId(PackageHasProductPK id) {
		this.id = id;
	}

	public boolean isFirstChoice() {
		return this.firstChoice;
	}

	public void setFirstChoice(boolean firstChoice) {
		this.firstChoice = firstChoice;
	}

	public Package getPackage() {
		return this.pkg;
	}

	public void setPackage(Package pkg) {
		this.pkg = pkg;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}