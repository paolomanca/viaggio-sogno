package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalExcursionDTO;

import java.util.Date;


/**
 * The persistent class for the FINAL_EXCURSION database table.
 * 
 */
@Entity
@Table(name="FINAL_EXCURSION")
@NamedQuery(name="FinalExcursion.findAll", query="SELECT f FROM FinalExcursion f")
public class FinalExcursion extends FinalProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TYPE = Product.EXCURSION;
	
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
	
}