package usermanagement;

import javax.ejb.Local;

import usermanagement.dto.UserDTO;

@Local
public interface UserMgr {
	
	public void save(UserDTO user);
	
	public void update(UserDTO user);
	
	public void unregister();
	
	public UserDTO getUserDTO();

}
