package com.hackathon.covid.assessment.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.covid.assessment.entities.Answer_Option;
import com.hackathon.covid.assessment.entities.Assessment_Answer;
import com.hackathon.covid.assessment.entities.Assessment_Question;
import com.hackathon.covid.assessment.entities.Biomedical_Authority;
import com.hackathon.covid.assessment.entities.Country;
import com.hackathon.covid.assessment.entities.Gender;
import com.hackathon.covid.assessment.entities.Profile;
import com.hackathon.covid.assessment.entities.State;
import com.hackathon.covid.assessment.entities.StatePK;
import com.hackathon.covid.assessment.model.AnswerInformation;
import com.hackathon.covid.assessment.model.AssesseProfile;
import com.hackathon.covid.assessment.model.AssessmentAnswer;
import com.hackathon.covid.assessment.model.AssessmentAnswerInfo;
import com.hackathon.covid.assessment.model.Assessment_Question_Model;
import com.hackathon.covid.assessment.model.Assessment_Questionnaire;
import com.hackathon.covid.assessment.model.CountryCodesAndNames;
import com.hackathon.covid.assessment.model.StateCodesAndNames;
import com.hackathon.covid.assessment.repo.AnswerOptionsRepo;
import com.hackathon.covid.assessment.repo.AssesseProfileRepo;
import com.hackathon.covid.assessment.repo.AssessmentAnswerRepo;
import com.hackathon.covid.assessment.repo.AssessmentQuestionRepo;
import com.hackathon.covid.assessment.repo.AssessmentRepo;
import com.hackathon.covid.assessment.repo.BiomedicalAuthorityRepo;
import com.hackathon.covid.assessment.repo.CountryRepo;
import com.hackathon.covid.assessment.repo.StateRepo;
import com.hackathon.covid.assessment.transformation.AssessmentTransformer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssessmentService {

	@Autowired
	AssessmentRepo assessmentRepo;

	@Autowired
	CountryRepo countryRepo;

	@Autowired
	StateRepo stateRepo;

	@Autowired
	AssesseProfileRepo assesseProfileRepo;

	@Autowired
	BiomedicalAuthorityRepo biomedicalAuthorityRepo;

	@Autowired
	AssessmentQuestionRepo assessmentQuestionRepo;

	@Autowired
	AnswerOptionsRepo answerOptionsRepo;

	@Autowired
	AssessmentAnswerRepo assessmentAnswerRepo;

	@Autowired
	AssessmentTransformer assessmentTransformer;

	@Autowired
	ObjectMapper objectMapper;

	public List<CountryCodesAndNames> getCountryCodeAndNames() {
		log.info("Get the list of Country codes and names");

		List<Country> countryEntities = new ArrayList<>();
		List<CountryCodesAndNames> countryCodesAndNamesList = new ArrayList<>();

		try {
			countryEntities = assessmentRepo.findAll();

			if (!CollectionUtils.isEmpty(countryEntities)) {
				countryCodesAndNamesList = assessmentTransformer.convertCountryEntityToModel(countryEntities);
			}
		} catch (Exception exception) {
			log.error("ERROR : while retrieving CountryEntity list from datastore", exception);
		}

		return countryCodesAndNamesList;

	}

	public List<StateCodesAndNames> getStateCodeAndNames(String countryCode) {
		log.info("Get the list of State codes and names");

		List<StateCodesAndNames> stateCodesAndNamesList = new ArrayList<>();
		Country country = new Country();
		List<State> stateEntitiesList = new ArrayList<>();
		StatePK statePK = new StatePK();
		Optional<State> optionalState = Optional.empty();

		try {

			country.setCountry_Code(countryCode);
			stateEntitiesList = stateRepo.findStateByCountry(country);

			if (!CollectionUtils.isEmpty(stateEntitiesList)) {
				stateCodesAndNamesList = assessmentTransformer.convertStateEntityToModel(stateEntitiesList);
			}
		} catch (Exception exception) {
			log.error("ERROR : while retrieving CountryEntity list from datastore", exception);
		}

		return stateCodesAndNamesList;
	}

	public Assessment_Questionnaire createProfile(AssesseProfile assesseProfile) {
		log.info("Get the list of State codes and names");

		Profile assesseProfileEntity = new Profile();
		Country country = new Country();
		Biomedical_Authority biomedicalAuthority = null;
		Assessment_Questionnaire assessment_Questionnaire = new Assessment_Questionnaire();
		Set<Assessment_Question_Model> assessmentQuestionsSet = new HashSet<>();

		try {

			assesseProfileEntity = assesseProfileRepo
					.save(convertAssessProfileToEntity(assesseProfile, assesseProfileEntity));

			assessment_Questionnaire.setProfileId(assesseProfileEntity.getProfile_Code());
			assessment_Questionnaire.setCountryCode(assesseProfile.getCountryCode());

			Optional<Country> countryOptional = countryRepo.findById(assesseProfile.getCountryCode());

			country = countryOptional.get();

			biomedicalAuthority = country.getBiomedicalAuthority();

			assessment_Questionnaire.setBiomedical_Authority_Code(biomedicalAuthority.getBiomedical_Authority_Code());

			List<Assessment_Question> questionsList = biomedicalAuthority.getAssessmentQuestions();

			if (!CollectionUtils.isEmpty(questionsList)) {

				for (Assessment_Question question : questionsList) {
					Assessment_Question_Model assessmentQuestion = new Assessment_Question_Model();
					assessmentQuestion.setQuestionId(question.getId().getAssessment_Question_Code());
					assessmentQuestion.setQuestionDescription(question.getAssessment_Question_Desc());
					List<Answer_Option> answerOptionsList = question.getAnswerOptions();

					if (!CollectionUtils.isEmpty(answerOptionsList)) {

						for (Answer_Option answerOption : answerOptionsList) {

							AnswerInformation answerInformation = new AnswerInformation();

							answerInformation.setAnswerId(answerOption.getId().getAnswer_Option_Code());
							answerInformation.setAnswerDescription(answerOption.getAnswer_Option_Desc());

							answerInformation.setAnswerType(answerOption.getAnswerType().getAnswer_Type_Code());
							answerInformation.setAnswerTypeId(answerOption.getAnswerType().getAnswer_Type_Desc());

							assessmentQuestion.getAnswerInformationList().add(answerInformation);
						}
					}

					assessmentQuestionsSet.add(assessmentQuestion);
				}
			}
			assessment_Questionnaire.getAssessmentQuestions().addAll(assessmentQuestionsSet);

		} catch (Exception exception) {
			log.error("ERROR : while retrieving CountryEntity list from datastore", exception);
		}

		return assessment_Questionnaire;

	}

	private Profile convertAssessProfileToEntity(AssesseProfile assesseProfile, Profile assesseProfileEntity) {

		assesseProfileEntity.setProfile_Code(new java.util.Random().nextLong());
		assesseProfileEntity.setFirst_Name(assesseProfile.getAssesseFirstName());
		assesseProfileEntity.setLast_Name(assesseProfile.getAssesseLastName());
		assesseProfileEntity.setDob(assesseProfile.getDateOfBirth());
		Gender gender = new Gender();
		gender.setGender_Code(assesseProfile.getGenderId());
		assesseProfileEntity.setGender(gender);
		assesseProfileEntity.setEmail_Id(assesseProfile.getEmailId());
		assesseProfileEntity.setMobile_Number(assesseProfile.getMobileNumber());
		assesseProfileEntity.setWhatsapp_Number(assesseProfile.getWhatsAppNumber());
		Country country = new Country();
		country.setCountry_Code(assesseProfile.getCountryCode());
		assesseProfileEntity.setCountry(country);
		State state = new State();
		StatePK statePK = new StatePK();
		statePK.setState_Code(assesseProfile.getStateCode());
		statePK.setCountry_Code(assesseProfile.getCountryCode());
		state.setId(statePK);
		assesseProfileEntity.setState(state);
		assesseProfileEntity.setState_Code(assesseProfile.getStateCode());
		assesseProfileEntity.setCity_Name(assesseProfile.getCity());
		assesseProfileEntity.setDistrict_Or_County_Name(assesseProfile.getDistrictOrCountyName());
		assesseProfileEntity.setStreet_Address(assesseProfile.getAddressLine1());
		assesseProfileEntity.setPin_Zip_Code(assesseProfile.getZipcode());
		assesseProfileEntity.setAssessment_Score(assesseProfile.getAssessmentScore());

		return assesseProfileEntity;
	}

	public boolean saveAssessmentAnswers(AssessmentAnswerInfo assessmentAnswerInfo) {

		List<Assessment_Answer> assessmentAnswerEntities = new ArrayList<>();
		boolean flag = false;

		try {
			for (AssessmentAnswer assessmentAnswerModel : assessmentAnswerInfo.getAssessmentAnswers()) {
				Assessment_Answer assessment_Answer = new Assessment_Answer();

				assessment_Answer.setAssessment_Answer_Code(new java.util.Random().nextLong());
				Profile profile = new Profile();
				profile.setProfile_Code(assessmentAnswerInfo.getProfileAndBiomedicalAuthorityInfo().getProfileCode());
				assessment_Answer.setProfile(profile);
				assessment_Answer.setAnswer_Option_Code(assessmentAnswerModel.getAnswerOptionCode());
				assessment_Answer.setBiomedical_Authority_Code(assessmentAnswerInfo.getProfileAndBiomedicalAuthorityInfo().getBiomedicalAuthorityCode());
				assessment_Answer.setAssessment_Question_Code(assessmentAnswerModel.getAssessmentQuestionCode());

				assessmentAnswerEntities.add(assessment_Answer);
			}

			if (!CollectionUtils.isEmpty(assessmentAnswerEntities)) {
				assessmentAnswerRepo.saveAll(assessmentAnswerEntities);
				flag = true;
			}
		} catch (Exception exception) {

		}

		return flag;
	}

}
