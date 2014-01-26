package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.ProductDTO;

@Local
public interface ProductMgr {

	public void add(ProductDTO productDTO);
	
	public ProductDTO getByID(int id);
	
	public List<ProductDTO> listAllProducts();

	public List<ProductDTO> listByType(String type);

}
