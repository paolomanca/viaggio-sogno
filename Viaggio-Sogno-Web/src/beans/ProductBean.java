package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import dto.ProductDTO;
import entitymanagers.ProductMgr;

@ManagedBean(name="productBean")
@RequestScoped
public class ProductBean {
	
	@EJB
	private ProductMgr productMgr;
	
	public List<ProductDTO> getAll() {
		return productMgr.listAllProducts();
	}
	
}
