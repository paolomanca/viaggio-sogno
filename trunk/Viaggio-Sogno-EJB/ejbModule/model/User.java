package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idUSER;

	@Column(nullable=false, length=45)
	private String email;

	@Column(name="`first name`", nullable=false, length=45)
	private String first_name;

	@Column(name="`last name`", nullable=false, length=45)
	private String last_name;

	@Column(nullable=false, length=45)
	private String password;

	//bi-directional many-to-one association to FinalPackage
	@OneToMany(mappedBy="user")
	private List<FinalPackage> finalPackages;

	//bi-directional many-to-one association to Package
	@OneToMany(mappedBy="user")
	private List<Package> packages;

	public User() {
	}

	public int getIdUSER() {
		return this.idUSER;
	}

	public void setIdUSER(int idUSER) {
		this.idUSER = idUSER;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
		finalPackage.setUser(this);

		return finalPackage;
	}

	public FinalPackage removeFinalPackage(FinalPackage finalPackage) {
		getFinalPackages().remove(finalPackage);
		finalPackage.setUser(null);

		return finalPackage;
	}

	public List<Package> getPackages() {
		return this.packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public Package addPackage(Package pkg) {
		getPackages().add(pkg);
		pkg.setUser(this);

		return pkg;
	}

	public Package removePackage(Package pkg) {
		getPackages().remove(pkg);
		pkg.setUser(null);

		return pkg;
	}

}