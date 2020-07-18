package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Gender database table.
 * 
 */
@Entity
@NamedQuery(name="Gender.findAll", query="SELECT g FROM Gender g")
public class Gender implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Gender_Code")
	private String gender_Code;

	@Column(name="Gender_Name")
	private String gender_Name;

	//bi-directional many-to-one association to Profile
	@OneToMany(mappedBy="gender")
	private List<Profile> profiles;

	public Gender() {
	}

	public String getGender_Code() {
		return this.gender_Code;
	}

	public void setGender_Code(String gender_Code) {
		this.gender_Code = gender_Code;
	}

	public Object getGender_Name() {
		return this.gender_Name;
	}

	public void setGender_Name(String gender_Name) {
		this.gender_Name = gender_Name;
	}

	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setGender(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setGender(null);

		return profile;
	}

}