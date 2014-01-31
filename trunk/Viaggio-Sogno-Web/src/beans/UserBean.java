package beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
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

	public String getName() {
		return user.getFirstName();
	}

	public void unregister() {
		usrMgr.unregister();
		logout();
	}

	public void logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		
		ec.invalidateSession();
        try {
			ec.redirect(ec.getRequestContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String update() {
		usrMgr.updateSelf(user);
		return "costumer/index?faces-redirect=true";
	}

}
