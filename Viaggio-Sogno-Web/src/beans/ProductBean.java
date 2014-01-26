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

	private static final String FLIGHT = "flight";
	private static final String HOTEL = "hotel";
	private static final String EXCURSION = "excursion";

	private ProductDTO product;

	@EJB
	private ProductMgr productMgr;

	@ManagedProperty(value = "#{param.id}")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@PostConstruct
	public void init() {
		if (id <= 0)
			product = new ProductDTO();
		else
			product = productMgr.getByID(id);
	}

	public String addFlight() {
		getProduct().setType(FLIGHT);
		productMgr.add(getProduct());
		return "index?faces-redirect=true";
	}

	public String addHotel() {
		getProduct().setType(HOTEL);
		productMgr.add(getProduct());
		return "index?faces-redirect=true";
	}

	public String addExcursion() {
		getProduct().setType(EXCURSION);
		productMgr.add(getProduct());
		return "index?faces-redirect=true";
	}

	public List<ProductDTO> getAll() {
		return productMgr.listAllProducts();
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public List<ProductDTO> getFlights() {
		return productMgr.listByType(FLIGHT);
	}

	public List<ProductDTO> getHotels() {
		return productMgr.listByType(HOTEL);
	}

	public List<ProductDTO> getExcursions() {
		return productMgr.listByType(EXCURSION);
	}

}
