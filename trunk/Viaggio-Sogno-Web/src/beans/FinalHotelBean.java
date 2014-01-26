package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.FinalHotelDTO;

@ManagedBean(name = "finalHotelBean")
@RequestScoped
public class FinalHotelBean extends FinalProductBean {


	@PostConstruct
	private void init() {
		if ( act == "create" ) {
			finalProduct = new FinalHotelDTO();
			finalProduct.setProduct( prMgr.getByID(prID) );
			finalProduct.setFinalPackage(fPkgMgr.getByID(fPkgID));
		} else {
			finalProduct = fPrMgr.getByID(fPrID, "HOTEL");
		}
	}

}
