package entitymanagers;

import javax.ejb.Local;

import dto.FinalProductDTO;

@Local
public interface FinalProductMgr {
	
	public void add(FinalProductDTO fP);

	public void update(FinalProductDTO fP);

	public void remove(FinalProductDTO fP);
	
	public FinalProductDTO getByID(int id);
	
	public FinalProductDTO getByID(int id, String type);

}