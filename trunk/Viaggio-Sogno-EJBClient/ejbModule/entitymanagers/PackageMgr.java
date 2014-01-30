package entitymanagers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

import common.Constants;

import dto.PackageDTO;
import dto.ProductDTO;

@Local
public interface PackageMgr {
	
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public void add(PackageDTO pkg);
	
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public void remove(PackageDTO pkg);
	
	public void update(PackageDTO pkg);
	
	public PackageDTO getByID(int id);
	
	public List<PackageDTO> listAllPackages();
	
	public List<PackageDTO> listShowcasePackages();

	public List<ProductDTO> listFirstChoices(PackageDTO pkg);
	
	public List<ProductDTO> listAlternatives(PackageDTO pkg);
	
	public List<ProductDTO> listFirstChoicesByType(PackageDTO pkg, String type);
	
	public List<ProductDTO> listAlternativesByType(PackageDTO pkg, String type);



}
