package entitymanagers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

import common.Constants;

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
	public FinalPackageDTO finalizePackage(PackageDTO originalPkg);
	
	/**
	 * @param finalPkg : the final package to be updated
	 */
	@RolesAllowed({Constants.Group.CUSTOMER})
	public void update(FinalPackageDTO finalPkg);
	
	
	/**
	 * @param finalPkg : the final package to be removed
	 */
	@RolesAllowed({Constants.Group.CUSTOMER})
	public void remove(FinalPackageDTO finalPkg);
	
	
	/**
	 * 
	 * @param container
	 * @param finalProduct
	 */
	public void finalizeProduct(FinalPackageDTO container, FinalProductDTO finalProduct);
	
	
	/**
	 *
	 * @param byMyID
	 */
	@RolesAllowed({Constants.Group.CUSTOMER})
	public void reserve(FinalPackageDTO byMyID);
	
	public void swap(FinalPackageDTO finalPackage, ProductDTO oldProduct, ProductDTO newProduct);
	
	public void swap(FinalPackageDTO toChange, FinalProductDTO oldProduct,
			ProductDTO newProduct);
	
	@RolesAllowed({Constants.Group.CUSTOMER})
	public FinalPackageDTO getByMyID(int ID);
	
	public FinalPackageDTO getSharedFinalPackage(String ID);
	
	public void shareFinalPackage(FinalPackageDTO finalPkg);
	
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public void pay(FinalPackageDTO finalPkg);
	
	
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public List<FinalPackageDTO> listAll();
	
	/**
	 * @return the list of final packages owned by the current user
	 */
	public List<FinalPackageDTO> listByUser();
	
	
	/**
	 * @return the list of final packages owned by the given user
	 */
	@RolesAllowed({Constants.Group.EMPLOYEE})
	public List<FinalPackageDTO> listByUser(UserDTO user);

}
