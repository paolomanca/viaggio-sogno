package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.FinalPackageDTO;
import dto.FinalProductDTO;
import dto.ProductDTO;
import dto.UserDTO;

@Local
public interface FinalPackageMgr {
	
	public void add(FinalPackageDTO finalPkg);
	
	public void update(FinalPackageDTO finalPkg);
	
	public void remove(FinalPackageDTO finalPkg);
	
	public void finalize(FinalProductDTO finalProduct);
	
	public void swap( ProductDTO oldProduct, ProductDTO newProduct);
	
	public void swap( FinalProductDTO oldProduct, ProductDTO newProduct);
	
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
