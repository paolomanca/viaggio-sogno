package control;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.FinalPackage;
import model.Group;
import model.Product;
import dto.FinalPackageDTO;
import dto.ProductDTO;
import entitymanagers.FinalPackageMgr;

@Stateless
@LocalBean
public class FinalPackageMgrBean implements FinalPackageMgr, DTOBuilder<FinalPackage, FinalPackageDTO> {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@EJB
	private UserMgrBean usrMgr;
	
	@EJB
	private PackageMgrBean pkgMgr;
	
	@EJB
	private ProductMgrBean prdMgr;
	
	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void add(FinalPackageDTO finalPkgDTO) {
		FinalPackage newFPkg = new FinalPackage(finalPkgDTO);
		newFPkg.setUser(usrMgr.findByEmail(context.getCallerPrincipal().getName()));
		em.persist(newFPkg);
	}

	@Override
	public void update(FinalPackageDTO finalPkgDTO) {
		FinalPackage newFPkg = new FinalPackage(finalPkgDTO);
		em.merge(newFPkg);
	}

	@Override
	public void remove(FinalPackageDTO finalPkgDTO) {
		em.remove(new FinalPackage(finalPkgDTO));
	}

	@Override
	public FinalPackageDTO getByID(int ID) {
		return buildDTO(em.find(FinalPackage.class, ID));
	}
	
	@Override
	public FinalPackageDTO buildDTO(FinalPackage in) {
		FinalPackageDTO out = new FinalPackageDTO();
		out.setId(in.getIdfinalPackage());
		out.setOriginalPackage(pkgMgr.buildDTO(in.getPackage()));
		List<ProductDTO> productDTOs = new LinkedList<>();
		for(Product p : in.getProducts()){
			productDTOs.add(prdMgr.buildDTO(p));
		}
		out.setProducts(productDTOs );
		return out;
	}

}
