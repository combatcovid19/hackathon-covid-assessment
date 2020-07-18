package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the State database table.
 * 
 */
@Embeddable
public class StatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="State_Code")
	private String state_Code;

	@Column(name="Country_Code", insertable=false, updatable=false)
	private String country_Code;

	public StatePK() {
	}
	public String getState_Code() {
		return this.state_Code;
	}
	public void setState_Code(String state_Code) {
		this.state_Code = state_Code;
	}
	public String getCountry_Code() {
		return this.country_Code;
	}
	public void setCountry_Code(String country_Code) {
		this.country_Code = country_Code;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StatePK)) {
			return false;
		}
		StatePK castOther = (StatePK)other;
		return 
			this.state_Code.equals(castOther.state_Code)
			&& this.country_Code.equals(castOther.country_Code);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.state_Code.hashCode();
		hash = hash * prime + this.country_Code.hashCode();
		
		return hash;
	}
}