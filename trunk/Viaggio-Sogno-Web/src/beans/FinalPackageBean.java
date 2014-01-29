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
		
		if ( prID > 0 ) {
			ProductDTO oldProduct = prMgr.getByID(prID);
			fPkgMgr.swap(fPkg, oldProduct, selectedProduct);
		}
		
		if ( fPrID > 0 ) {
			FinalProductDTO oldProduct = fPrMgr.getByID(fPrID);
			fPkgMgr.swap(fPkg, oldProduct, selectedProduct);
		}
		
		
		return "index/finalPackage?act=show&amp;fPkgID=" + fPkgID + "&amp;faces-redirect=true";
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
	
	private List<ProductDTO> filterProductByType(List<ProductDTO> products,
			String type) {
		List<ProductDTO> out = new LinkedList<>();
		for(ProductDTO pDTO : products){
			if(pDTO.getType().equals(type)){
				out.add(pDTO);
			}
		}
		return out;	
	}

	@SuppressWarnings("unchecked")
	private <T extends FinalProductDTO> List<T> filterFinalProductByType(
			List<FinalProductDTO> finalProducts, Class<T> type) {
		List<T> out = new LinkedList<>();
		for(FinalProductDTO pDTO : finalProducts){
			if(type.isInstance(pDTO)){
				out.add((T)pDTO);
			}
		}
		return out;		
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
		return filterProductByType(fPkg.getProducts(), FinalProductDTO.FLIGHT);
	}
	
	public List<FinalFlightDTO> getFinalFlights() {
		return filterFinalProductByType(fPkg.getFinalProducts(), FinalFlightDTO.class);
	}
	
	public List<ProductDTO> getHotels() {
		return filterProductByType(fPkg.getProducts(), FinalProductDTO.HOTEL);
	}
	
	public List<FinalHotelDTO> getFinalHotels() {
		return filterFinalProductByType(fPkg.getFinalProducts(), FinalHotelDTO.class);
	}
	
	public List<ProductDTO> getExcursions() {
		return filterProductByType(fPkg.getProducts(), FinalProductDTO.EXCURSION);
	}
	
	public List<FinalExcursionDTO> getFinalExcursions() {
		return filterFinalProductByType(fPkg.getFinalProducts(), FinalExcursionDTO.class);
	}

	public List<ProductDTO> getOptions(String type) {
		List<ProductDTO> out = new LinkedList<ProductDTO>();

		System.out.println(fPkg);
		
		List<ProductDTO> first = pkgMgr.listFirstChoicesByType(fPkg.getOriginalPackage(), type);
		List<ProductDTO> alter = pkgMgr.listAlternativesByType(fPkg.getOriginalPackage(), type);
		
		System.out.println(first.size()+" first choices");
		System.out.println(alter.size()+" alter choices");
		
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