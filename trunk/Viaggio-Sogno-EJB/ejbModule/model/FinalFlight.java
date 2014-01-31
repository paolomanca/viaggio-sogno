package model;

import java.io.Serializable;

import javax.persistence.*;

import dto.FinalFlightDTO;
import dto.ProductDTO;

import java.util.Date;


/**
 * The persistent class for the FINAL_FLIGHT database table.
 * 
 */
@Entity
@NamedQuery(name="FinalFlight.findAll", query="SELECT f FROM FinalFlight f")
public class FinalFlight extends FinalProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TYPE = ProductDTO.FLIGHT;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date departure;

	public FinalFlight() {
	}

	public FinalFlight(FinalFlightDTO fP) {
		this.setId(fP.getId());
		this.departure = fP.getDeparture();
	}

	public Date getDeparture() {
		return this.departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	@Override
	public FinalFlight shallowCopy() {
		FinalFlight out = new FinalFlight();
		out.setDeparture(departure);
		out.setId(getId());
		out.setIdRelative(getIdRelative());
		return out;
	}

}