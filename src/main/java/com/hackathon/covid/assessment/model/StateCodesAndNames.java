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
public class StateCodesAndNames {
	
	protected String stateCode;

	protected String stateName;

}
