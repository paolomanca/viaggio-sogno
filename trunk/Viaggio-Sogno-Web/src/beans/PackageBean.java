package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.PackageDTO;
import dto.ProductDTO;
import entitymanagers.PackageMgr;

@ManagedBean(name="packageBean")
@RequestScoped
public class PackageBean {
	
	@EJB
	private PackageMgr pkgMgr;
	
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
			} else {
				pkg = pkgMgr.getByID(pkgID);
			}
		}
	}

	public String add(){	
		pkgMgr.add(pkg);
		return "index?faces-redirect=true";
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
		System.out.println(pkg);
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

}
