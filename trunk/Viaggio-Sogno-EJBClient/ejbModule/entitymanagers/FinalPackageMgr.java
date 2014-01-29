package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.FinalPackageDTO;
import dto.FinalProductDTO;
import dto.PackageDTO;
import dto.ProductDTO;
import dto.UserDTO;

@Local
public interface FinalPackageMgr {
	
	
	/**
	 * @param originalPkg : the package on which the final package will be based
	 * @return the new final package just created
	 */
	public FinalPackageDTO add(PackageDTO originalPkg);
	
	/**
	 * @param finalPkg : the final package to be updated
	 */
	public void update(FinalPackageDTO finalPkg);
	
	
	/**
	 * @param finalPkg : the final package to be removed
	 */
	public void remove(FinalPackageDTO finalPkg);
	
	
	public void finalize(FinalProductDTO finalProduct);
	
	public void swap(FinalPackageDTO finalPackage, ProductDTO oldProduct, ProductDTO newProduct);
	
	public void swap(FinalProductDTO oldProduct, ProductDTO newProduct);
	
	public FinalPackageDTO getByID(int ID);
	
	
	/**
	 * @return the list of final packages owned by the current user
	 */
	public List<FinalPackageDTO> listByUser();
	
	
	/**
	 * @return the list of final packages owned by the given user
	 */
	public List<FinalPackageDTO> listByUser(UserDTO user);
	



}