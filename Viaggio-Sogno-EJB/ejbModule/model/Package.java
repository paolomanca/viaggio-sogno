package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the package database table.
 * 
 */
@Entity
@Table(name="package")
@NamedQuery(name="Package.findAll", query="SELECT p FROM Package p")
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idPACKAGE;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to FinalPackage
	@OneToMany(mappedBy="pkg")
	private List<FinalPackage> finalPackages;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_idEMPLOYEE", nullable=false)
	private Employee employee;

	//bi-directional many-to-many association to Product
	@ManyToMany(mappedBy="packages")
	private List<Product> products;

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

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}