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

import model.Group;
import model.Package;
import model.PackageHasProduct;
import model.Product;
import dto.PackageDTO;
import dto.ProductDTO;
import entitymanagers.PackageMgr;

@Stateless
@LocalBean
public class PackageMgrBean implements PackageMgr {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@EJB
	private UserMgrBean usrMgr;

	@EJB
	private ProductMgrBean prdMgr;

	@Override
	@RolesAllowed({Group._EMPLOYEE})
	public void add(PackageDTO pkg) {
		Package newPkg = new Package(pkg);
		newPkg.setUser(usrMgr.findByEmail(context.getCallerPrincipal().getName()));
		em.persist(newPkg);
		
		for(ProductDTO prdDTO : pkg.getFirstChoices()){
			PackageHasProduct temp = new PackageHasProduct(newPkg, new Product(prdDTO));
			temp.setFirstChoice(true);
			em.persist(temp);
			newPkg.addPackageHasProduct(temp);
		}
		
		for(ProductDTO prdDTO : pkg.getAlternatives()){
			PackageHasProduct temp = new PackageHasProduct(newPkg, new Product(prdDTO));
			temp.setFirstChoice(false);
			em.persist(temp);
			newPkg.addPackageHasProduct(temp);
		}
		
		em.merge(newPkg);
	}

	@Override
	@RolesAllowed({Group._EMPLOYEE})
	public void remove(PackageDTO pkg) {
		em.remove(findByID(pkg.getId()));
	}

	private Package findByID(int id) {
		return em.find(Package.class, id);
	}

	@Override
	public List<PackageDTO> listShowcasePackages() {
		List<PackageDTO> out = new LinkedList<>();
		for(Package p : em.createNamedQuery(Package.FIND_ALL, Package.class).getResultList()){
			if(p.isShowcased())
				out.add(buildDTO(p));
		};
		return out;
	}

	@Override
	public List<PackageDTO> listAllPackages() {
		List<PackageDTO> out = new LinkedList<>();
		for(Package p : em.createNamedQuery(Package.FIND_ALL, Package.class).getResultList()){
			out.add(buildDTO(p));
		};
		return out;
	}

	private List<PackageHasProduct> getPackageProducts(Package pkg) {
		List<PackageHasProduct> pkgHsPrds = em.createQuery("SELECT t FROM PackageHasProduct t where t.pkg = :pkg", PackageHasProduct.class)
				.setParameter("pkg", pkg).getResultList();
		return pkgHsPrds;
	}

	@Override
	public List<ProductDTO> listFirstChoices(PackageDTO pkg) {
		List<ProductDTO> out = new LinkedList<>();
		for(PackageHasProduct pkgHsPrd : getPackageProducts(em.find(Package.class, pkg.getId()))){
			if(pkgHsPrd.getFirstChoice()){
				out.add(prdMgr.buildDTO(pkgHsPrd.getProduct()));
			}
		}
		return out;
	}

	@Override
	public List<ProductDTO> listAlternatives(PackageDTO pkg) {
		List<ProductDTO> out = new LinkedList<>();
		for(PackageHasProduct pkgHsPrd : getPackageProducts(em.find(Package.class, pkg.getId()))){
			if(!pkgHsPrd.getFirstChoice()){
				out.add(prdMgr.buildDTO(pkgHsPrd.getProduct()));
			}
		}
		return out;
	}

	@Override
	public List<ProductDTO> listFirstChoicesByType(PackageDTO pkg, String type) {
		
		
		List<ProductDTO> out = new LinkedList<>();
		for(PackageHasProduct pkgHsPrd : getPackageProducts(em.find(Package.class, pkg.getId()))){
			if(pkgHsPrd.getFirstChoice() && pkgHsPrd.getProduct().getType().equals(type)){
				out.add(prdMgr.buildDTO(pkgHsPrd.getProduct()));
			}
		}
		
		System.out.println("# of first choices " + out.size());

		
		return out;
	}

	@Override
	public List<ProductDTO> listAlternativesByType(PackageDTO pkg, String type) {
		List<ProductDTO> out = new LinkedList<>();
		for(PackageHasProduct pkgHsPrd : getPackageProducts(em.find(Package.class, pkg.getId()))){
			if(!pkgHsPrd.getFirstChoice() && pkgHsPrd.getProduct().getType().equals(type)){
				out.add(prdMgr.buildDTO(pkgHsPrd.getProduct()));
			}
		}
				
		return out;
	}

	public PackageDTO buildDTO(Package in) {
		PackageDTO pkgDTO = new PackageDTO();
		pkgDTO.setId(in.getIdpackage());
		pkgDTO.setName(in.getName());
		pkgDTO.setFirstChoices(new LinkedList<ProductDTO>());
		pkgDTO.setFirstChoices(new LinkedList<ProductDTO>());
		for(PackageHasProduct pkgHsPrd : getPackageProducts(in)){
			if(pkgHsPrd.getFirstChoice()){

				pkgDTO.getFirstChoices().add(prdMgr.buildDTO(pkgHsPrd.getProduct()));
			} else {
				pkgDTO.getAlternatives().add(prdMgr.buildDTO(pkgHsPrd.getProduct()));
			}
		}
		pkgDTO.setShowcased(in.isShowcased());
		return pkgDTO;
	}

	@Override
	public PackageDTO getByID(int id) {
		return buildDTO(em.find(Package.class, id));
	}

}
