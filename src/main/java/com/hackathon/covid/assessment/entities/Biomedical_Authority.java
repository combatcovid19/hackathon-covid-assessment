package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Biomedical_Authority database table.
 * 
 */
@Entity
@NamedQuery(name="Biomedical_Authority.findAll", query="SELECT b FROM Biomedical_Authority b")
public class Biomedical_Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Biomedical_Authority_Code")
	private String biomedical_Authority_Code;

	@Column(name="Biomedical_Authority_Name")
	private String biomedical_Authority_Name;

	//bi-directional many-to-one association to Assessment_Question
	@OneToMany(mappedBy="biomedicalAuthority")
	private List<Assessment_Question> assessmentQuestions;

	//bi-directional one-to-one association to Country
	@OneToOne
	@JoinColumn(name="Biomedical_Authority_Code")
	private Country country;

	public Biomedical_Authority() {
	}

	public String getBiomedical_Authority_Code() {
		return this.biomedical_Authority_Code;
	}

	public void setBiomedical_Authority_Code(String biomedical_Authority_Code) {
		this.biomedical_Authority_Code = biomedical_Authority_Code;
	}

	public Object getBiomedical_Authority_Name() {
		return this.biomedical_Authority_Name;
	}

	public void setBiomedical_Authority_Name(String biomedical_Authority_Name) {
		this.biomedical_Authority_Name = biomedical_Authority_Name;
	}

	public List<Assessment_Question> getAssessmentQuestions() {
		return this.assessmentQuestions;
	}

	public void setAssessmentQuestions(List<Assessment_Question> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;
	}

	public Assessment_Question addAssessmentQuestion(Assessment_Question assessmentQuestion) {
		getAssessmentQuestions().add(assessmentQuestion);
		assessmentQuestion.setBiomedicalAuthority(this);

		return assessmentQuestion;
	}

	public Assessment_Question removeAssessmentQuestion(Assessment_Question assessmentQuestion) {
		getAssessmentQuestions().remove(assessmentQuestion);
		assessmentQuestion.setBiomedicalAuthority(null);

		return assessmentQuestion;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}