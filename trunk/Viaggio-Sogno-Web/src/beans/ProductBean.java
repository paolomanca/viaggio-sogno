package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dto.PackageDTO;
import dto.ProductDTO;
import entitymanagers.PackageMgr;
import entitymanagers.ProductMgr;

@ManagedBean(name="packageBean")
@RequestScoped
public class ProductBean {
	
	@EJB
	private ProductMgr productMgr;
	
	public List<ProductDTO> getAll() {
		return productMgr.listProducts();
	}
	
}
