package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import dto.PackageDTO;

/**
 * The persistent class for the PACKAGE database table.
 * 
 */
@Entity
@NamedQuery(name="Package.findAll", query="SELECT p FROM Package p")
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Package.findAll";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idpackage;

	@Column(nullable=false, length=45)
	private String name;

	@Column(nullable=false)
	private boolean showcased;

	//uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_idUSER", nullable=false)
	private User user;

	//bi-directional one-to-many association to PackageHasProduct
	@OneToMany(mappedBy="pkg", cascade = CascadeType.ALL)
	private List<PackageHasProduct> packageHasProducts = new LinkedList<>();

	public Package() {		
	}

	public Package(PackageDTO pkg) {
		setName(pkg.getName());
		setShowcased(pkg.isShowcased());
	}

	public int getIdpackage() {
		return this.idpackage;
	}

	public void setIdpackage(int idpackage) {
		this.idpackage = idpackage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isShowcased() {
		return showcased;
	}

	public void setShowcased(boolean showcased) {
		this.showcased = showcased;
	}
	
	public List<PackageHasProduct> getPackageHasProducts() {
		return this.packageHasProducts;
	}

	public void setPackageHasProducts(List<PackageHasProduct> packageHasProducts) {
		this.packageHasProducts = packageHasProducts;
	}

	public PackageHasProduct addPackageHasProduct(PackageHasProduct packageHasProduct) {
		getPackageHasProducts().add(packageHasProduct);
		packageHasProduct.setPackage(this);

		return packageHasProduct;
	}

	public PackageHasProduct removePackageHasProduct(PackageHasProduct packageHasProduct) {
		getPackageHasProducts().remove(packageHasProduct);
		packageHasProduct.setPackage(null);

		return packageHasProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idpackage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Package))
			return false;
		Package other = (Package) obj;
		if (idpackage != other.idpackage)
			return false;
		return true;
	}

	
}