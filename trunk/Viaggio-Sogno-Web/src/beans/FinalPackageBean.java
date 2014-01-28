package beans;

import java.util.LinkedList;
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
import dto.FinalProductDTO;
import dto.ProductDTO;
import entitymanagers.FinalPackageMgr;
import entitymanagers.FinalProductMgr;
import entitymanagers.PackageMgr;
import entitymanagers.ProductMgr;

@ManagedBean(name="finalPackageBean")
@RequestScoped
public class FinalPackageBean {
	
	@EJB
	private ProductMgr prMgr;
	
	@EJB
	private FinalProductMgr fPrMgr;
	
	@EJB
	private PackageMgr pkgMgr;
	
	@EJB
	private FinalPackageMgr fPkgMgr;
	
	@ManagedProperty(value = "#{param.fPkgID}")
	private int fPkgID;
	
	@ManagedProperty(value = "#{param.prID}")
	private int prID;
	
	@ManagedProperty(value = "#{param.fPrID}")
	private int fPrID;
	
	@ManagedProperty(value = "#{param.act}")
	private String act;

	private FinalPackageDTO fPkg;
	
	private ProductDTO selectedProduct;
	
	@PostConstruct
	public void init() {
		
		if ( act != null ) {
			
			if ( act.equalsIgnoreCase("create") ) {
				fPkg = new FinalPackageDTO();
			} else {
				fPkg = fPkgMgr.getByID(fPkgID);
			}
			
		}
		
	}
	
	public String add(){	
		fPkgMgr.add(fPkg);
		return "index?faces-redirect=true";
	}
	
	public String swap() {
		
		if ( prID >= 0 ) {
			ProductDTO oldProduct = prMgr.getByID(prID);
			fPkgMgr.swap(fPkg, oldProduct, selectedProduct);
		}
		
		if ( fPrID >= 0 ) {
			FinalProductDTO oldProduct = fPrMgr.getByID(prID);
			fPkgMgr.swap(oldProduct, selectedProduct);
		}
		
		
		return "index/finalPackage?act=show&fPkgID=" + fPkgID;
	}
	
	public String remove(FinalPackageDTO finalPkg) {
		fPkgMgr.remove(finalPkg);

		return "index?faces-redirect=true";
	}
	
	
	public void removeProduct( ProductDTO product ) {
		fPkg.removeProduct(product);
		fPkgMgr.update(fPkg);
	}
	
	public void removeFinalProduct( FinalProductDTO finalProduct ) {
		System.out.println(finalProduct);
		fPkg.removeFinalProduct(finalProduct);
		fPkgMgr.update(fPkg);
	}
	
	public int getfPkgID() {
		return fPkgID;
	}

	public void setfPkgID(int id) {
		this.fPkgID = id;
	}
	

	public FinalPackageDTO getFinalPackage() {
		return fPkg;
	}
	
	public List<ProductDTO> getFlights() {
		return fPkg.getFlights();
	}
	
	public List<FinalFlightDTO> getFinalFlights() {
		return fPkg.getFinalFlights();
	}
	
	public List<ProductDTO> getHotels() {
		return fPkg.getHotels();
	}
	
	public List<FinalHotelDTO> getFinalHotels() {
		return fPkg.getFinalHotels();
	}
	
	public List<ProductDTO> getExcursions() {
		return fPkg.getExcursions();
	}
	
	public List<FinalExcursionDTO> getFinalExcursions() {
		return fPkg.getFinalExcursions();
	}

	public List<ProductDTO> getOptions(String type) {
		List<ProductDTO> out = new LinkedList<ProductDTO>();

		System.out.println(fPkg);
		
		List<ProductDTO> first = pkgMgr.listFirstChoicesByType(fPkg.getOriginalPackage(), type);
		List<ProductDTO> alter = pkgMgr.listAlternativesByType(fPkg.getOriginalPackage(), type);
		
		out.addAll(first);
		out.addAll(alter);
		
		return out;
	}
	
	public List<ProductDTO> getOptionsFlight() {
		return getOptions("FLIGHT");
	}
	
	public List<ProductDTO> getOptionsHotel() {
		return getOptions("HOTEL");
	}
	
	public List<ProductDTO> getOptionsExcursion() {
		return getOptions("EXCURSION");
	}
	
	public List<FinalPackageDTO> getOwn() {
		return fPkgMgr.listByUser();
	}

	public int getPrID() {
		return prID;
	}

	public void setPrID(int prID) {
		this.prID = prID;
	}

	public int getfPrID() {
		return fPrID;
	}

	public void setfPrID(int fPrID) {
		this.fPrID = fPrID;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public ProductDTO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductDTO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

}
