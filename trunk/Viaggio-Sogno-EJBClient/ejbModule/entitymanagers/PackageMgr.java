package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.PackageDTO;
import dto.ProductDTO;

@Local
public interface PackageMgr {
	
	public void add(PackageDTO pkg);
	
	public void remove(PackageDTO pkg);
	
	public PackageDTO getByID(int id);
	
	public List<PackageDTO> listAllPackages();
	
	public List<PackageDTO> listShowcasePackages();

	public List<ProductDTO> listFirstChoices(PackageDTO pkg);
	
	public List<ProductDTO> listAlternatives(PackageDTO pkg);
	
	public List<ProductDTO> listFirstChoicesByType(PackageDTO pkg, String type);
	
	public List<ProductDTO> listAlternativesByType(PackageDTO pkg, String type);

}
