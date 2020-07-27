package com.hackathon.covid.assessment.model;

import java.util.ArrayList;
import java.util.List;

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
public class AssessmentScoreAndProviders {
	
	int assessmentScore = 0;
	List<Provider_Model> nearestProviders = new ArrayList<>();

}
