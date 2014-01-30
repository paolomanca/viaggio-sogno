package control;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;
import dto.ProductDTO;
import entitymanagers.ProductMgr;

@Stateless
@LocalBean
public class ProductMgrBean implements ProductMgr {
	
	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@Override
	public List<ProductDTO> listAllProducts() {
		List<ProductDTO> out = new LinkedList<>();
		for(Product p : em.createNamedQuery(Product.FIND_ALL, Product.class).getResultList()){
				out.add(buildDTO(p));
		};
		return out;
	}

	@Override
	public void add(ProductDTO productDTO) {
		em.persist(new Product(productDTO));
	}

	@Override
	public List<ProductDTO> listByType(String type) {
		List<ProductDTO> out = new LinkedList<>();
		for(Product p : em.createQuery("SELECT t FROM Product t where t.type = :type", Product.class)
				.setParameter("type", type).getResultList()){
				out.add(buildDTO(p));
		};
		return out;
	}

	public ProductDTO buildDTO(Product in) {
		ProductDTO out = new ProductDTO();
		out.setArrAirport(in.getArrAirport());
		out.setDepAirport(in.getDepAirport());
		out.setDescription(in.getDescription());
		out.setFlightLength(in.getFlightLength());
		out.setId(in.getIdproduct());
		out.setLocation(in.getLocation());
		out.setName(in.getName());
		if ( in.getPrice() != null )
			out.setPrice(in.getPrice().intValue());
		out.setRating(in.getRating());
		out.setType(in.getType());
		return out;
	}

	@Override
	public ProductDTO getByID(int id) {
		System.out.println("getting product with id: "+id);
		return buildDTO(em.find(Product.class, id));
	}

	@Override
	public void update(ProductDTO productDTO) {
		Product toUpdate = new Product(productDTO);
		em.merge(toUpdate);
	}

	@Override
	public void remove(ProductDTO productDTO) {
		em.remove(em.find(Product.class, productDTO).getIdproduct());
	}

}
