package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.FinalExcursionDTO;


@ManagedBean(name = "finalExcursionBean")
@RequestScoped
public class FinalExcursionBean extends FinalProductBean {


	@PostConstruct
	private void init() {
		if ( act == "create" ) {
			finalProduct = new FinalExcursionDTO();
			finalProduct.setProduct( prMgr.getByID(prID) );
			finalProduct.setFinalPackage(fPkgMgr.getByID(fPkgID));
		} else {
			finalProduct = fPrMgr.getByID(fPrID, "EXCURSION");
		}
	}

}
