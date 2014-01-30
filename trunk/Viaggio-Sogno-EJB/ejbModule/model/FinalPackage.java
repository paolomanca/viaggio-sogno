package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalPackageDTO;

import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the FINAL_PACKAGE database table.
 * 
 */
@Entity
@Table(name="FINAL_PACKAGE")
@NamedQuery(name="FinalPackage.findAll", query="SELECT f FROM FinalPackage f")
public class FinalPackage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDFINAL_PACKAGE", unique=true, nullable=false)
	private int id;
	
	@Column(name="IDFINAL_PACKAGE_RELATIVE", nullable=false)
	private int idRelative;

	//uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_idUSER", nullable=false)
	private User user;
	
	//uni-directional many-to-one association to FinalProduct
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<FinalProduct> finalProducts;

	//uni-directional many-to-one association to Package
	@ManyToOne
	@JoinColumn(name="PACKAGE_idPACKAGE", nullable=false)
	private Package pkg;

	//uni-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(
		name="final_package_has_product"
		, joinColumns={
			@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
			}
		)
	private List<Product> products;
	
	private boolean shared = false;

	public FinalPackage() {
	}

	public FinalPackage(FinalPackageDTO finalPkgDTO) {
		this.id = finalPkgDTO.getId();
		this.pkg = new Package(finalPkgDTO.getOriginalPackage());
		this.products = new LinkedList<>();
	}

	public int getIdfinalPackage() {
		return this.id;
	}

	public void setIdfinalPackage(int idfinalPackage) {
		this.id = idfinalPackage;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Package getPackage() {
		return this.pkg;
	}

	public void setPackage(Package pkg) {
		this.pkg = pkg;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getIdfinalPackageRelative() {
		return idRelative;
	}

	public void setIdfinalPackageRelative(int idfinalPackageRelative) {
		this.idRelative = idfinalPackageRelative;
	}

	public List<FinalProduct> getFinalProducts() {
		return finalProducts;
	}

	public void setFinalProducts(List<FinalProduct> finalProducts) {
		this.finalProducts = finalProducts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FinalPackage))
			return false;
		FinalPackage other = (FinalPackage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

}