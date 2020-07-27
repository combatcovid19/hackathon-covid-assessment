package com.hackathon.covid.assessment.model;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({ "zip_code", "lat", "lng", "city", "state", "timezone", "acceptable_city_names", "area_codes" })

public class GeocodingResult {

	@JsonProperty("zip_code")

	private String zipCode;

	@JsonProperty("lat")

	private Double lat;

	@JsonProperty("lng")

	private Double lng;

	@JsonProperty("city")

	private String city;

	@JsonProperty("state")

	private String state;

	@JsonProperty("timezone")

	private Timezone timezone;

	@JsonProperty("acceptable_city_names")

	private List<Object> acceptableCityNames = null;

	@JsonProperty("area_codes")

	private List<Integer> areaCodes = null;

	@JsonIgnore

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("zip_code")

	public String getZipCode() {

		return zipCode;

	}

	@JsonProperty("zip_code")

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;

	}

	@JsonProperty("lat")

	public Double getLat() {

		return lat;

	}

	@JsonProperty("lat")

	public void setLat(Double lat) {

		this.lat = lat;

	}

	@JsonProperty("lng")

	public Double getLng() {

		return lng;

	}

	@JsonProperty("lng")

	public void setLng(Double lng) {

		this.lng = lng;

	}

	@JsonProperty("city")

	public String getCity() {

		return city;

	}

	@JsonProperty("city")

	public void setCity(String city) {

		this.city = city;

	}

	@JsonProperty("state")

	public String getState() {

		return state;

	}

	@JsonProperty("state")

	public void setState(String state) {

		this.state = state;

	}

	@JsonProperty("timezone")

	public Timezone getTimezone() {

		return timezone;

	}

	@JsonProperty("timezone")

	public void setTimezone(Timezone timezone) {

		this.timezone = timezone;

	}

	@JsonProperty("acceptable_city_names")

	public List<Object> getAcceptableCityNames() {

		return acceptableCityNames;

	}

	@JsonProperty("acceptable_city_names")

	public void setAcceptableCityNames(List<Object> acceptableCityNames) {

		this.acceptableCityNames = acceptableCityNames;

	}

	@JsonProperty("area_codes")

	public List<Integer> getAreaCodes() {

		return areaCodes;

	}

	@JsonProperty("area_codes")

	public void setAreaCodes(List<Integer> areaCodes) {

		this.areaCodes = areaCodes;

	}

	@JsonAnyGetter

	public Map<String, Object> getAdditionalProperties() {

		return this.additionalProperties;

	}

	@JsonAnySetter

	public void setAdditionalProperty(String name, Object value) {

		this.additionalProperties.put(name, value);

	}

}