package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.FinalExcursionDTO;
import dto.FinalFlightDTO;
import dto.FinalHotelDTO;
import dto.FinalPackageDTO;
import dto.ProductDTO;
import entitymanagers.FinalPackageMgr;

@ManagedBean(name="finalPackageBean")
@RequestScoped
public class FinalPackageBean {
	
	@EJB
	private FinalPackageMgr finalPackageMgr;
	
	private FinalPackageDTO pkg;
	
	@ManagedProperty(value = "#{param.id}")
	private int id;
	
	
	@PostConstruct
	public void init() {
		
		if( id <= 0 )
			pkg = new FinalPackageDTO();
		else 
			pkg = finalPackageMgr.getByID(id);

	}
	
	public String add(){	
		finalPackageMgr.add(pkg);
		return "index?faces-redirect=true";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public FinalPackageDTO getFinalPackage() {
		return pkg;
	}
	
	public List<ProductDTO> getFlights() {
		return pkg.getFlights();
	}
	
	public List<FinalFlightDTO> getFinalFlights() {
		return pkg.getFinalFlights();
	}
	
	public List<ProductDTO> getHotels() {
		return pkg.getHotels();
	}
	
	public List<FinalHotelDTO> getFinalHotels() {
		return pkg.getFinalHotels();
	}
	
	public List<ProductDTO> getExcursions() {
		return pkg.getExcursions();
	}
	
	public List<FinalExcursionDTO> getFinalExcursions() {
		return pkg.getFinalExcursions();
	}


}
