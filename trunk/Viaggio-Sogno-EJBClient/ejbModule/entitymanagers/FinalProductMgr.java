package entitymanagers;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

import common.Constants;

import dto.FinalProductDTO;

@Local
public interface FinalProductMgr {
	
	@RolesAllowed({Constants.Group.CUSTOMER})
	public int add(FinalProductDTO fP);

	@RolesAllowed({Constants.Group.CUSTOMER})
	public void update(FinalProductDTO fP);

	@RolesAllowed({Constants.Group.CUSTOMER})
	public void remove(FinalProductDTO fP);
	
	public FinalProductDTO getByID(int id);
	
	public FinalProductDTO getByID(int id, String type);

}