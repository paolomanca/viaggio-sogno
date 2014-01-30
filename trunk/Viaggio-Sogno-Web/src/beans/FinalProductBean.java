package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dto.FinalExcursionDTO;
import dto.FinalFlightDTO;
import dto.FinalHotelDTO;
import dto.FinalProductDTO;
import entitymanagers.FinalPackageMgr;
import entitymanagers.FinalProductMgr;
import entitymanagers.ProductMgr;

@ManagedBean(name = "finalProductBean")
@RequestScoped
public class FinalProductBean {

	@EJB
	protected FinalProductMgr fPrMgr;

	@EJB
	protected ProductMgr prMgr;

	@EJB
	protected FinalPackageMgr fPkgMgr;

	@ManagedProperty(value = "#{param.prID}")
	protected int prID;

	@ManagedProperty(value = "#{param.fPrID}")
	protected int fPrID;

	@ManagedProperty(value = "#{param.fPkgID}")
	protected int fPkgID;

	protected FinalProductDTO finalProduct;

	@ManagedProperty(value = "#{param.act}")
	protected String act;

	@ManagedProperty(value = "#{param.type}")
	protected String type;

	@PostConstruct
	private void init() {

		if (act != null && type != null) {
			if (act.equalsIgnoreCase("create")) {
				
				switch (type) {
				case "FLIGHT":
					finalProduct = new FinalFlightDTO();
					break;
				case "HOTEL":
					finalProduct = new FinalHotelDTO();
					break;
				case "EXCURSION":
					finalProduct = new FinalExcursionDTO();
					break;
				}
				
				finalProduct.setProduct(prMgr.getByID(prID));

			} else {
				finalProduct = fPrMgr.getByID(fPrID, type.toUpperCase());
			}
		}

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrID() {
		return prID;
	}

	public void setPrID(int prID) {
		this.prID = prID;
	}

	public int getfPrID() {
		return fPrID;
	}

	public void setfPrID(int id) {
		this.fPrID = id;
	}

	public int getfPkgID() {
		return fPkgID;
	}

	public void setfPkgID(int fPkgID) {
		this.fPkgID = fPkgID;
	}

	public FinalProductDTO getFinalProduct() {
		return finalProduct;
	}

	public void setFinalProduct(FinalProductDTO finalProduct) {
		this.finalProduct = finalProduct;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String add() {
		fPkgMgr.finalizeProduct(fPkgMgr.getByMyID(fPkgID), finalProduct);
		return "finalPackage?act=show&amp;fPkgID=" + fPkgID + "&amp;faces-redirect=true";
	}
	
	public String update() {
		fPrMgr.update(finalProduct);
		return "finalPackage?act=show&amp;fPkgID=" + fPkgID + "&amp;faces-redirect=true";
	}

	public String remove() {
		fPrMgr.remove(finalProduct);
		return "finalPackage?act=show&amp;fPkgID=" + fPkgID + "&amp;faces-redirect=true";
	}

}
