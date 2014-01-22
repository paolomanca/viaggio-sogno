package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.FinalPackageDTO;
import entitymanagers.FinalPackageMgr;

@ManagedBean(name="finalPackageBean")
@RequestScoped
public class FinalPackageBean {
	
	@EJB
	private FinalPackageMgr finalPackageMgr;
	
	private FinalPackageDTO finalPkg;
	
	
	public FinalPackageBean() {
		finalPkg = new FinalPackageDTO();
	}
	
}
