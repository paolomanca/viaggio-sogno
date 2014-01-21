package entitymanagers;


import javax.ejb.Local;

import dto.FinalPackageDTO;
import dto.PackageDTO;

@Local
public interface FinalPackageMgr {
	
	public void add(FinalPackageDTO finalPkg);
	
	public void update(FinalPackageDTO finalPkg);
	
	public void remove(FinalPackageDTO finalPkg);
	
	public PackageDTO getOriginalPackage(FinalPackageDTO finalPkg);

}
