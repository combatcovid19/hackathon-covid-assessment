package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Answer_Option database table.
 * 
 */
@Embeddable
public class Answer_OptionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Answer_Option_Code")
	private String answer_Option_Code;

	@Column(name="Assessment_Question_Code", insertable=false, updatable=false)
	private String assessment_Question_Code;

	@Column(name="Biomedical_Authority_Code", insertable=false, updatable=false)
	private String biomedical_Authority_Code;

	public Answer_OptionPK() {
	}
	public String getAnswer_Option_Code() {
		return this.answer_Option_Code;
	}
	public void setAnswer_Option_Code(String answer_Option_Code) {
		this.answer_Option_Code = answer_Option_Code;
	}
	public String getAssessment_Question_Code() {
		return this.assessment_Question_Code;
	}
	public void setAssessment_Question_Code(String assessment_Question_Code) {
		this.assessment_Question_Code = assessment_Question_Code;
	}
	public String getBiomedical_Authority_Code() {
		return this.biomedical_Authority_Code;
	}
	public void setBiomedical_Authority_Code(String biomedical_Authority_Code) {
		this.biomedical_Authority_Code = biomedical_Authority_Code;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Answer_OptionPK)) {
			return false;
		}
		Answer_OptionPK castOther = (Answer_OptionPK)other;
		return 
			this.answer_Option_Code.equals(castOther.answer_Option_Code)
			&& this.assessment_Question_Code.equals(castOther.assessment_Question_Code)
			&& this.biomedical_Authority_Code.equals(castOther.biomedical_Authority_Code);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.answer_Option_Code.hashCode();
		hash = hash * prime + this.assessment_Question_Code.hashCode();
		hash = hash * prime + this.biomedical_Authority_Code.hashCode();
		
		return hash;
	}
}