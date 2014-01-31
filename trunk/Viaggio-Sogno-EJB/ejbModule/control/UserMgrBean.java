package control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Group;
import model.User;

import org.apache.commons.codec.digest.DigestUtils;

import common.Constants;

import dto.UserDTO;
import entitymanagers.UserMgr;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserMgrBean implements UserMgr {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;


	@Override
	public void addCustomer(UserDTO user) {
		User newUser = new User(user);
		List<Group> groups = new ArrayList<Group>();
		groups.add(Group.CUSTOMER);
		newUser.setGroups(groups);
		em.persist(newUser);
	}


	@Override
	public void addEmployee(UserDTO user) {
		User newUser = new User(user);
		List<Group> groups = new ArrayList<Group>();
		groups.add(Group.EMPLOYEE);
		newUser.setGroups(groups);
		em.persist(newUser);
	}


	@Override
	public void updateSelf(UserDTO user) {
		getPrincipalUser().setPassword(DigestUtils.sha512Hex(user.getPassword()));
		getPrincipalUser().setFirstName(user.getFirstName());
		getPrincipalUser().setLastName(user.getLastName());
		// TODO
		/*getPrincipalUser().setEmail(user.getEmail());*/
	}
	

	@Override
	public void update(UserDTO user) {
		em.merge(new User(user));
	}


	@Override
	public UserDTO getUserDTO() {
		UserDTO userDTO = buildDTO(getPrincipalUser());
		return userDTO;
	}


	@Override
	public void remove(String email) {
		remove(findByEmail(email));
	}


	public User findByEmail(String email) {
		return em.createQuery("SELECT t FROM User t where t.email = :email", User.class)
				.setParameter("email", email).getSingleResult();
	}


	@Override
	public UserDTO findByEmailDTO(String email) {
		return buildDTO(findByEmail(email));
	}


	@Override
	public void unregister() {
		remove(getPrincipalUser());
	}


	public User find(int ID) {
		return em.find(User.class, ID);
	}


	public List<User> getAllUsers() {
		return em.createNamedQuery(User.FIND_ALL, User.class)
				.getResultList();
	}


	public void remove(int ID) {
		User user = find(ID);
		em.remove(user);
	}


	public void remove(User user) {
		em.remove(user);
	}


	public User getPrincipalUser() {
		return find(getPrincipalID());
	}


	private int getPrincipalID() {
		return findByEmail(getPrincipalEmail()).getIduser();
	}


	public String getPrincipalEmail() {
		return context.getCallerPrincipal().getName();
	}

	public UserDTO buildDTO(User in) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(in.getEmail());
		userDTO.setFirstName(in.getFirstName());
		userDTO.setLastName(in.getLastName());
		return userDTO;
	}

	@Override
	@RolesAllowed({Constants.Group.CUSTOMER, Constants.Group.EMPLOYEE})
	public boolean isRole(String role) {
		return context.isCallerInRole(role);
	}
}
