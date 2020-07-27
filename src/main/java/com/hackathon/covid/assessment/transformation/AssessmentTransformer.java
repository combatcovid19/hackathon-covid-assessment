package com.hackathon.covid.assessment.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hackathon.covid.assessment.entities.Country;
import com.hackathon.covid.assessment.entities.Provider;
import com.hackathon.covid.assessment.entities.State;
import com.hackathon.covid.assessment.model.AssessmentScoreAndProviders;
import com.hackathon.covid.assessment.model.CountryCodesAndNames;
import com.hackathon.covid.assessment.model.Provider_Model;
import com.hackathon.covid.assessment.model.StateCodesAndNames;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AssessmentTransformer {

	public List<CountryCodesAndNames> convertCountryEntityToModel(List<Country> countryEntities) {
		List<CountryCodesAndNames> countryCodesAndNamesList = new ArrayList<>();

		try {
			for (Country countryEntity : countryEntities) {
				CountryCodesAndNames countryCodesAndNames = new CountryCodesAndNames();

				countryCodesAndNames.setCountryCode(countryEntity.getCountry_Code());
				countryCodesAndNames.setCountryName(countryEntity.getCountry_Name());

				countryCodesAndNamesList.add(countryCodesAndNames);
			}
		} catch (Exception exception) {
			log.error("ERROR : while transforming CountryEntity to CountryCodesAndNames Model", exception);
		}

		return countryCodesAndNamesList;
	}

	public List<StateCodesAndNames> convertStateEntityToModel(List<State> stateEntitiesList) {
		List<StateCodesAndNames> stateCodesAndNamesList = new ArrayList<>();

		try {
			for (State stateEntity : stateEntitiesList) {
				StateCodesAndNames stateCodesAndNames = new StateCodesAndNames();

				stateCodesAndNames.setStateCode(stateEntity.getId().getState_Code());
				stateCodesAndNames.setStateName(stateEntity.getState_Name());

				stateCodesAndNamesList.add(stateCodesAndNames);
			}
		} catch (Exception exception) {
			log.error("ERROR : while transforming StateEntity to StateCodesAndNames Model", exception);
		}

		return stateCodesAndNamesList;
	}
	
	// New Method
	
	public AssessmentScoreAndProviders convertProviderEntityToModel(List<Provider> nearByProvidersToAssesse, int assessmentScore) {

		List<Provider_Model> providerModelList = new ArrayList<>();
		AssessmentScoreAndProviders assessmentScoreAndProviders = new AssessmentScoreAndProviders();

		try {

			for (Provider provider : nearByProvidersToAssesse) {

				Provider_Model provider_Model = new Provider_Model();

				provider_Model.setProvider_Code(provider.getProvider_Code());

				provider_Model.setProvider_City_Name(provider.getProvider_City_Name());

				provider_Model.setProvider_District_Or_County_Name(provider.getProvider_District_Or_County_Name());

				provider_Model.setProvider_Email_Id(provider.getProvider_Email_Id());

				provider_Model.setProvider_Latitude(provider.getProvider_Latitude());

				provider_Model.setProvider_Longitude(provider.getProvider_Longitude());

				provider_Model.setProvider_Phone_Number1(provider.getProvider_Phone_Number1());

				provider_Model.setProvider_Phone_Number2(provider.getProvider_Phone_Number2());

				provider_Model.setProvider_Phone_Number3(provider.getProvider_Phone_Number3());

				provider_Model.setProvider_Pin_Zip_Code(provider.getProvider_Pin_Zip_Code());

				provider_Model.setProvider_Street_Address(provider.getProvider_Street_Address());

				provider_Model.setDistance(provider.getDistance());

				providerModelList.add(provider_Model);

			}
			
			assessmentScoreAndProviders.setNearestProviders(providerModelList);
			assessmentScoreAndProviders.setAssessmentScore(assessmentScore);

		} catch (Exception exception) {

			log.error("ERROR : while transforming Provider Entity to Provider_Model ", exception);

		}

		return assessmentScoreAndProviders;

	}

}
