package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.ProductDTO;
import entitymanagers.ProductMgr;

@ManagedBean(name = "productBean")
@RequestScoped
public class ProductBean {

	private ProductDTO product;

	@EJB
	private ProductMgr prMgr;

	
	@ManagedProperty(value = "#{param.prID}")
	protected int prID;
	
	@ManagedProperty(value = "#{param.act}")
	protected String act;
	
	@ManagedProperty(value = "#{param.type}")
	protected String type;


	@PostConstruct
	public void init() {
		
		if ( act != null ) {
			if ( act.equalsIgnoreCase("create") ) {
				product = new ProductDTO();
			} else {
				product = prMgr.getByID(prID);
			}
		}
	}
	
	public String add() {
		product.setType(type);
		prMgr.add(product);
		return "index?faces-redirect=true";
	}

	public List<ProductDTO> getAll() {
		return prMgr.listAllProducts();
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public List<ProductDTO> getFlights() {
		return prMgr.listByType(ProductDTO.FLIGHT);
	}

	public List<ProductDTO> getHotels() {
		return prMgr.listByType(ProductDTO.HOTEL);
	}

	public List<ProductDTO> getExcursions() {
		return prMgr.listByType(ProductDTO.EXCURSION);
	}

	public int getPrID() {
		return prID;
	}

	public void setPrID(int prID) {
		this.prID = prID;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
