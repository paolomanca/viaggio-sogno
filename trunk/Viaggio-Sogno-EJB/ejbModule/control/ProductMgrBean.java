package control;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;
import dto.ProductDTO;
import entitymanagers.ProductMgr;

@Stateless
public class ProductMgrBean implements ProductMgr {
	
	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@Override
	public List<ProductDTO> listAllProducts() {
		List<ProductDTO> out = new LinkedList<>();
		for(Product p : em.createNamedQuery(Product.FIND_ALL, Product.class).getResultList()){
				out.add(p.getDTO());
		};
		return out;
	}

	@Override
	public void add(ProductDTO productDTO) {
		em.persist(new Product(productDTO));
	}

}
