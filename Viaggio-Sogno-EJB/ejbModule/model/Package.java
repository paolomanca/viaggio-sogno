package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PACKAGE database table.
 * 
 */
@Entity
@Table(name="PACKAGE")
@NamedQuery(name="Package.findAll", query="SELECT p FROM Package p")
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idPACKAGE;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to FinalPackage
	@OneToMany(mappedBy="pkg")
	private List<FinalPackage> finalPackages;

	//bi-directional many-to-many association to Product
	@ManyToMany
	@JoinTable(
		name="PACKAGE_has_PRODUCT"
		, joinColumns={
			@JoinColumn(name="PACKAGE_idPACKAGE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PRODUCT_idPRODUCT", nullable=false)
			}
		)
	private List<Product> products;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_idUSER", nullable=false)
	private User user;

	public Package() {
	}

	public int getIdPACKAGE() {
		return this.idPACKAGE;
	}

	public void setIdPACKAGE(int idPACKAGE) {
		this.idPACKAGE = idPACKAGE;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FinalPackage> getFinalPackages() {
		return this.finalPackages;
	}

	public void setFinalPackages(List<FinalPackage> finalPackages) {
		this.finalPackages = finalPackages;
	}

	public FinalPackage addFinalPackage(FinalPackage finalPackage) {
		getFinalPackages().add(finalPackage);
		finalPackage.setPackage(this);

		return finalPackage;
	}

	public FinalPackage removeFinalPackage(FinalPackage finalPackage) {
		getFinalPackages().remove(finalPackage);
		finalPackage.setPackage(null);

		return finalPackage;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}