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
public class AssessmentAnswer {

	private String assessmentQuestionCode;
	private String answerOptionCode;
	

}
