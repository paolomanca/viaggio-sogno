package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
	private PackageMgr packageMgr;
	
	private PackageDTO pkg;
	
	
	public PackageBean() {
		pkg = new PackageDTO();
	}
	
	
	public String add(){	
		packageMgr.add(pkg);
		return "index?faces-redirect=true";
	}

	public PackageDTO getPackage() {
		return pkg;
	}
	
	
	public List<ProductDTO> getFirstChoicesFlight() {
		return packageMgr.listFirstChoicesByType(pkg, FLIGHT);
	}
		
	
	public void setFirstChoicesFlight( List<ProductDTO> products ) {
		System.out.println(products);

		pkg.getFirstChoices().addAll(products);
	}

	
	public List<ProductDTO> getFirstChoicesHotel() {
		return packageMgr.listFirstChoicesByType(pkg, HOTEL);
	}
		
	
	public void setFirstChoicesHotel( List<ProductDTO> products ) {
		pkg.getFirstChoices().addAll(products);
	}
	

	public List<ProductDTO> getFirstChoicesExcursion() {
		return packageMgr.listFirstChoicesByType(pkg, EXCURSION);
	}
	
	
	public void setFirstChoicesExcursion( List<ProductDTO> products ) {
		pkg.getFirstChoices().addAll(products);
	}
	
	public List<ProductDTO> getAlternativesFlight() {
		return packageMgr.listFirstChoicesByType(pkg, FLIGHT);
	}
		
	
	public void setAlternativesFlight( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}

	
	public List<ProductDTO> getAlternativesHotel() {
		return packageMgr.listAlternativesByType(pkg, HOTEL);
	}
		
	
	public void setAlternativesHotel( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}
	

	public List<ProductDTO> getAlternativesExcursion() {
		return packageMgr.listAlternativesByType(pkg, EXCURSION);
	}
	
	
	public void setAlternativesExcursion( List<ProductDTO> products ) {
		pkg.getAlternatives().addAll(products);
	}
	
	
	public List<PackageDTO> getShowcased() {
		return packageMgr.listShowcasePackages();
	}

}
