package model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.codec.digest.DigestUtils;

import dto.UserDTO;

import java.util.List;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER_")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "User.findAll";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idUSER;

	@Column(unique=true, nullable=false, length=45)
	private String email;

	@Column(name="FIRST_NAME", nullable=false, length=45)
	private String firstName;

	@Column(name="LAST_NAME", nullable=false, length=45)
	private String lastName;

	@Column(nullable=false, length=128)
	private String password;

	//bi-directional many-to-one association to FinalPackage
	@OneToMany(mappedBy="user")
	private List<FinalPackage> finalPackages;

	//bi-directional many-to-one association to Package
	@OneToMany(mappedBy="user")
	private List<Package> packages;
	
	@ElementCollection(targetClass = Group.class)
	@CollectionTable(	name = "USER_GROUP",
						joinColumns = @JoinColumn(name = "email", referencedColumnName = "EMAIL"))
	@Enumerated(EnumType.STRING)
	@Column(name="groupname")
	private List<Group> groups;
	
	public User(){
		
	}

	public User(UserDTO user) {
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = DigestUtils.sha512Hex(user.getPassword());
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
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

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}