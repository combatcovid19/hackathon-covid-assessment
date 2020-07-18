package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Assessment_Question database table.
 * 
 */
@Entity
@NamedQuery(name="Assessment_Question.findAll", query="SELECT a FROM Assessment_Question a")
public class Assessment_Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Assessment_QuestionPK id;

	@Column(name="Assessment_Question_Desc")
	private String assessment_Question_Desc;

	//bi-directional many-to-one association to Answer_Option
	@OneToMany(mappedBy="assessmentQuestion")
	private List<Answer_Option> answerOptions;

	//bi-directional many-to-one association to Assessment_Answer
	@OneToMany(mappedBy="assessmentQuestion")
	private List<Assessment_Answer> assessmentAnswers;

	//bi-directional many-to-one association to Biomedical_Authority
	@ManyToOne
	@JoinColumn(name="Biomedical_Authority_Code", insertable = false, updatable = false)
	private Biomedical_Authority biomedicalAuthority;

	public Assessment_Question() {
	}

	public Assessment_QuestionPK getId() {
		return this.id;
	}

	public void setId(Assessment_QuestionPK id) {
		this.id = id;
	}

	public String getAssessment_Question_Desc() {
		return this.assessment_Question_Desc;
	}

	public void setAssessment_Question_Desc(String assessment_Question_Desc) {
		this.assessment_Question_Desc = assessment_Question_Desc;
	}

	public List<Answer_Option> getAnswerOptions() {
		return this.answerOptions;
	}

	public void setAnswerOptions(List<Answer_Option> answerOptions) {
		this.answerOptions = answerOptions;
	}

	public Answer_Option addAnswerOption(Answer_Option answerOption) {
		getAnswerOptions().add(answerOption);
		answerOption.setAssessmentQuestion(this);

		return answerOption;
	}

	public Answer_Option removeAnswerOption(Answer_Option answerOption) {
		getAnswerOptions().remove(answerOption);
		answerOption.setAssessmentQuestion(null);

		return answerOption;
	}

	public List<Assessment_Answer> getAssessmentAnswers() {
		return this.assessmentAnswers;
	}

	public void setAssessmentAnswers(List<Assessment_Answer> assessmentAnswers) {
		this.assessmentAnswers = assessmentAnswers;
	}

	public Assessment_Answer addAssessmentAnswer(Assessment_Answer assessmentAnswer) {
		getAssessmentAnswers().add(assessmentAnswer);
		assessmentAnswer.setAssessmentQuestion(this);

		return assessmentAnswer;
	}

	public Assessment_Answer removeAssessmentAnswer(Assessment_Answer assessmentAnswer) {
		getAssessmentAnswers().remove(assessmentAnswer);
		assessmentAnswer.setAssessmentQuestion(null);

		return assessmentAnswer;
	}

	public Biomedical_Authority getBiomedicalAuthority() {
		return this.biomedicalAuthority;
	}

	public void setBiomedicalAuthority(Biomedical_Authority biomedicalAuthority) {
		this.biomedicalAuthority = biomedicalAuthority;
	}

}