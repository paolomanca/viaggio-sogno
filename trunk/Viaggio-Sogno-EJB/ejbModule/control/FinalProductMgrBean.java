package control;

import javax.annotation.Resource;
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

	@Override
	public void add(FinalProductDTO fP) {
		if(fP instanceof FinalFlightDTO){
			FinalFlightDTO fFDTO = (FinalFlightDTO)fP;
			FinalFlight fF = new FinalFlight(fFDTO);
			fF.setFinalPackage(new FinalPackage(fFDTO.getFinalPackage()));
			fF.setProduct(new Product(fFDTO.getProduct()));
			em.persist(fF);
		} else
		if(fP instanceof FinalHotelDTO){
			FinalHotelDTO fHDTO = (FinalHotelDTO)fP;
			FinalHotel fH = new FinalHotel(fHDTO);
			fH.setFinalPackage(new FinalPackage(fHDTO.getFinalPackage()));
			fH.setProduct(new Product(fHDTO.getProduct()));
			em.persist(fH);
		} else
		if(fP instanceof FinalExcursionDTO){
			FinalExcursionDTO fEDTO = (FinalExcursionDTO)fP;
			FinalExcursion fE = new FinalExcursion(fEDTO);
			fE.setFinalPackage(new FinalPackage(fEDTO.getFinalPackage()));
			fE.setProduct(new Product(fEDTO.getProduct()));
			em.persist(fE);
		}
	}

	@Override
	public void update(FinalProductDTO fP) {
		if(fP instanceof FinalFlightDTO){
			em.merge(em.find(FinalFlight.class, fP.getId()));
		} else
		if(fP instanceof FinalHotelDTO){
			em.merge(em.find(FinalHotel.class, fP.getId()));
		} else
		if(fP instanceof FinalExcursionDTO){
			em.merge(em.find(FinalExcursion.class, fP.getId()));
		}
	}

	@Override
	public void remove(FinalProductDTO fP) {
		if(fP instanceof FinalFlightDTO){
			em.remove(em.find(FinalFlight.class, fP.getId()));
		} else
		if(fP instanceof FinalHotelDTO){
			em.remove(em.find(FinalHotel.class, fP.getId()));
		} else
		if(fP instanceof FinalExcursionDTO){
			em.remove(em.find(FinalExcursion.class, fP.getId()));
		}
	}

	public FinalFlightDTO buildFlightDTO(FinalFlight in) {
		FinalFlightDTO out = new FinalFlightDTO();
		out.setId(in.getIdfinalFlight());
		out.setDeparture(in.getDeparture());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	public FinalHotelDTO buildHotelDTO(FinalHotel in) {
		FinalHotelDTO out = new FinalHotelDTO();
		out.setId(in.getIdfinalHotel());
		out.setCheckIn(in.getCheckIn());
		out.setCheckOut(in.getCheckOut());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}
	
	public FinalExcursionDTO buildExcursionDTO(FinalExcursion in) {
		FinalExcursionDTO out = new FinalExcursionDTO();
		out.setId(in.getIdfinalExcursion());
		out.setDate(in.getDate());
		out.setProduct(prdMgr.buildDTO(in.getProduct()));
		return out;
	}

	@Override
	public FinalProductDTO getByID(int id, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
