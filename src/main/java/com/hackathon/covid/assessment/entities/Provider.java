package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the Provider database table.
 * 
 */
@Entity
@NamedQuery(name="Provider.findAll", query="SELECT p FROM Provider p")
public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Provider_Code")
	private long provider_Code;

	@Column(name="Provider_City_Name")
	private String provider_City_Name;

	@Column(name="Provider_District_Or_County_Name")
	private String provider_District_Or_County_Name;

	@Column(name="Provider_Email_Id")
	private String provider_Email_Id;

	@Column(name="Provider_Latitude")
	private BigDecimal provider_Latitude;

	@Column(name="Provider_Longitude")
	private BigDecimal provider_Longitude;

	@Column(name="Provider_Phone_Number1")
	private String provider_Phone_Number1;

	@Column(name="Provider_Phone_Number2")
	private String provider_Phone_Number2;

	@Column(name="Provider_Phone_Number3")
	private String provider_Phone_Number3;

	@Column(name="Provider_Pin_Zip_Code")
	private short provider_Pin_Zip_Code;

	@Column(name="Provider_Street_Address")
	private String provider_Street_Address;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Country_Code", referencedColumnName="Country_Code"),
		@JoinColumn(name="State_Code", referencedColumnName="State_Code")
		})
	private State state;

	public Provider() {
	}

	public long getProvider_Code() {
		return this.provider_Code;
	}

	public void setProvider_Code(long provider_Code) {
		this.provider_Code = provider_Code;
	}

	public String getProvider_City_Name() {
		return this.provider_City_Name;
	}

	public void setProvider_City_Name(String provider_City_Name) {
		this.provider_City_Name = provider_City_Name;
	}

	public String getProvider_District_Or_County_Name() {
		return this.provider_District_Or_County_Name;
	}

	public void setProvider_District_Or_County_Name(String provider_District_Or_County_Name) {
		this.provider_District_Or_County_Name = provider_District_Or_County_Name;
	}

	public String getProvider_Email_Id() {
		return this.provider_Email_Id;
	}

	public void setProvider_Email_Id(String provider_Email_Id) {
		this.provider_Email_Id = provider_Email_Id;
	}

	public BigDecimal getProvider_Latitude() {
		return this.provider_Latitude;
	}

	public void setProvider_Latitude(BigDecimal provider_Latitude) {
		this.provider_Latitude = provider_Latitude;
	}

	public BigDecimal getProvider_Longitude() {
		return this.provider_Longitude;
	}

	public void setProvider_Longitude(BigDecimal provider_Longitude) {
		this.provider_Longitude = provider_Longitude;
	}

	public String getProvider_Phone_Number1() {
		return this.provider_Phone_Number1;
	}

	public void setProvider_Phone_Number1(String provider_Phone_Number1) {
		this.provider_Phone_Number1 = provider_Phone_Number1;
	}

	public String getProvider_Phone_Number2() {
		return this.provider_Phone_Number2;
	}

	public void setProvider_Phone_Number2(String provider_Phone_Number2) {
		this.provider_Phone_Number2 = provider_Phone_Number2;
	}

	public String getProvider_Phone_Number3() {
		return this.provider_Phone_Number3;
	}

	public void setProvider_Phone_Number3(String provider_Phone_Number3) {
		this.provider_Phone_Number3 = provider_Phone_Number3;
	}

	public short getProvider_Pin_Zip_Code() {
		return this.provider_Pin_Zip_Code;
	}

	public void setProvider_Pin_Zip_Code(short provider_Pin_Zip_Code) {
		this.provider_Pin_Zip_Code = provider_Pin_Zip_Code;
	}

	public String getProvider_Street_Address() {
		return this.provider_Street_Address;
	}

	public void setProvider_Street_Address(String provider_Street_Address) {
		this.provider_Street_Address = provider_Street_Address;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}