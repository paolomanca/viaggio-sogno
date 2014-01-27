package control;

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
	public void add(FinalProductDTO fP) {
		if(fP instanceof FinalFlightDTO){
			FinalFlightDTO fFDTO = (FinalFlightDTO)fP;
			FinalFlight fF = new FinalFlight(fFDTO);
			fF.setFinalPackage(new FinalPackage(fFDTO.getFinalPackage()));
			fF.setProduct(new Product(fFDTO.getProduct()));
			fF.setIdfinalFlightRelative(usrMgr.findByEmail(context.getCallerPrincipal().getName()).nextID());
			em.persist(fF);
		} else
			if(fP instanceof FinalHotelDTO){
				FinalHotelDTO fHDTO = (FinalHotelDTO)fP;
				FinalHotel fH = new FinalHotel(fHDTO);
				fH.setFinalPackage(new FinalPackage(fHDTO.getFinalPackage()));
				fH.setProduct(new Product(fHDTO.getProduct()));
				fH.setIdfinalHotelRelative(usrMgr.findByEmail(context.getCallerPrincipal().getName()).nextID());
				em.persist(fH);
			} else
				if(fP instanceof FinalExcursionDTO){
					FinalExcursionDTO fEDTO = (FinalExcursionDTO)fP;
					FinalExcursion fE = new FinalExcursion(fEDTO);
					fE.setFinalPackage(new FinalPackage(fEDTO.getFinalPackage()));
					fE.setProduct(new Product(fEDTO.getProduct()));
					fE.setIdfinalExcursionRelative(usrMgr.findByEmail(context.getCallerPrincipal().getName()).nextID());
					em.persist(fE);
				}
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void update(FinalProductDTO fP) {
		if(fP instanceof FinalFlightDTO){
			FinalFlightDTO fFDTO = (FinalFlightDTO) fP;
			FinalFlight fF = flightFromRelativeID(fP.getId());
			fF.setDeparture(fFDTO.getDeparture());
			em.merge(fF);
		} else
			if(fP instanceof FinalHotelDTO){
				FinalHotelDTO fHDTO = (FinalHotelDTO) fP;
				FinalHotel fH = hotelFromRelativeID(fP.getId());
				fH.setCheckIn(fHDTO.getCheckIn());
				fH.setCheckOut(fHDTO.getCheckOut());
				em.merge(fH);
			} else
				if(fP instanceof FinalExcursionDTO){
					FinalExcursionDTO fEDTO = (FinalExcursionDTO) fP;
					FinalExcursion fE = excursionFromRelativeID(fP.getId());
					fE.setDate(fEDTO.getDate());
					em.merge(fE);
				}
	}

	@Override
	@RolesAllowed({Group._CUSTOMER})
	public void remove(FinalProductDTO fP) {
		if(fP instanceof FinalFlightDTO){
			em.remove(flightFromRelativeID(fP.getId()));			
		} else
			if(fP instanceof FinalHotelDTO){
				em.remove(hotelFromRelativeID(fP.getId()));
			} else
				if(fP instanceof FinalExcursionDTO){
					em.remove(excursionFromRelativeID(fP.getId()));
				}
		usrMgr.findByEmail(context.getCallerPrincipal().getName()).freeID(fP.getId());
	}

	public FinalFlightDTO buildFlightDTO(FinalFlight in) {
		FinalFlightDTO out = new FinalFlightDTO();
		out.setId(in.getIdfinalFlightRelative());
		out.setDeparture(in.getDeparture());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	public FinalHotelDTO buildHotelDTO(FinalHotel in) {
		FinalHotelDTO out = new FinalHotelDTO();
		out.setId(in.getIdfinalHotelRelative());
		out.setCheckIn(in.getCheckIn());
		out.setCheckOut(in.getCheckOut());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	public FinalExcursionDTO buildExcursionDTO(FinalExcursion in) {
		FinalExcursionDTO out = new FinalExcursionDTO();
		out.setId(in.getIdfinalExcursionRelative());
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
	
	public FinalFlight flightFromRelativeID(int id) {
		return em.createQuery("SELECT t FROM FinalFlight t WHERE t.idfinalFlightRelative = :id AND t.finalPackage.user = :user ", FinalFlight.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();		
	}
	
	public FinalHotel hotelFromRelativeID(int id) {
		return em.createQuery("SELECT t FROM FinalHotel t WHERE t.idfinalHotelRelative = :id AND t.finalPackage.user = :user ", FinalHotel.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();		
	}
	
	public FinalExcursion excursionFromRelativeID(int id) {
		return em.createQuery("SELECT t FROM FinalExcursion t WHERE t.idfinalExcursionRelative = :id AND t.finalPackage.user = :user ", FinalExcursion.class)
				.setParameter("user", usrMgr.getPrincipalUser()).setParameter("id", id).getSingleResult();		
	}

}
