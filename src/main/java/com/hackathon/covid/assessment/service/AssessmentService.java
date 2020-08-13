package com.hackathon.covid.assessment.service;

import static java.text.MessageFormat.format;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.covid.assessment.entities.Answer_Option;
import com.hackathon.covid.assessment.entities.Assessment_Answer;
import com.hackathon.covid.assessment.entities.Assessment_Question;
import com.hackathon.covid.assessment.entities.Biomedical_Authority;
import com.hackathon.covid.assessment.entities.Contact_Tracing;
import com.hackathon.covid.assessment.entities.Country;
import com.hackathon.covid.assessment.entities.Gender;
import com.hackathon.covid.assessment.entities.Profile;
import com.hackathon.covid.assessment.entities.Provider;
import com.hackathon.covid.assessment.entities.State;
import com.hackathon.covid.assessment.entities.StatePK;
import com.hackathon.covid.assessment.model.AnswerInformation;
import com.hackathon.covid.assessment.model.AssesseProfile;
import com.hackathon.covid.assessment.model.AssessmentAnswer;
import com.hackathon.covid.assessment.model.AssessmentAnswerInfo;
import com.hackathon.covid.assessment.model.Assessment_Question_Model;
import com.hackathon.covid.assessment.model.Assessment_Questionnaire;
import com.hackathon.covid.assessment.model.ContactTracer;
import com.hackathon.covid.assessment.model.ContactTracingInfo;
import com.hackathon.covid.assessment.model.CountryCodesAndNames;
import com.hackathon.covid.assessment.model.GeocodingResult;
import com.hackathon.covid.assessment.model.Provider_Model;
import com.hackathon.covid.assessment.model.StateCodesAndNames;
import com.hackathon.covid.assessment.repo.AnswerOptionsRepo;
import com.hackathon.covid.assessment.repo.AssesseProfileRepo;
import com.hackathon.covid.assessment.repo.AssessmentAnswerRepo;
import com.hackathon.covid.assessment.repo.AssessmentQuestionRepo;
import com.hackathon.covid.assessment.repo.AssessmentRepo;
import com.hackathon.covid.assessment.repo.BiomedicalAuthorityRepo;
import com.hackathon.covid.assessment.repo.ContactTracingRepo;
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

@Autowired
RestTemplate serviceRestTemplate;

@Autowired
@Qualifier("sourceEntityManager")
protected LocalContainerEntityManagerFactoryBean sourceEntityManager;

@Value("${georesult.rest.path}")
String geoCodeRestURL;

@Autowired
private JavaMailSender emailSender;

@Autowired
ContactTracingRepo contactTracingRepo;

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

public List<Provider_Model> saveAssessmentAnswers(AssessmentAnswerInfo assessmentAnswerInfo) {

List<Assessment_Answer> assessmentAnswerEntities = new ArrayList<>();
Profile profile = new Profile();
List<Provider> nearByProvidersToAssesse = new ArrayList<>();
int assessmentScore = 0;
List<Provider_Model> providerModelList = new ArrayList<>();

try {
for (AssessmentAnswer assessmentAnswerModel : assessmentAnswerInfo.getAssessmentAnswers()) {
Assessment_Answer assessment_Answer = new Assessment_Answer();

assessment_Answer.setAssessment_Answer_Code(new java.util.Random().nextLong());
profile.setProfile_Code(assessmentAnswerInfo.getProfileAndBiomedicalAuthorityInfo().getProfileCode());
assessment_Answer.setProfile(profile);
assessment_Answer.setAnswer_Option_Code(assessmentAnswerModel.getAnswerOptionCode());
assessment_Answer.setBiomedical_Authority_Code(
assessmentAnswerInfo.getProfileAndBiomedicalAuthorityInfo().getBiomedicalAuthorityCode());
assessment_Answer.setAssessment_Question_Code(assessmentAnswerModel.getAssessmentQuestionCode());

assessmentAnswerEntities.add(assessment_Answer);
}

if (!CollectionUtils.isEmpty(assessmentAnswerEntities)) {
assessmentScore = calculateAssessmentScore(assessmentAnswerEntities);

assessmentAnswerRepo.saveAll(assessmentAnswerEntities);

assesseProfileRepo.setAssessmentScore(new BigDecimal(assessmentScore), profile.getProfile_Code());

if (assessmentScore >= 60) {

GeocodingResult geocodingResult = retrieveGeoLocationFromZipCode(String
.valueOf(assessmentAnswerInfo.getProfileAndBiomedicalAuthorityInfo().getProfileZipCode()));

if (null != geocodingResult && null != geocodingResult.getLat()
&& null != geocodingResult.getLng()) {
EntityManager entityManager = sourceEntityManager.getNativeEntityManagerFactory()
.createEntityManager();

StoredProcedureQuery calculateProviderDistanceFromAssesseProcedureQuery = entityManager
.createNamedStoredProcedureQuery(Provider.PROCEDURE_NAME);

calculateProviderDistanceFromAssesseProcedureQuery.setParameter("RADIUS", 20);

calculateProviderDistanceFromAssesseProcedureQuery.setParameter("LAT",
Double.toString(geocodingResult.getLat()));
calculateProviderDistanceFromAssesseProcedureQuery.setParameter("LONG",
Double.toString(geocodingResult.getLng()));

calculateProviderDistanceFromAssesseProcedureQuery.execute();
nearByProvidersToAssesse = calculateProviderDistanceFromAssesseProcedureQuery.getResultList();
}

}
}

if (!CollectionUtils.isEmpty(nearByProvidersToAssesse)) {
providerModelList = assessmentTransformer.convertProviderEntityToModel(nearByProvidersToAssesse);
}
} catch (Exception exception) {

}

return providerModelList;
}

private int calculateAssessmentScore(List<Assessment_Answer> assessmentAnswerEntities) {

int score = 0;
try {
for (Assessment_Answer assessmentAnswer : assessmentAnswerEntities) {
if (null != assessmentAnswer.getAnswer_Option_Code()
&& assessmentAnswer.getAnswer_Option_Code().equals("A001")) {
score += 20;
}
}

} catch (Exception exception) {

}
return score;
}

public GeocodingResult retrieveGeoLocationFromZipCode(String zipcode) {

GeocodingResult geocodingResult = null;
try {

UriComponentsBuilder builders = UriComponentsBuilder.fromUriString(format(geoCodeRestURL, zipcode));
String uriBuilder = builders.build().encode().toUriString();

String responseString = serviceRestTemplate.getForObject(new URI(uriBuilder), String.class);

if (!StringUtils.isEmpty(responseString)) {

geocodingResult = objectMapper.readValue(responseString, new TypeReference<GeocodingResult>() {
});
}
} catch (Exception exception) {

}

return geocodingResult;

}

public boolean saveContactTracingInfoAndNotify(ContactTracingInfo contactTracingInfo) {

Profile profile = new Profile();
List<Contact_Tracing> contactTracingEntities = new ArrayList<>();
List<String> recipients = new ArrayList<>();
boolean flag = false;

try {
profile.setProfile_Code(contactTracingInfo.getProfileCode());

for (ContactTracer contactTracer : contactTracingInfo.getContactTracerList()) {
Contact_Tracing contactTracingEntity = new Contact_Tracing();

contactTracingEntity.setContact_Tracing_Code(new java.util.Random().nextLong());
contactTracingEntity.setContact_Phone_Number(contactTracer.getPhoneNumber());
contactTracingEntity.setContact_Tracing_Email_Id(contactTracer.getEmailId());
contactTracingEntity.setContact_Desc(contactTracer.getContactDescription());
contactTracingEntity.setProfile(profile);
contactTracingEntities.add(contactTracingEntity);
recipients.add(contactTracer.getEmailId());
}

contactTracingRepo.saveAll(contactTracingEntities);

sendAlertMessage(recipients, "Be aware of COVID-19",
"Authenticated Information from State Govt. :: One of the person recently contacted you got Positive in COVID-19 test.So, please go through COVID-19 tests immediately");
flag = true;
} catch (Exception exception) {
log.error("ERROR : while saving contact tracer list to datastore", exception);
flag = false;
}

return flag;
}

public void sendAlertMessage(List<String> recipients, String subject, String text) {
SimpleMailMessage message = new SimpleMailMessage();
message.setTo(recipients.toArray(new String[recipients.size()]));
message.setSubject(subject);
message.setText(text);
emailSender.send(message);
}

}

