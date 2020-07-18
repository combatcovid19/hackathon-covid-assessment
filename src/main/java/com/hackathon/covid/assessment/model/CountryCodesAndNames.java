package com.hackathon.covid.assessment.model;

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
public class CountryCodesAndNames {

	protected String countryCode;

	protected String countryName;

}