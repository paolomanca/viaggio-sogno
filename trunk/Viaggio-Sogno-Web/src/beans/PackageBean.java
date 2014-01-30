package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.FinalPackageDTO;
import dto.PackageDTO;
import dto.ProductDTO;
import entitymanagers.FinalPackageMgr;
import entitymanagers.PackageMgr;

@ManagedBean(name="packageBean")
@RequestScoped
public class PackageBean {
	
	@EJB
	private PackageMgr pkgMgr;
	
	@EJB
	private FinalPackageMgr fPkgMgr;
	
	@ManagedProperty(value = "#{param.act}")
	protected String act;
	
	@ManagedProperty(value = "#{param.pkgID}")
	protected int pkgID;
	
	private PackageDTO pkg;

	@PostConstruct
	private void init() {
		if ( act != null ) {			
			if( act.equalsIgnoreCase("create") ) {
				pkg = new PackageDTO();
				System.out.println("fix!"); // TODO WTF!?
			} else {
				pkg = pkgMgr.getByID(pkgID);
			}
		}
	}

	public String finalizePackage() {
		FinalPackageDTO fPkg = fPkgMgr.finalizePackage(pkgMgr.getByID(pkgID));
		return "finalPackage?act=show&amp;fPkgID =" + fPkg.getId()
				+ "&amp;faces-redirect=true";
	}
	
	public String add(){	
		pkgMgr.add(pkg);
		return "index?faces-redirect=true";
	}
	
	public void remove(PackageDTO rs) {
		pkgMgr.remove(rs);
	}
	
	public String update() {
		pkgMgr.update(pkg);
		return "index";
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public int getPkgID() {
		return pkgID;
	}

	public void setPkgID(int pkgID) {
		this.pkgID = pkgID;
	}

	public PackageDTO getPackage() {
		return pkg;
	}
	
	
	public List<ProductDTO> getFirstFlight() {
		return pkgMgr.listFirstChoicesByType(pkg, ProductDTO.FLIGHT );
	}
		
	
	public void setFirstFlight( List<ProductDTO> products ) {
		System.out.println(products);

		pkg.getFirstChoices().addAll(products);
	}

	
	public List<ProductDTO> getFirstHotel() {
		return pkgMgr.listFirstChoicesByType(pkg, ProductDTO.HOTEL);
	}
		
	
	public void setFirstHotel( List<ProductDTO> products ) {
		pkg.getFirstChoices().addAll(products);
	}
	

	public List<ProductDTO> getFirstExcursion() {
		return pkgMgr.listFirstChoicesByType(pkg, ProductDTO.EXCURSION);
	}
	
	
	public void setFirstExcursion( List<ProductDTO> products ) {
		pkg.getFirstChoices().addAll(products);
	}
	
	public List<ProductDTO> getAlternativesFlight() {
		System.out.println(pkg);
		return pkgMgr.listFirstChoicesByType(pkg, ProductDTO.FLIGHT);
	}
		
	
	public void setAlternativesFlight( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}

	
	public List<ProductDTO> getAlternativesHotel() {
		return pkgMgr.listAlternativesByType(pkg, ProductDTO.HOTEL);
	}
		
	
	public void setAlternativesHotel( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}
	

	public List<ProductDTO> getAlternativesExcursion() {
		return pkgMgr.listAlternativesByType(pkg, ProductDTO.EXCURSION);
	}
	
	
	public void setAlternativesExcursion( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}
	
	
	public List<PackageDTO> getShowcased() {
		return pkgMgr.listShowcasePackages();
	}

	public List<PackageDTO> getAll() {
		return pkgMgr.listAllPackages();
	}
	
}
