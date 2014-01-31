package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalExcursionDTO;
import dto.ProductDTO;

import java.util.Date;


/**
 * The persistent class for the FINAL_EXCURSION database table.
 * 
 */
@Entity
@NamedQuery(name="FinalExcursion.findAll", query="SELECT f FROM FinalExcursion f")
public class FinalExcursion extends FinalProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TYPE = ProductDTO.EXCURSION;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;
	
	public FinalExcursion() {
	}

	public FinalExcursion(FinalExcursionDTO fEDTO) {
		setId(fEDTO.getId());
		this.date = fEDTO.getDate();
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public FinalExcursion shallowCopy() {
		FinalExcursion out = new FinalExcursion();
		out.setDate(date);
		out.setId(getId());
		out.setIdRelative(getIdRelative());
		return out;
	}
	
}