package com.hackathon.covid.assessment.model;

import java.util.HashMap;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({ "timezone_identifier", "timezone_abbr", "utc_offset_sec", "is_dst" })

public class Timezone {

	@JsonProperty("timezone_identifier")

	private String timezoneIdentifier;

	@JsonProperty("timezone_abbr")

	private String timezoneAbbr;

	@JsonProperty("utc_offset_sec")

	private Integer utcOffsetSec;

	@JsonProperty("is_dst")

	private String isDst;

	@JsonIgnore

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("timezone_identifier")

	public String getTimezoneIdentifier() {

		return timezoneIdentifier;

	}

	@JsonProperty("timezone_identifier")

	public void setTimezoneIdentifier(String timezoneIdentifier) {

		this.timezoneIdentifier = timezoneIdentifier;

	}

	@JsonProperty("timezone_abbr")

	public String getTimezoneAbbr() {

		return timezoneAbbr;

	}

	@JsonProperty("timezone_abbr")

	public void setTimezoneAbbr(String timezoneAbbr) {

		this.timezoneAbbr = timezoneAbbr;

	}

	@JsonProperty("utc_offset_sec")

	public Integer getUtcOffsetSec() {

		return utcOffsetSec;

	}

	@JsonProperty("utc_offset_sec")

	public void setUtcOffsetSec(Integer utcOffsetSec) {

		this.utcOffsetSec = utcOffsetSec;

	}

	@JsonProperty("is_dst")

	public String getIsDst() {

		return isDst;

	}

	@JsonProperty("is_dst")

	public void setIsDst(String isDst) {

		this.isDst = isDst;

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