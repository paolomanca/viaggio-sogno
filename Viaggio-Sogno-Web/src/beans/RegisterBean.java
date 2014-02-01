package beans;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dto.UserDTO;
import entitymanagers.UserMgr;

@ManagedBean(name = "registerBean")
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

	public void register() {

		Map<String, String[]> param = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterValuesMap();

		userMgr.addCustomer(user);
		try {
			if (param.get("sharedID") != null) {
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"customer/sharedPackage.xhtml?sharedID="
										+ param.get("sharedID")[0]);

			} else {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("customer/index.xhtml");
			}
		} catch (IOException e) {
			e.printStackTrace();
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
