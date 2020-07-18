package com.hackathon.covid.assessment.model;

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
public class AssessmentAnswerInfo {
	
	private List<AssessmentAnswer> assessmentAnswers;	
	private Profile_BiomedicalAuthorityInfo profileAndBiomedicalAuthorityInfo;

}
