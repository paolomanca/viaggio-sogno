package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
		System.out.println("Ciao");
		userMgr.addCustomer(user);
		
		if ( sharedID != null ) {
			return "customer/sharedPackage?sharedID=" + sharedID + "&amp;faces-redirect=true";
		} else {
			return "customer/index";
		}
	}

	public String getSharedID() {
		return sharedID;
	}

	public void setSharedID(String sharedID) {
		this.sharedID = sharedID;
	}
}
