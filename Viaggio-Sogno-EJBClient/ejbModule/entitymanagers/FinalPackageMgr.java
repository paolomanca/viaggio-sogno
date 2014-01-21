package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.PackageDTO;
import dto.ProductDTO;

@Local
public interface FinalPackageMgr {
	
	public void add(FinalPackageDTO finalPkg);
	
	public void update(FinalPackageDTO finalPkg);
	
	public void remove(FinalPackageDTO finalPkg);
	
	public PackageDTO getOriginalPackage(FinalPackageDTO finalPkg);

}
