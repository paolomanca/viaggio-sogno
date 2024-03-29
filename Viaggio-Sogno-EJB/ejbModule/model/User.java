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
	private int iduser;

	@Column(unique=true, nullable=false, length=45)
	private String email;

	@Column(name="FIRST_NAME", nullable=false, length=45)
	private String firstName;

	@Column(name="LAST_NAME", nullable=false, length=45)
	private String lastName;

	@Column(nullable=false, length=128)
	private String password;

	//bi-directional many-to-one association to FinalPackage
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<FinalPackage> finalPackages;

	//bi-directional many-to-one association to FinalProduct
	@OneToMany(mappedBy="user")
	private List<FinalProduct> finalProducts;
	
	@ElementCollection(targetClass = Group.class)
	@CollectionTable(	name = "USER_GROUP",
						joinColumns = @JoinColumn(name = "email", referencedColumnName = "EMAIL"))
	@Enumerated(EnumType.STRING)
	@Column(name="groupname")
	private List<Group> groups;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique=true, nullable=false)
	private IdPool idPool = new IdPool();
	
	public User(){
		
	}

	public User(UserDTO user) {
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = DigestUtils.sha512Hex(user.getPassword());
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
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

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public int nextID(){
		return idPool.nextAvailable();
	}
	
	public void freeID(int toBeFreed){
		idPool.free(toBeFreed);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iduser;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (iduser != other.iduser)
			return false;
		return true;
	}

	public List<FinalProduct> getFinalProducts() {
		return finalProducts;
	}

	public void setFinalProducts(List<FinalProduct> finalProducts) {
		this.finalProducts = finalProducts;
	}

}