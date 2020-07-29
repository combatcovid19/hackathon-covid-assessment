package com.hackathon.covid.assessment.model;

import java.util.HashSet;
import java.util.Set;

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
public class Assessment_Questionnaire {

	String countryCode;
	String biomedical_Authority_Code;	
	String profileId;
	String firstName;

	Set<Assessment_Question_Model> assessmentQuestions = new HashSet<>();

}
