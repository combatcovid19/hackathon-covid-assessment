package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Profile database table.
 * 
 */
@Entity
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Profile_Code")
	private String profile_Code;

	@Column(name="Assessment_Score")
	private BigDecimal assessment_Score;

	@Column(name="City_Name")
	private String city_Name;

	@Column(name="District_Or_County_Name")
	private String district_Or_County_Name;

	@Column(name="DOB")
	private Date dob;

	@Column(name="Email_Id")
	private String email_Id;

	@Column(name="First_Name")
	private String first_Name;

	@Column(name="Last_Name")
	private String last_Name;

	@Column(name="Mobile_Number")
	private String mobile_Number;

	@Column(name="Pin_Zip_Code")
	private long pin_Zip_Code;

	@Column(name="Street_Address")
	private String street_Address;

	@Column(name="Whatsapp_Number")
	private String whatsapp_Number;

	//bi-directional many-to-one association to Assessment_Answer
	@OneToMany(mappedBy="profile")
	private List<Assessment_Answer> assessmentAnswers;

	//bi-directional many-to-one association to Contact_Tracing
	@OneToMany(mappedBy="profile")
	private List<Contact_Tracing> contactTracings;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="Country_Code")
	private Country country;

	//bi-directional many-to-one association to Gender
	@ManyToOne
	@JoinColumn(name="Gender_Code")
	private Gender gender;
	
	@Column(name="State_Code")
	private String state_Code;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Country_Code", referencedColumnName="Country_Code", insertable = false, updatable = false),
		@JoinColumn(name="State_Code", referencedColumnName="State_Code", insertable = false, updatable = false)
		})
	private State state;

	public Profile() {
	}

	public String getProfile_Code() {
		return this.profile_Code;
	}

	public void setProfile_Code(String profile_Code) {
		this.profile_Code = profile_Code;
	}

	public BigDecimal getAssessment_Score() {
		return this.assessment_Score;
	}

	public void setAssessment_Score(BigDecimal assessment_Score) {
		this.assessment_Score = assessment_Score;
	}

	public String getCity_Name() {
		return this.city_Name;
	}

	public void setCity_Name(String city_Name) {
		this.city_Name = city_Name;
	}

	public String getDistrict_Or_County_Name() {
		return this.district_Or_County_Name;
	}

	public void setDistrict_Or_County_Name(String district_Or_County_Name) {
		this.district_Or_County_Name = district_Or_County_Name;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail_Id() {
		return this.email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getMobile_Number() {
		return this.mobile_Number;
	}

	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}

	public long getPin_Zip_Code() {
		return this.pin_Zip_Code;
	}

	public void setPin_Zip_Code(long pin_Zip_Code) {
		this.pin_Zip_Code = pin_Zip_Code;
	}

	public String getStreet_Address() {
		return this.street_Address;
	}

	public void setStreet_Address(String street_Address) {
		this.street_Address = street_Address;
	}

	public String getWhatsapp_Number() {
		return this.whatsapp_Number;
	}

	public void setWhatsapp_Number(String whatsapp_Number) {
		this.whatsapp_Number = whatsapp_Number;
	}

	public List<Assessment_Answer> getAssessmentAnswers() {
		return this.assessmentAnswers;
	}

	public void setAssessmentAnswers(List<Assessment_Answer> assessmentAnswers) {
		this.assessmentAnswers = assessmentAnswers;
	}

	public Assessment_Answer addAssessmentAnswer(Assessment_Answer assessmentAnswer) {
		getAssessmentAnswers().add(assessmentAnswer);
		assessmentAnswer.setProfile(this);

		return assessmentAnswer;
	}

	public Assessment_Answer removeAssessmentAnswer(Assessment_Answer assessmentAnswer) {
		getAssessmentAnswers().remove(assessmentAnswer);
		assessmentAnswer.setProfile(null);

		return assessmentAnswer;
	}

	public List<Contact_Tracing> getContactTracings() {
		return this.contactTracings;
	}

	public void setContactTracings(List<Contact_Tracing> contactTracings) {
		this.contactTracings = contactTracings;
	}

	public Contact_Tracing addContactTracing(Contact_Tracing contactTracing) {
		getContactTracings().add(contactTracing);
		contactTracing.setProfile(this);

		return contactTracing;
	}

	public Contact_Tracing removeContactTracing(Contact_Tracing contactTracing) {
		getContactTracings().remove(contactTracing);
		contactTracing.setProfile(null);

		return contactTracing;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getState_Code() {
		return state_Code;
	}

	public void setState_Code(String state_Code) {
		this.state_Code = state_Code;
	}
	
	

}