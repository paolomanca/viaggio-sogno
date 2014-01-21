package entitymanagers;

import java.util.List;

import javax.ejb.Local;

import dto.ProductDTO;

@Local
public interface ProductMgr {

	public List<ProductDTO> listAllProducts();

	public void add(ProductDTO productDTO);

}
