package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idEMPLOYEE;

	@Column(name="`first name`", nullable=false, length=45)
	private String first_name;

	@Column(nullable=false, length=45)
	private String password;

	//bi-directional many-to-one association to Package
	@OneToMany(mappedBy="employee")
	private List<Package> packages;

	public Employee() {
	}

	public int getIdEMPLOYEE() {
		return this.idEMPLOYEE;
	}

	public void setIdEMPLOYEE(int idEMPLOYEE) {
		this.idEMPLOYEE = idEMPLOYEE;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Package> getPackages() {
		return this.packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public Package addPackage(Package pkg) {
		getPackages().add(pkg);
		pkg.setEmployee(this);

		return pkg;
	}

	public Package removePackage(Package pkg) {
		getPackages().remove(pkg);
		pkg.setEmployee(null);

		return pkg;
	}

}