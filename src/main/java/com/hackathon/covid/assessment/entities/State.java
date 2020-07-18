package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the State database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StatePK id;

	@Column(name="State_Name")
	private String state_Name;

	//bi-directional many-to-one association to Profile
	@OneToMany(mappedBy="state")
	private List<Profile> profiles;

	//bi-directional many-to-one association to Provider
	@OneToMany(mappedBy="state")
	private List<Provider> providers;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="Country_Code", insertable = false, updatable = false)
	private Country country;

	public State() {
	}

	public StatePK getId() {
		return this.id;
	}

	public void setId(StatePK id) {
		this.id = id;
	}

	public String getState_Name() {
		return this.state_Name;
	}

	public void setState_Name(String state_Name) {
		this.state_Name = state_Name;
	}

	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setState(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setState(null);

		return profile;
	}

	public List<Provider> getProviders() {
		return this.providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public Provider addProvider(Provider provider) {
		getProviders().add(provider);
		provider.setState(this);

		return provider;
	}

	public Provider removeProvider(Provider provider) {
		getProviders().remove(provider);
		provider.setState(null);

		return provider;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}