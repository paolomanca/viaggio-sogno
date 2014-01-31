package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.UserDTO;
import entitymanagers.UserMgr;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {
	
	@EJB
	private UserMgr userMgr;

	@ManagedProperty(value = "#{param.sharedID}")
	private String sharedID;
	
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
		
		if ( sharedID != null ) {
			return "customer/sharedPackage?sharedID=" + sharedID + "&amp;faces-redirect=true";
		} else {
			return "customer/index";
		}
	}
	
	public String registerEmployee() {
		userMgr.addEmployee(user);
		return "index?faces-redirect=true";
	}

	public String getSharedID() {
		return sharedID;
	}

	public void setSharedID(String sharedID) {
		this.sharedID = sharedID;
	}
}
