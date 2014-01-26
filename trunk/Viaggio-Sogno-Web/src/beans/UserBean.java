package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dto.UserDTO;
import entitymanagers.UserMgr;

@ManagedBean
@RequestScoped
public class UserBean {

	@EJB
	private UserMgr usrMgr;
	
	private UserDTO user;
	
	@PostConstruct
	public void init() {
		user = usrMgr.getUserDTO();
	}
	
	public UserDTO getUser() {
		return user;
	}
	
	public String getName(){
		return user.getFirstName();
	}
	
	public String unregister(){
		usrMgr.unregister();
		return logout();
	}
	
	public String logout() {
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/index.xhtml";
	}
	
	public String update(){
		usrMgr.updateSelf(user);
		return "/user/index?faces-redirect=true";
	}
	
}
