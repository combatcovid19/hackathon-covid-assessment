package com.hackathon.covid.assessment.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssesseProfile {

	@JsonProperty
	String assesseFirstName;

	@JsonProperty
	String assesseLastName;

	@JsonProperty
	Date dateOfBirth;

	@JsonProperty
	String genderId;

	@JsonProperty
	String emailId;

	@JsonProperty
	String mobileNumber;

	@JsonProperty
	String whatsAppNumber;

	@JsonProperty
	String countryCode;

	@JsonProperty
	String stateCode;

	@JsonProperty
	String city;

	@JsonProperty
	String districtOrCountyName;

	@JsonProperty
	String addressLine1;

	@JsonProperty
	long zipcode;

	@JsonProperty
	BigDecimal assessmentScore;

}
