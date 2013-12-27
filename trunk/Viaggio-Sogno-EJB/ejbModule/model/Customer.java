package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idCUSTOMER;

	@Column(nullable=false, length=45)
	private String email;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(nullable=false, length=45)
	private String password;

	//bi-directional many-to-one association to FinalPackage
	@OneToMany(mappedBy="customer")
	private List<FinalPackage> finalPackages;

	public Customer() {
	}

	public int getIdCUSTOMER() {
		return this.idCUSTOMER;
	}

	public void setIdCUSTOMER(int idCUSTOMER) {
		this.idCUSTOMER = idCUSTOMER;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<FinalPackage> getFinalPackages() {
		return this.finalPackages;
	}

	public void setFinalPackages(List<FinalPackage> finalPackages) {
		this.finalPackages = finalPackages;
	}

	public FinalPackage addFinalPackage(FinalPackage finalPackage) {
		getFinalPackages().add(finalPackage);
		finalPackage.setCustomer(this);

		return finalPackage;
	}

	public FinalPackage removeFinalPackage(FinalPackage finalPackage) {
		getFinalPackages().remove(finalPackage);
		finalPackage.setCustomer(null);

		return finalPackage;
	}

}