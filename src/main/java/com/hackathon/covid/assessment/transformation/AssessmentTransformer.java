package com.hackathon.covid.assessment.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hackathon.covid.assessment.entities.Country;
import com.hackathon.covid.assessment.entities.State;
import com.hackathon.covid.assessment.model.CountryCodesAndNames;
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

}
