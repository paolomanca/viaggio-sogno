package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.PackageDTO;
import dto.ProductDTO;

import java.util.LinkedList;
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
	
	public static final String FIND_ALL = "Package.findAll";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idPACKAGE;

	@Column(nullable=false, length=45)
	private String name;
	
	
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

	@Column(nullable=false)
	private boolean showcased;

	private List<Product> getProductsFromDTOs(List<ProductDTO> productDTOs) {
		List<Product> out = new LinkedList<>();
		for(ProductDTO p : productDTOs){
			out .add(new Product(p));
		}
		return out;
	}
	
	public Package() {
		
	}

	public Package(PackageDTO pkg) {
		setName(pkg.getName());
		setProducts(getProductsFromDTOs(pkg.getProducts()));
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

	public boolean isShowcased() {
		return showcased;
	}

	public void setShowcased(boolean showcased) {
		this.showcased = showcased;
	}

}