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

import model.FinalExcursion;
import model.FinalFlight;
import model.FinalHotel;
import model.FinalPackage;
import model.Group;
import model.Product;
import model.User;
import dto.FinalExcursionDTO;
import dto.FinalFlightDTO;
import dto.FinalHotelDTO;
import dto.FinalPackageDTO;
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
	@RolesAllowed({Group._CUSTOMER})
	public void add(FinalPackageDTO finalPkgDTO) {
		FinalPackage newFPkg = new FinalPackage(finalPkgDTO);
		
		newFPkg.setProducts(new LinkedList<Product>());			
		newFPkg.setFinalFlights(new LinkedList<FinalFlight>());
		newFPkg.setFinalHotels(new LinkedList<FinalHotel>());
		newFPkg.setFinalExcursions(new LinkedList<FinalExcursion>());
		
		for(ProductDTO pDTO : finalPkgDTO.getFlights()){
			newFPkg.getProducts().add(new Product(pDTO));
		}
		for(ProductDTO pDTO : finalPkgDTO.getHotels()){
			newFPkg.getProducts().add(new Product(pDTO));
		}
		for(ProductDTO pDTO : finalPkgDTO.getExcursions()){
			newFPkg.getProducts().add(new Product(pDTO));
		}
		
		for(FinalFlightDTO fFDTO : finalPkgDTO.getFinalFlights()){
			newFPkg.getFinalFlights().add(new FinalFlight(fFDTO));
		}
		for(FinalHotelDTO fHDTO : finalPkgDTO.getFinalHotels()){
			newFPkg.getFinalHotels().add(new FinalHotel(fHDTO));
		}
		for(FinalExcursionDTO fEDTO : finalPkgDTO.getFinalExcursions()){
			newFPkg.getFinalExcursions().add(new FinalExcursion(fEDTO));
		}
		
		User current = usrMgr.findByEmail(context.getCallerPrincipal().getName());
		
		newFPkg.setUser(current);
		newFPkg.setIdfinalPackageRelative(current.nextID());
		em.persist(newFPkg);
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void update(FinalPackageDTO finalPkgDTO) {
		em.merge(fromRelativeID(finalPkgDTO.getId()));
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void remove(FinalPackageDTO finalPkgDTO) {
		em.remove(fromRelativeID(finalPkgDTO.getId()));
		usrMgr.getPrincipalUser().freeID(finalPkgDTO.getId());
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public FinalPackageDTO getByID(int ID) {
		return buildDTO(fromRelativeID(ID), ID);
	}

	private FinalPackageDTO buildDTO(FinalPackage in) {
		return buildDTO(in, in.getIdfinalPackage());
	}

	public FinalPackageDTO buildDTO(FinalPackage in, int ID) {
		FinalPackageDTO out = new FinalPackageDTO();
		out.setId(ID);
		out.setOriginalPackage(pkgMgr.buildDTO(in.getPackage()));
		
		List<ProductDTO> flightDTOs = new LinkedList<>();
		List<ProductDTO> hotelDTOs = new LinkedList<>();
		List<ProductDTO> excursionDTOs = new LinkedList<>();
		
		List<FinalFlightDTO> finalFlightDTOs = new LinkedList<>();
		List<FinalHotelDTO> finalHotelDTOs = new LinkedList<>();
		List<FinalExcursionDTO> finalExcursionDTOs = new LinkedList<>();
		
		for(Product p : in.getProducts()){
			if(p.getType().equals(Product.FLIGHT)){
				flightDTOs.add(prdMgr.buildDTO(p));
			} else
			if(p.getType().equals(Product.HOTEL)){
				hotelDTOs.add(prdMgr.buildDTO(p));
			} else
			if(p.getType().equals(Product.EXCURSION)){
				excursionDTOs.add(prdMgr.buildDTO(p));
			}			
		}
		
		for(FinalFlight fF : getFinalFlights(in)){
			finalFlightDTOs.add(fnPrdMgr.buildFlightDTO(fF));
		}
		for(FinalHotel fH : getFinalHotels(in)){
			finalHotelDTOs.add(fnPrdMgr.buildHotelDTO(fH));
		}
		for(FinalExcursion fE : getFinalExcursions(in)){
			finalExcursionDTOs.add(fnPrdMgr.buildExcursionDTO(fE));
		}
		
		out.setFlights(flightDTOs);
		out.setHotels(hotelDTOs);
		out.setExcursions(excursionDTOs);
		out.setFinalFlights(finalFlightDTOs);
		out.setFinalHotels(finalHotelDTOs);
		out.setFinalExcursions(finalExcursionDTOs);

		return out;
	}

	private List<FinalFlight> getFinalFlights(FinalPackage in) {
		return em.createQuery("SELECT t FROM FinalFlight t where t.finalPackage = :finalPackage", FinalFlight.class)
		.setParameter("finalPackage", in).getResultList();
	}

	private List<FinalHotel> getFinalHotels(FinalPackage in) {
		return em.createQuery("SELECT t FROM FinalHotel t where t.finalPackage = :finalPackage", FinalHotel.class)
				.setParameter("finalPackage", in).getResultList();
	}

	private List<FinalExcursion> getFinalExcursions(FinalPackage in) {
		return em.createQuery("SELECT t FROM FinalExcursion t where t.finalPackage = :finalPackage", FinalExcursion.class)
				.setParameter("finalPackage", in).getResultList();
	}

	@Override
	public List<FinalPackageDTO> listByUser() {
		List<FinalPackageDTO> out = new LinkedList<>();
		List<FinalPackage> toConvert = em.createQuery("SELECT t FROM FinalPackage t where t.user = :user", FinalPackage.class)
		.setParameter("user", usrMgr.getPrincipalUser()).getResultList();
		
		for(FinalPackage pkg : toConvert){
			out.add(buildDTO(pkg, pkg.getIdfinalPackageRelative()));
		}
		
		return out;
	}

	@Override
	@RolesAllowed({Group._EMPLOYEE})
	public List<FinalPackageDTO> listByUser(UserDTO user) {
		List<FinalPackageDTO> out = new LinkedList<>();
		List<FinalPackage> toConvert = em.createQuery("SELECT t FROM FinalPackage t where t.user = :user", FinalPackage.class)
		.setParameter("user", user).getResultList();
		
		for(FinalPackage fP : toConvert){
			out.add(buildDTO(fP));
		}
		
		return out;
	}

	private FinalPackage fromRelativeID(int id) {
		return em.createQuery("SELECT t FROM FinalPackage t where t.user = :user and t.idfinalPackageRelative = :id ", FinalPackage.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();
	}

}
