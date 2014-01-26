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
	
	@ManagedProperty(value = "#{param.fPkgID}")
	private int fPkgID;

	private FinalPackageDTO pkg;
	
	@PostConstruct
	public void init() {
		
		if( fPkgID <= 0 ) {
			pkg = new FinalPackageDTO();
		
		} else {
			pkg = finalPackageMgr.getByID(fPkgID);
		}

	}
	
	public String add(){	
		finalPackageMgr.add(pkg);
		return "index?faces-redirect=true";
	}
	
	public String remove(FinalPackageDTO finalPkg) {
		finalPackageMgr.remove(finalPkg);

		return "index?faces-redirect=true";
	}
	
	public void removeFlight( ProductDTO flight ) {
		pkg.removeFlight(flight);
		finalPackageMgr.update(pkg);
	}
	
	
	public void removeHotel( ProductDTO hotel ) {
		System.out.println("Paolo: " + hotel);
		System.out.println("Paolo: " + pkg.getHotels());
		pkg.removeHotel(hotel);
		System.out.println("Paolo: " + pkg.getHotels());
		
		finalPackageMgr.update(pkg);
	}
	
	public void removeExcursion( ProductDTO excursion ) {
		pkg.removeExcursion(excursion);
		finalPackageMgr.update(pkg);
	}
	
	public void removeFinalProduct( FinalFlightDTO finalFlight ) {
		pkg.removeFinalProduct(finalFlight);
		finalPackageMgr.update(pkg);
	}

	public void removeFinalProduct( FinalHotelDTO finalHotel ) {
		pkg.removeFinalProduct(finalHotel);
		finalPackageMgr.update(pkg);
	}
	
	public void removeFinalProduct( FinalExcursionDTO finalExcursion ) {
		pkg.removeFinalProduct(finalExcursion);
		finalPackageMgr.update(pkg);
	}
	
	public int getfPkgID() {
		return fPkgID;
	}

	public void setfPkgID(int id) {
		this.fPkgID = id;
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

	public List<FinalPackageDTO> getOwn() {
		return finalPackageMgr.listByUser();
	}

}
