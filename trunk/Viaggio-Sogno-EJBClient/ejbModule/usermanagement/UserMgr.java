package usermanagement;

import javax.ejb.Local;

import usermanagement.dto.UserDTO;

@Local
public interface UserMgr {
	
	public void addCustomer(UserDTO user);

	public void addEmployee(UserDTO user);
	
	public void update(UserDTO user);
	
	public void updateSelf(UserDTO user);
	
	public void unregister();
	
	public void remove(String email);
	
	public UserDTO getUserDTO();

}
