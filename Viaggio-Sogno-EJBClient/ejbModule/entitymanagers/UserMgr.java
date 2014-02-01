package entitymanagers;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

import common.Constants;
import dto.UserDTO;

@Local
public interface UserMgr {
	
	public void addCustomer(UserDTO user);

	@RolesAllowed({Constants.Group.EMPLOYEE})
	public void addEmployee(UserDTO user);
	
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public void update(UserDTO user);
	
	@RolesAllowed({Constants.Group.CUSTOMER})
	public void updateSelf(UserDTO user);
	
	@RolesAllowed({Constants.Group.CUSTOMER})
	public void unregister();
	
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public void remove(String email);
	
	public UserDTO findByEmailDTO(String email);
	
	@RolesAllowed({Constants.Group.CUSTOMER, Constants.Group.EMPLOYEE})
	public UserDTO getUserDTO();
	
	public boolean isRole(String role);

	public String getPrincipal();

}
