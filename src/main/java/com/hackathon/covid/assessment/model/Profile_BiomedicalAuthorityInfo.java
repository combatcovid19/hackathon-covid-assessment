package com.hackathon.covid.assessment.model;

public class Profile_BiomedicalAuthorityInfo {
	private String biomedicalAuthorityCode;
	private long profileCode;

	public Profile_BiomedicalAuthorityInfo() {
	}

	public String getBiomedicalAuthorityCode() {
		return biomedicalAuthorityCode;
	}

	public void setBiomedicalAuthorityCode(String biomedicalAuthorityCode) {
		this.biomedicalAuthorityCode = biomedicalAuthorityCode;
	}

	public long getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(long profileCode) {
		this.profileCode = profileCode;
	}
}