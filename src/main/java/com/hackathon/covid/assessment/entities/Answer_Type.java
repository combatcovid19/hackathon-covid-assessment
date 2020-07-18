package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Answer_Type database table.
 * 
 */
@Entity
@NamedQuery(name="Answer_Type.findAll", query="SELECT a FROM Answer_Type a")
public class Answer_Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Answer_Type_Code")
	private String answer_Type_Code;

	@Column(name="Answer_Type_Desc")
	private String answer_Type_Desc;

	//bi-directional many-to-one association to Answer_Option
	@OneToMany(mappedBy="answerType")
	private List<Answer_Option> answerOptions;

	public Answer_Type() {
	}

	public String getAnswer_Type_Code() {
		return this.answer_Type_Code;
	}

	public void setAnswer_Type_Code(String answer_Type_Code) {
		this.answer_Type_Code = answer_Type_Code;
	}

	public String getAnswer_Type_Desc() {
		return this.answer_Type_Desc;
	}

	public void setAnswer_Type_Desc(String answer_Type_Desc) {
		this.answer_Type_Desc = answer_Type_Desc;
	}

	public List<Answer_Option> getAnswerOptions() {
		return this.answerOptions;
	}

	public void setAnswerOptions(List<Answer_Option> answerOptions) {
		this.answerOptions = answerOptions;
	}

	public Answer_Option addAnswerOption(Answer_Option answerOption) {
		getAnswerOptions().add(answerOption);
		answerOption.setAnswerType(this);

		return answerOption;
	}

	public Answer_Option removeAnswerOption(Answer_Option answerOption) {
		getAnswerOptions().remove(answerOption);
		answerOption.setAnswerType(null);

		return answerOption;
	}

}