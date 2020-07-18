package com.hackathon.covid.assessment.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.covid.assessment.model.AssesseProfile;
import com.hackathon.covid.assessment.model.AssessmentAnswer;
import com.hackathon.covid.assessment.model.AssessmentAnswerInfo;
import com.hackathon.covid.assessment.model.Assessment_Questionnaire;
import com.hackathon.covid.assessment.model.CountryCodesAndNames;
import com.hackathon.covid.assessment.model.StateCodesAndNames;
import com.hackathon.covid.assessment.repo.AssessmentRepo;
import com.hackathon.covid.assessment.service.AssessmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HCL Technologies
 *
 */
@RestController
@RequestMapping(value = "/assessment", produces = "application/json")
@Api(value = "/assessment")
@Slf4j
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

	@Autowired
	AssessmentRepo assessmentRepo;

	@ApiOperation(value = "This API accepts requests to get country code and name", notes = "This API returns country code and name by accepting the requests.", response = Object.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 404, message = "Invalid Request", response = Object.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Object.class) })
	@GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getCountryCodeAndCountryNameList() {
		log.info("GET API Called to retrieve Country Names and Codes ...");

		ResponseEntity<?> responseEntity = null;
		List<CountryCodesAndNames> countryCodesAndNamesList = new ArrayList<>();

		countryCodesAndNamesList = assessmentService.getCountryCodeAndNames();

		if (!CollectionUtils.isEmpty(countryCodesAndNamesList)) {
			responseEntity = new ResponseEntity<>(countryCodesAndNamesList, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("Error while retrieving country codes and names list",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@ApiOperation(value = "This API accepts requests to get state code and name", notes = "This API returns state code and name by accepting the requests.", response = Object.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 404, message = "Invalid Request", response = Object.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Object.class) })
	@GetMapping(value = "/{countryCode}/states/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getStatesList(@PathVariable(name = "countryCode", required = true) String countryCode) {
		log.info("GET API Called to retrieve State Names and Codes ...");

		ResponseEntity<?> responseEntity = null;
		List<StateCodesAndNames> stateCodesAndNamesList = new ArrayList<>();

		stateCodesAndNamesList = assessmentService.getStateCodeAndNames(countryCode);

		if (!CollectionUtils.isEmpty(stateCodesAndNamesList)) {
			responseEntity = new ResponseEntity<>(stateCodesAndNamesList, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("Error while retrieving state codes and names list",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@ApiOperation(value = "This API accepts assesse profile and store in datastore.", notes = "This API accepts assesse profile and store in datastore.", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully Stored Profile in DataStore /  Retrieved Questionnaire from DataStore", response = String.class),
			@ApiResponse(code = 404, message = "Invalid", response = Object.class),
			@ApiResponse(code = 500, message = "Internal error", response = Object.class) })
	@PutMapping(value = "/create")
	@ResponseBody
	public ResponseEntity<?> createAssesseProfile(@RequestBody AssesseProfile assesseProfile) {
		log.info("Create Assesse Profile based on user request");
		ResponseEntity<?> responseEntity = null;
		Assessment_Questionnaire assessmentQuestionnaire = null;

		if (null == assesseProfile) {
			log.error("Either missing templateName or Invalid template file");
			throw new RuntimeException("Required Assesse Profile Details to store in database");
		}

		assessmentQuestionnaire = assessmentService.createProfile(assesseProfile);

		if (null != assessmentQuestionnaire) {
			responseEntity = new ResponseEntity<Object>(assessmentQuestionnaire, HttpStatus.CREATED);
		} else {
			responseEntity = new ResponseEntity<Object>("Unable to retrieve the assessment questionnaire",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@ApiOperation(value = "This API save answers given by assesse for the questionnaire.", notes = "This API save answers given by assesse for the questionnaire.", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully Saved Assessment Answers in DataStore", response = String.class),
			@ApiResponse(code = 404, message = "Invalid", response = Object.class),
			@ApiResponse(code = 500, message = "Internal error", response = Object.class) })
	@PostMapping(value = "/save")
	@ResponseBody
	public ResponseEntity<?> saveAssesseProfile(@RequestBody AssessmentAnswerInfo assessmentAnswerInfo) {
		log.info("Save Assessment Answers based on user request");
		ResponseEntity<?> responseEntity = null;

		if (null == assessmentAnswerInfo) {
			log.error("Either missing assessmentAnswers");
			throw new RuntimeException("Required Assessment Answers to store in database");
		}

		boolean flag = assessmentService.saveAssessmentAnswers(assessmentAnswerInfo);

		if (flag) {
			responseEntity = new ResponseEntity<Object>(flag, HttpStatus.CREATED);
		} else {
			responseEntity = new ResponseEntity<Object>("Unable to save the assessment answers for questionnaire",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

}
