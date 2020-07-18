package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Answer_Option database table.
 * 
 */
@Entity
@NamedQuery(name="Answer_Option.findAll", query="SELECT a FROM Answer_Option a")
public class Answer_Option implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Answer_OptionPK id;

	@Column(name="Answer_Option_Desc")
	private String answer_Option_Desc;

	//bi-directional many-to-one association to Answer_Type
	@ManyToOne
	@JoinColumn(name="Answer_Type_Code")
	private Answer_Type answerType;

	//bi-directional many-to-one association to Assessment_Question
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Assessment_Question_Code", referencedColumnName="Assessment_Question_Code", insertable = false, updatable = false),
		@JoinColumn(name="Biomedical_Authority_Code", referencedColumnName="Biomedical_Authority_Code", insertable = false, updatable = false)
		})
	private Assessment_Question assessmentQuestion;

	//bi-directional many-to-one association to Assessment_Answer
	@OneToMany(mappedBy="answerOption")
	private List<Assessment_Answer> assessmentAnswers;

	public Answer_Option() {
	}

	public Answer_OptionPK getId() {
		return this.id;
	}

	public void setId(Answer_OptionPK id) {
		this.id = id;
	}

	public String getAnswer_Option_Desc() {
		return this.answer_Option_Desc;
	}

	public void setAnswer_Option_Desc(String answer_Option_Desc) {
		this.answer_Option_Desc = answer_Option_Desc;
	}

	public Answer_Type getAnswerType() {
		return this.answerType;
	}

	public void setAnswerType(Answer_Type answerType) {
		this.answerType = answerType;
	}

	public Assessment_Question getAssessmentQuestion() {
		return this.assessmentQuestion;
	}

	public void setAssessmentQuestion(Assessment_Question assessmentQuestion) {
		this.assessmentQuestion = assessmentQuestion;
	}

	public List<Assessment_Answer> getAssessmentAnswers() {
		return this.assessmentAnswers;
	}

	public void setAssessmentAnswers(List<Assessment_Answer> assessmentAnswers) {
		this.assessmentAnswers = assessmentAnswers;
	}

	public Assessment_Answer addAssessmentAnswer(Assessment_Answer assessmentAnswer) {
		getAssessmentAnswers().add(assessmentAnswer);
		assessmentAnswer.setAnswerOption(this);

		return assessmentAnswer;
	}

	public Assessment_Answer removeAssessmentAnswer(Assessment_Answer assessmentAnswer) {
		getAssessmentAnswers().remove(assessmentAnswer);
		assessmentAnswer.setAnswerOption(null);

		return assessmentAnswer;
	}

}