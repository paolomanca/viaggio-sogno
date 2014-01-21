package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.UserDTO;
import entitymanagers.UserMgr;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {
	
	@EJB
	private UserMgr userMgr;

	private UserDTO user;
	
	public RegisterBean() {
		user = new UserDTO();
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String register() {
		userMgr.addCustomer(user);
		return "index?faces-redirect=true";
	}
}
