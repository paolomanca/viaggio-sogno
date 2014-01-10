package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import usermanagement.UserMgr;

@ManagedBean
@RequestScoped
public class UserBean {

	@EJB
	private UserMgr usrMgr;
	
	public String getName(){
		return usrMgr.getUserDTO().getEmail();
	}
	
}
