package entitymanagers;

import dto.FinalProductDTO;

public interface FinalProductMgr {
	
	public void add(FinalProductDTO fP);

	public void update(FinalProductDTO fP);

	public void remove(FinalProductDTO fP);

}