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
import model.Product;
import dto.PackageDTO;
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

	@Override
	@RolesAllowed({Group._EMPLOYEE})
	public void add(PackageDTO pkg) {
		Package newPkg = new Package(pkg);
		newPkg.setUser(usrMgr.findByEmail(context.getCallerPrincipal().getName()));
		em.persist(newPkg);
	}
	

	@Override
	@RolesAllowed({Group._EMPLOYEE})
	public void update(PackageDTO pkg) {
		Package oldPackage = findByID(pkg.getId());
		Package tempPackage = new Package(pkg);
		tempPackage.setUser(oldPackage.getUser());
		em.merge(tempPackage);
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
				out.add(convertToDTO(p));
		};
		return out;
	}

	@Override
	public List<PackageDTO> listAllPackages() {
		List<PackageDTO> out = new LinkedList<>();
		for(Package p : em.createNamedQuery(Package.FIND_ALL, Package.class).getResultList()){
				out.add(convertToDTO(p));
		};
		return out;
	}
	
	private PackageDTO convertToDTO(Package pkg) {
		PackageDTO pkgDTO = new PackageDTO();
		pkgDTO.setId(pkg.getIdPACKAGE());
		pkgDTO.setName(pkg.getName());
		pkgDTO.setProducts(Product.convertProductsToDTOs(pkg.getProducts()));
		pkgDTO.setShowcased(pkg.isShowcased());
		return pkgDTO;
	}

}
