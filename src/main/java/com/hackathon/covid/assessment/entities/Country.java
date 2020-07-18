package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Country_Code")
	private String country_Code;

	@Column(name="Country_Name")
	private String country_Name;

	//bi-directional one-to-one association to Biomedical_Authority
	@OneToOne(mappedBy="country")
	private Biomedical_Authority biomedicalAuthority;

	//bi-directional many-to-one association to Profile
	@OneToMany(mappedBy="country")
	private List<Profile> profiles;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="country")
	private List<State> states;

	public Country() {
	}

	public String getCountry_Code() {
		return this.country_Code;
	}

	public void setCountry_Code(String country_Code) {
		this.country_Code = country_Code;
	}

	public String getCountry_Name() {
		return this.country_Name;
	}

	public void setCountry_Name(String country_Name) {
		this.country_Name = country_Name;
	}

	public Biomedical_Authority getBiomedicalAuthority() {
		return this.biomedicalAuthority;
	}

	public void setBiomedicalAuthority(Biomedical_Authority biomedicalAuthority) {
		this.biomedicalAuthority = biomedicalAuthority;
	}

	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setCountry(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setCountry(null);

		return profile;
	}

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State addState(State state) {
		getStates().add(state);
		state.setCountry(this);

		return state;
	}

	public State removeState(State state) {
		getStates().remove(state);
		state.setCountry(null);

		return state;
	}

}