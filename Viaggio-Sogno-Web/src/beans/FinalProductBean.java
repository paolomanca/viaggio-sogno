package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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
	
	@ManagedProperty(value = "#{param.fPrID}")
	protected int prID;
	
	@ManagedProperty(value = "#{param.fPrID}")
	protected int fPrID;
	
	@ManagedProperty(value = "#{param.fPkgID}")
	protected int fPkgID;
	
	protected FinalProductDTO finalProduct;

	@ManagedProperty(value = "#{param.act}")
	protected String act;


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
		fPrMgr.add(finalProduct);
		return "customer/showFinalPackage";
	}
	
	public String update() {
		fPrMgr.update(finalProduct);
		return "customer/showFinalPackage";
	}
	
	public String remove() {
		fPrMgr.remove(finalProduct);
		return "customer/showFinalPackage";
	}
	
}
