package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.UserDTO;

@Local
public interface UserMgr {
	
	public void addCustomer(UserDTO user);

	public void addEmployee(UserDTO user);
	
	public void update(UserDTO user);
	
	public void updateSelf(UserDTO user);
	
	public void unregister();
	
	public void remove(String email);
	
	public UserDTO findByEmailDTO(String email);
	
	public UserDTO getUserDTO();

}
