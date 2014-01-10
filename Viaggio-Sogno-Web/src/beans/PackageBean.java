package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.PackageDTO;
import entitymanagers.PackageMgr;

@ManagedBean(name="packageBean")
@RequestScoped
public class PackageBean {
	
	@EJB
	private PackageMgr packageMgr;
	
	public List<PackageDTO> getShowcased() {
		return packageMgr.listShowcasePackages();
	}
	
}
