package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.FinalPackageDTO;
import dto.UserDTO;

@Local
public interface FinalPackageMgr {
	
	public void add(FinalPackageDTO finalPkg);
	
	public void update(FinalPackageDTO finalPkg);
	
	public void remove(FinalPackageDTO finalPkg);
	
	public FinalPackageDTO getByID(int ID);
	
	
	/**
	 * @return the list of final packages owned by the current user
	 */
	public List<FinalPackageDTO> listByUser();
	
	
	/**
	 * @return the list of final packages owned by the given user
	 */
	public List<FinalPackageDTO> listByUser(UserDTO user);
	
	
	/**
	 * @return the list of products in the given final package
	 */
	//public List<ProductDTO> listProducts(FinalPackageDTO finalPkg);
	
	
	

	
	/**
	 * @return the list of final products in the given final package
	 */
	//public List<FinalProductDTO> listFinalProducts(FinalPackageDTO finalPkg);


}
