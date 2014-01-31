package control;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.FinalPackage;
import model.FinalProduct;
import model.Package;
import model.PackageHasProduct;
import model.Product;
import model.SharedPackage;
import dto.FinalPackageDTO;
import dto.FinalProductDTO;
import dto.PackageDTO;
import dto.ProductDTO;
import dto.UserDTO;
import entitymanagers.FinalPackageMgr;

@Stateless
@LocalBean
public class FinalPackageMgrBean implements FinalPackageMgr {

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

	@EJB
	private FinalProductMgrBean fnPrdMgr;

	@Override
	public void update(FinalPackageDTO finalPkgDTO) {
		FinalPackage fP = fromRelativeID(finalPkgDTO.getId());
		em.detach(fP);
		fP.setProducts(new LinkedList<Product>());
		List<FinalProduct> originalFinalProducts = fP.getFinalProducts();
		List<FinalProduct> newFinalProducts = new LinkedList<FinalProduct>();

		for (ProductDTO pDTO : finalPkgDTO.getProducts()) {
			fP.getProducts().add(em.find(Product.class, pDTO.getId()));
		}

		for (FinalProductDTO fPDTO : finalPkgDTO.getFinalProducts()) {
			FinalProduct fPrd = fnPrdMgr.fromRelativeID(fPDTO.getId(),
					fPDTO.getType());
			if (fPrd != null) {
				newFinalProducts.add(fPrd);
			} else {
				int id = fnPrdMgr.add(fPDTO);
				fPrd = fnPrdMgr.fromRelativeID(id, fPDTO.getType());
				newFinalProducts.add(fPrd);
			}
		}

		System.out.println("original size: " + originalFinalProducts.size());
		System.out.println("new size: " + newFinalProducts.size());
		originalFinalProducts.removeAll(newFinalProducts);

		for (FinalProduct oFP : originalFinalProducts) {
			System.out.println("Deleting ID: " + oFP.getId());
			usrMgr.getPrincipalUser().freeID(oFP.getIdRelative());
		}

		fP.setFinalProducts(newFinalProducts);
		if (fP.getProducts().isEmpty()) {
			fP.setFinalized(true);
		} else {
			fP.setFinalized(false);
		}
		em.merge(fP);
	}

	@Override
	public void remove(FinalPackageDTO finalPkgDTO) {
		em.remove(fromRelativeID(finalPkgDTO.getId()));
		usrMgr.getPrincipalUser().freeID(finalPkgDTO.getId());
	}

	@Override
	public FinalPackageDTO getByMyID(int ID) {
		System.out.println("getting my id number: " + ID);
		return buildDTO(fromRelativeID(ID), ID);
	}

	private FinalPackageDTO buildDTO(FinalPackage in) {
		return buildDTO(in, in.getIdfinalPackage());
	}

	public FinalPackageDTO buildDTO(FinalPackage in, int ID) {
		FinalPackageDTO out = new FinalPackageDTO();
		out.setId(ID);
		out.setOriginalPackage(pkgMgr.buildDTO(in.getPackage()));

		List<ProductDTO> productDTOs = new LinkedList<>();

		List<FinalProductDTO> finalProductDTOs = new LinkedList<>();

		for (Product p : in.getProducts()) {
			productDTOs.add(prdMgr.buildDTO(p));
		}

		for (FinalProduct fP : in.getFinalProducts()) {
			finalProductDTOs.add(fnPrdMgr.buildDTO(fP));
		}

		out.setProducts(productDTOs);
		out.setFinalProducts(finalProductDTOs);

		if (in.getSharedPackage() != null) {
			out.setSharedID(in.getSharedPackage().getUniqueIdentifier());
		}

		out.setFinalized(in.isFinalized());
		out.setReserved(in.isReserved());
		out.setPaid(in.isPaid());
		out.setShared(in.isShared());

		return out;
	}

	@Override
	public List<FinalPackageDTO> listByUser() {
		List<FinalPackageDTO> out = new LinkedList<>();
		List<FinalPackage> toConvert = em
				.createQuery(
						"SELECT t FROM FinalPackage t where t.user = :user",
						FinalPackage.class)
				.setParameter("user", usrMgr.getPrincipalUser())
				.getResultList();

		for (FinalPackage pkg : toConvert) {
			out.add(buildDTO(pkg, pkg.getIdfinalPackageRelative()));
		}

		return out;
	}

	@Override
	public List<FinalPackageDTO> listByUser(UserDTO user) {
		List<FinalPackageDTO> out = new LinkedList<>();
		List<FinalPackage> toConvert = em
				.createQuery(
						"SELECT t FROM FinalPackage t where t.user = :user",
						FinalPackage.class).setParameter("user", user)
				.getResultList();

		for (FinalPackage fP : toConvert) {
			out.add(buildDTO(fP));
		}

		return out;
	}

	public FinalPackage fromRelativeID(int id) {
		return em
				.createQuery(
						"SELECT t FROM FinalPackage t where t.user = :user and t.idRelative = :id ",
						FinalPackage.class)
				.setParameter("user", usrMgr.getPrincipalUser())
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public void finalizeProduct(FinalPackageDTO container,
			FinalProductDTO finalProduct) {
		FinalPackage current = fromRelativeID(container.getId());
		Product finalizedPrd = em.find(Product.class, finalProduct.getProduct()
				.getId());

		int id = fnPrdMgr.add(finalProduct);
		FinalProduct fP = fnPrdMgr.fromRelativeID(id, finalProduct.getType());

		current.getFinalProducts().add(fP);

		current.getProducts().remove(finalizedPrd);

		if (current.getProducts().isEmpty()
				&& !current.getFinalProducts().isEmpty()) {
			current.setFinalized(true);
		}
		em.merge(current);
	}

	@Override
	public void swap(FinalPackageDTO toChange, ProductDTO oldProduct,
			ProductDTO newProduct) {

		FinalPackage toSwap = fromRelativeID(toChange.getId());
		Product oldP = em.find(Product.class, oldProduct.getId());
		Product newP = em.find(Product.class, newProduct.getId());

		toSwap.getProducts().add(newP);
		toSwap.getProducts().remove(oldP);
		toSwap.setFinalized(false);
		em.merge(toSwap);
	}

	@Override
	public void swap(FinalPackageDTO toChange, FinalProductDTO oldProduct,
			ProductDTO newProduct) {
		FinalPackage toSwap = fromRelativeID(toChange.getId());
		Product newP = em.find(Product.class, newProduct.getId());
		toSwap.getProducts().add(newP);
		toSwap.getFinalProducts().remove(
				fnPrdMgr.fromRelativeID(oldProduct.getId(),
						oldProduct.getType()));
		usrMgr.getPrincipalUser().freeID(oldProduct.getId());
		toSwap.setFinalized(false);
		em.merge(toSwap);
	}

	@Override
	public FinalPackageDTO finalizePackage(PackageDTO originalPkg) {
		Package toFinalize = em.find(Package.class, originalPkg.getId());
		FinalPackage fP = new FinalPackage();
		fP.setIdfinalPackageRelative(usrMgr.getPrincipalUser().nextID());
		fP.setUser(usrMgr.getPrincipalUser());
		fP.setPackage(toFinalize);
		fP.setProducts(new LinkedList<Product>());
		fP.setFinalProducts(new LinkedList<FinalProduct>());

		for (PackageHasProduct p : toFinalize.getPackageHasProducts()) {
			if (p.isFirstChoice())
				fP.getProducts().add(p.getProduct());
		}

		em.persist(fP);

		return buildDTO(fP, fP.getIdfinalPackageRelative());
	}

	@Override
	public FinalPackageDTO getSharedFinalPackage(String ID) {
		SharedPackage sP = em.find(SharedPackage.class, ID);
		return buildSharedDTO(sP.getFinalPackage());
	}

	private FinalPackageDTO buildSharedDTO(FinalPackage finalPackage) {
		int idCount = 1;
		FinalPackageDTO out = buildDTO(finalPackage);
		out.setId(idCount++);
		for (FinalProductDTO pDTO : out.getFinalProducts()) {
			pDTO.setId(idCount++);
		}
		System.out.println("SHARED ID: " + out.getSharedID());
		return out;
	}

	@Override
	public void shareFinalPackage(FinalPackageDTO finalPkg) {
		FinalPackage toShare = fromRelativeID(finalPkg.getId());

		String uniqueId = UUID.randomUUID().toString();

		SharedPackage sP = new SharedPackage();
		sP.setFinalPackage(toShare);
		sP.setUniqueIdentifier(uniqueId);

		em.persist(sP);

		toShare.setSharedPackage(sP);
		toShare.setShared(true);

	}

	@Override
	public void reserve(FinalPackageDTO finalPkg) {
		FinalPackage fP = fromRelativeID(finalPkg.getId());
		if (fP.isFinalized())
			fP.setReserved(true);
	}

	@Override
	public List<FinalPackageDTO> listAll() {
		List<FinalPackageDTO> out = new LinkedList<>();
		for (FinalPackage fP : em.createNamedQuery(FinalPackage.ALL,
				FinalPackage.class).getResultList()) {
			out.add(buildDTO(fP));
		}
		return out;
	}

	@Override
	public void pay(FinalPackageDTO finalPkg) {
		FinalPackage fP = fromRelativeID(finalPkg.getId());
		if (fP.isReserved())
			fP.setPaid(true);
	}

}
