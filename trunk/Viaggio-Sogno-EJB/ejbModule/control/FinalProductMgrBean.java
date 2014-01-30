package control;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.FinalExcursion;
import model.FinalFlight;
import model.FinalHotel;
import model.FinalProduct;
import model.Group;
import model.Product;
import dto.FinalExcursionDTO;
import dto.FinalFlightDTO;
import dto.FinalHotelDTO;
import dto.FinalProductDTO;
import entitymanagers.FinalProductMgr;

@Stateless
@LocalBean
public class FinalProductMgrBean implements FinalProductMgr {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@EJB
	private ProductMgrBean prdMgr;

	@EJB
	private UserMgrBean usrMgr;

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public int add(FinalProductDTO fPDTO) {
		FinalProduct fP = buildFromDTO(fPDTO);		
		fP.setProduct(em.find(Product.class, fPDTO.getProduct().getId()));
		fP.setIdRelative(usrMgr.getPrincipalUser().nextID());
		fP.setUser(usrMgr.getPrincipalUser());
		em.persist(fP);
		return fP.getIdRelative();
	}

	private FinalProduct buildFromDTO(FinalProductDTO fPDTO) {
		switch (fPDTO.getType()) {
		case FinalFlight.TYPE:
			return new FinalFlight((FinalFlightDTO) fPDTO);
		case FinalHotel.TYPE:
			return new FinalHotel((FinalHotelDTO) fPDTO);
		case FinalExcursion.TYPE:
			return new FinalExcursion((FinalExcursionDTO) fPDTO);
		default:
			throw new IllegalArgumentException("No such type: "+fPDTO.getType());
		}
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void update(FinalProductDTO fP) {
		switch (fP.getType()) {
		case FinalFlight.TYPE:
			FinalFlightDTO fFDTO = (FinalFlightDTO) fP;
			FinalFlight fF = flightFromRelativeID(fP.getId());
			fF.setDeparture(fFDTO.getDeparture());
			em.merge(fF);
			break;
		case FinalHotel.TYPE:
			FinalHotelDTO fHDTO = (FinalHotelDTO) fP;
			FinalHotel fH = hotelFromRelativeID(fP.getId());
			fH.setCheckIn(fHDTO.getCheckIn());
			fH.setCheckOut(fHDTO.getCheckOut());
			em.merge(fH);
			break;
		case FinalExcursion.TYPE:
			FinalExcursionDTO fEDTO = (FinalExcursionDTO) fP;
			FinalExcursion fE = excursionFromRelativeID(fP.getId());
			fE.setDate(fEDTO.getDate());
			em.merge(fE);
			break;
		default:
			throw new IllegalArgumentException("No such type: "+fP.getType());
		}
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void remove(FinalProductDTO fP) {
		switch (fP.getType()) {
		case FinalFlight.TYPE:
			em.remove(flightFromRelativeID(fP.getId()));
			break;
		case FinalHotel.TYPE:
			em.remove(hotelFromRelativeID(fP.getId()));
			break;
		case FinalExcursion.TYPE:
			em.remove(excursionFromRelativeID(fP.getId()));
			break;
		default:
			throw new IllegalArgumentException("No such type: "+fP.getType());
		}
		
		usrMgr.getPrincipalUser().freeID(fP.getId());
	}

	private FinalFlightDTO buildFlightDTO(FinalFlight in) {
		FinalFlightDTO out = new FinalFlightDTO();
		out.setId(in.getIdRelative());
		out.setDeparture(in.getDeparture());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	private FinalHotelDTO buildHotelDTO(FinalHotel in) {
		FinalHotelDTO out = new FinalHotelDTO();
		out.setId(in.getIdRelative());
		out.setCheckIn(in.getCheckIn());
		out.setCheckOut(in.getCheckOut());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	private FinalExcursionDTO buildExcursionDTO(FinalExcursion in) {
		FinalExcursionDTO out = new FinalExcursionDTO();
		out.setId(in.getIdRelative());
		out.setDate(in.getDate());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	@Override
	public FinalProductDTO getByID(int id, String type) {
		switch (type) {
		case FinalFlight.TYPE:
			return buildFlightDTO(flightFromRelativeID(id));
		case FinalHotel.TYPE:
			return buildHotelDTO(hotelFromRelativeID(id));
		case FinalExcursion.TYPE:
			return buildExcursionDTO(excursionFromRelativeID(id));
		default:
			throw new IllegalArgumentException("No such type: "+type);
		}
	}

	private FinalFlight flightFromRelativeID(int id) {
		return em.createQuery("SELECT t FROM FinalFlight t WHERE t.idRelative = :id AND t.user = :user ", FinalFlight.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();		
	}

	private FinalHotel hotelFromRelativeID(int id) {
		return em.createQuery("SELECT t FROM FinalHotel t WHERE t.idRelative = :id AND t.user = :user ", FinalHotel.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();		
	}

	private FinalExcursion excursionFromRelativeID(int id) {
		System.out.println("Searching for excursion numnber: "+id);
		return em.createQuery("SELECT t FROM FinalExcursion t WHERE t.idRelative = :id AND t.user = :user ", FinalExcursion.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();		
	}

	/* oh my */ // TODO try em.find(FinalProduct.class, id)
	@Override
	public FinalProductDTO getByID(int id) {
		FinalFlight possibleFlight;
		FinalHotel possibleHotel;
		FinalExcursion possibleExcursion;

		try{
			possibleFlight = flightFromRelativeID(id);
		} catch (NoResultException e){
			possibleFlight = null;
		}
		try{
			possibleHotel = hotelFromRelativeID(id);
		} catch (NoResultException e){
			possibleHotel = null;
		}
		try{
			possibleExcursion = excursionFromRelativeID(id);
		} catch (NoResultException e){
			possibleExcursion = null;
		}

		if(possibleFlight!=null){
			return buildDTO(possibleFlight);
		} else
			if(possibleHotel!=null){
				return buildHotelDTO(possibleHotel);
			} else
				if(possibleExcursion!=null){
					return buildExcursionDTO(possibleExcursion);
				} else {
					throw new IllegalArgumentException("No final products with ID: "+id);
				}
	}

	public FinalProduct fromRelativeID(int id, String type) {
		switch (type) {
		case FinalFlight.TYPE:
			return flightFromRelativeID(id);
		case FinalHotel.TYPE:
			return hotelFromRelativeID(id);
		case FinalExcursion.TYPE:
			return excursionFromRelativeID(id);
		default:
			throw new IllegalArgumentException("No such type: "+type);
		}
	}

	public FinalProductDTO buildDTO(FinalProduct fP) {
		FinalProductDTO out = null;
		switch (fP.getProduct().getType()) {
		case FinalFlight.TYPE:
			out = buildFlightDTO((FinalFlight)fP);
			break;
		case FinalHotel.TYPE:
			out = buildHotelDTO((FinalHotel)fP);
			break;
		case FinalExcursion.TYPE:
			out = buildExcursionDTO((FinalExcursion)fP);
			break;
		default:
			throw new IllegalArgumentException("No such type: "+fP.getProduct().getType());
		}
		return out;
	}

}
