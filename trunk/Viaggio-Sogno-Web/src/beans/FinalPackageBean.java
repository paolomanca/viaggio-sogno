package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.FinalPackageDTO;
import dto.FinalProductDTO;
import dto.PackageDTO;
import dto.ProductDTO;
import entitymanagers.FinalPackageMgr;
import entitymanagers.PackageMgr;

@ManagedBean(name="finalPackageBean")
@RequestScoped
public class FinalPackageBean {
	
	private static final String FLIGHT = "flight";
	private static final String HOTEL = "hotel";
	private static final String EXCURSION = "excursion";
	
	@EJB
	private FinalPackageMgr finalPackageMgr;
	
	private FinalPackageDTO pkg;
	
	@ManagedProperty(value = "#{param.id}")
	private int id;
	
	public FinalPackageBean() {

	}
	
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
		return finalPackageMgr.listProducts(pkg, FLIGHT);
	}
	
	public List<FinalProductDTO> getFinalFlights() {
		return finalPackageMgr.listFinalProducts(pkg, FLIGHT);
	}


}
