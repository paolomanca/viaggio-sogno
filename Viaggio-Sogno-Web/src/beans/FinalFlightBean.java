package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.FinalFlightDTO;

@ManagedBean(name = "finalFlightBean")
@RequestScoped
public class FinalFlightBean extends FinalProductBean {


	@PostConstruct
	private void init() {
		if ( act == "create" ) {
			finalProduct = new FinalFlightDTO();
			finalProduct.setProduct( prMgr.getByID(prID) );
			finalProduct.setFinalPackage(fPkgMgr.getByID(fPkgID));
		} else {
			finalProduct = fPrMgr.getByID(fPrID, "FLIGHT");
		}
	}

}
