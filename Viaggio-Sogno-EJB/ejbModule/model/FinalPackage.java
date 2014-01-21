package model;

import java.io.Serializable;
import javax.persistence.*;
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
	private int idfinalPackage;

	//uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_idUSER", nullable=false)
	private User user;

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

	public FinalPackage() {
	}

	public int getIdfinalPackage() {
		return this.idfinalPackage;
	}

	public void setIdfinalPackage(int idfinalPackage) {
		this.idfinalPackage = idfinalPackage;
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

}