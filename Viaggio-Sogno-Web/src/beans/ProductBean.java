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
	
	private static final String FLIGHT = "flight";
	private static final String HOTEL = "hotel";
	private static final String EXCURSION = "excursion";
	
	private ProductDTO product;
	
	public ProductBean(){
		setProduct(new ProductDTO());
	}
	
	@EJB
	private ProductMgr productMgr;
	
	public List<ProductDTO> getAll() {
		return productMgr.listAllProducts();
	}
	
	public String addFlight(){
		getProduct().setType(FLIGHT);
		productMgr.add(getProduct());
		return "index?faces-redirect=true";
	}

	public String addHotel(){
		getProduct().setType(HOTEL);
		productMgr.add(getProduct());
		return "index?faces-redirect=true";
	}
	
	public String addExcursion(){
		getProduct().setType(EXCURSION);
		productMgr.add(getProduct());
		return "index?faces-redirect=true";
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
}
