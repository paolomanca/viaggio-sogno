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
	
	private static final String FLIGHT = "flight";
	private static final String HOTEL = "hotel";
	private static final String EXCURSION = "excursion";
	
	@EJB
	private PackageMgr pkgMgr;
	
	@ManagedProperty(value = "#{param.pkgID}")
	protected int pkgID;
	
	public int getPkgID() {
		return pkgID;
	}

	public void setPkgID(int pkgID) {
		this.pkgID = pkgID;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}


	@ManagedProperty(value = "#{param.act}")
	protected String act;
	
	private PackageDTO pkg;
	
	
	public PackageBean() {
		pkg = new PackageDTO();
	}
	
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

	public PackageDTO getPackage() {
		return pkg;
	}
	
	
	public List<ProductDTO> getFirstChoicesFlight() {
		return pkgMgr.listFirstChoicesByType(pkg, FLIGHT);
	}
		
	
	public void setFirstChoicesFlight( List<ProductDTO> products ) {
		System.out.println(products);

		pkg.getFirstChoices().addAll(products);
	}

	
	public List<ProductDTO> getFirstChoicesHotel() {
		return pkgMgr.listFirstChoicesByType(pkg, HOTEL);
	}
		
	
	public void setFirstChoicesHotel( List<ProductDTO> products ) {
		pkg.getFirstChoices().addAll(products);
	}
	

	public List<ProductDTO> getFirstChoicesExcursion() {
		return pkgMgr.listFirstChoicesByType(pkg, EXCURSION);
	}
	
	
	public void setFirstChoicesExcursion( List<ProductDTO> products ) {
		pkg.getFirstChoices().addAll(products);
	}
	
	public List<ProductDTO> getAlternativesFlight() {
		return pkgMgr.listFirstChoicesByType(pkg, FLIGHT);
	}
		
	
	public void setAlternativesFlight( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}

	
	public List<ProductDTO> getAlternativesHotel() {
		return pkgMgr.listAlternativesByType(pkg, HOTEL);
	}
		
	
	public void setAlternativesHotel( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}
	

	public List<ProductDTO> getAlternativesExcursion() {
		return pkgMgr.listAlternativesByType(pkg, EXCURSION);
	}
	
	
	public void setAlternativesExcursion( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}
	
	
	public List<PackageDTO> getShowcased() {
		return pkgMgr.listShowcasePackages();
	}

}
