package com.hackathon.covid.assessment.model;

import javax.persistence.Column;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;

@Builder

@NoArgsConstructor

@AllArgsConstructor

@Data

@Component

public class Provider_Model {

	private long provider_Code;

	private String provider_City_Name;

	private String provider_District_Or_County_Name;

	private String provider_Email_Id;

	private String provider_Latitude;

	private String provider_Longitude;

	private String provider_Phone_Number1;

	private String provider_Phone_Number2;

	private String provider_Phone_Number3;

	private long provider_Pin_Zip_Code;

	private String provider_Street_Address;

	private String distance;

}