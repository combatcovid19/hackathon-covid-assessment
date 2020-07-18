package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Contact_Tracing database table.
 * 
 */
@Entity
@NamedQuery(name="Contact_Tracing.findAll", query="SELECT c FROM Contact_Tracing c")
public class Contact_Tracing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Contact_Tracing_Code")
	private long contact_Tracing_Code;

	@Column(name="Contact_Desc")
	private String contact_Desc;

	@Column(name="Contact_Phone_Number")
	private String contact_Phone_Number;

	@Column(name="Contact_Tracing_Email_Id")
	private String contact_Tracing_Email_Id;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="Profile_Code")
	private Profile profile;

	public Contact_Tracing() {
	}

	public long getContact_Tracing_Code() {
		return this.contact_Tracing_Code;
	}

	public void setContact_Tracing_Code(long contact_Tracing_Code) {
		this.contact_Tracing_Code = contact_Tracing_Code;
	}

	public String getContact_Desc() {
		return this.contact_Desc;
	}

	public void setContact_Desc(String contact_Desc) {
		this.contact_Desc = contact_Desc;
	}

	public String getContact_Phone_Number() {
		return this.contact_Phone_Number;
	}

	public void setContact_Phone_Number(String contact_Phone_Number) {
		this.contact_Phone_Number = contact_Phone_Number;
	}

	public String getContact_Tracing_Email_Id() {
		return this.contact_Tracing_Email_Id;
	}

	public void setContact_Tracing_Email_Id(String contact_Tracing_Email_Id) {
		this.contact_Tracing_Email_Id = contact_Tracing_Email_Id;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}