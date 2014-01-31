package beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entitymanagers.UserMgr;

@ManagedBean
@RequestScoped
public class RedirectBean {

	@EJB
	private UserMgr uMgr;

	public String go() {

		String remoteUser = FacesContext.getCurrentInstance()
				.getExternalContext().getRemoteUser();
		
		System.out.println("Redirecting...");
		
		try {

			if (remoteUser == null) {

				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("login.xhtml");

			} else if (uMgr.isRole(common.Constants.Group.CUSTOMER)) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("customer/index.xhtml");
			} else if (uMgr.isRole(common.Constants.Group.EMPLOYEE)) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("employee/index.xhtml");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}
