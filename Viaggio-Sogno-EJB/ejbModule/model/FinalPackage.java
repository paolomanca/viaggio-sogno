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
@NamedQuery(name="FinalPackage.findAll", query="SELECT f FROM FinalPackage f")
public class FinalPackage implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "FinalPackage.findAll";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDFINAL_PACKAGE", unique=true, nullable=false)
	private int id;
	
	@Column(name="IDFINAL_PACKAGE_RELATIVE", nullable=false)
	private int idRelative;
	
	@OneToOne(cascade=CascadeType.ALL)
	private SharedPackage sharedPackage;

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

	private int numberOfPartecipants = 1;
	
	//uni-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(	joinColumns={
			@JoinColumn(name="FINAL_PACKAGE_idFINAL_PACKAGE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
			}
		)
	private List<Product> products;
	
	private boolean shared = false;
	private boolean finalized = false;
	private boolean paid = false;
	private boolean reserved = false;

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

	public SharedPackage getSharedPackage() {
		return sharedPackage;
	}

	public void setSharedPackage(SharedPackage sharedPackage) {
		this.sharedPackage = sharedPackage;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public int getNumberOfPartecipants() {
		return numberOfPartecipants;
	}

	public void setNumberOfPartecipants(int numberOfPartecipants) {
		this.numberOfPartecipants = numberOfPartecipants;
	}
	
	public int getTotalCost(){
		return singleCost()*numberOfPartecipants;
	}

	private int singleCost() {
		int out = 0;
		for(FinalProduct fProduct : getFinalProducts()){
			out += fProduct.getProduct().getPrice();
		}
		for(Product product : getProducts()){
			out += product.getPrice();
		}
		return out;
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

}