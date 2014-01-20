package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.PackageDTO;

@Local
public interface PackageMgr {
	
	public void add(PackageDTO pkg);
	
	public void update(PackageDTO pkg);
	
	public void remove(PackageDTO pkg);
	
	public List<PackageDTO> listAllPackages();
	
	public List<PackageDTO> listShowcasePackages();

}
