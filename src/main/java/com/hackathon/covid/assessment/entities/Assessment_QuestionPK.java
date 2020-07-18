package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Assessment_Question database table.
 * 
 */
@Embeddable
public class Assessment_QuestionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Assessment_Question_Code")
	private String assessment_Question_Code;

	@Column(name="Biomedical_Authority_Code", insertable=false, updatable=false)
	private String biomedical_Authority_Code;

	public Assessment_QuestionPK() {
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
		if (!(other instanceof Assessment_QuestionPK)) {
			return false;
		}
		Assessment_QuestionPK castOther = (Assessment_QuestionPK)other;
		return 
			this.assessment_Question_Code.equals(castOther.assessment_Question_Code)
			&& this.biomedical_Authority_Code.equals(castOther.biomedical_Authority_Code);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.assessment_Question_Code.hashCode();
		hash = hash * prime + this.biomedical_Authority_Code.hashCode();
		
		return hash;
	}
}