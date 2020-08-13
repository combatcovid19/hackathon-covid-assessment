package com.hackathon.covid.assessment.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Assessment_Answer database table.
 *
 */
@Entity
@NamedQuery(name="Assessment_Answer.findAll", query="SELECT a FROM Assessment_Answer a")
public class Assessment_Answer implements Serializable {
private static final long serialVersionUID = 1L;

@Id
@Column(name="Assessment_Answer_Code")
private long assessment_Answer_Code;

@Column(name="Assessment_Question_Code")
private String assessment_Question_Code;
@Column(name="Answer_Option_Code")
private String answer_Option_Code;
@Column(name="Biomedical_Authority_Code")
private String biomedical_Authority_Code;

//bi-directional many-to-one association to Answer_Option
@ManyToOne
@JoinColumns({
@JoinColumn(name="Answer_Option_Code", referencedColumnName="Answer_Option_Code", insertable = false, updatable = false),
@JoinColumn(name="Assessment_Question_Code", referencedColumnName="Assessment_Question_Code", insertable = false, updatable = false),
@JoinColumn(name="Biomedical_Authority_Code", referencedColumnName="Biomedical_Authority_Code", insertable = false, updatable = false)
})
private Answer_Option answerOption;

//bi-directional many-to-one association to Assessment_Question
@ManyToOne
@JoinColumns({
@JoinColumn(name="Assessment_Question_Code", referencedColumnName="Assessment_Question_Code", insertable = false, updatable = false),
@JoinColumn(name="Biomedical_Authority_Code", referencedColumnName="Biomedical_Authority_Code", insertable = false, updatable = false)
})
private Assessment_Question assessmentQuestion;

//bi-directional many-to-one association to Profile
@ManyToOne
@JoinColumn(name="Profile_Code")
private Profile profile;

public Assessment_Answer() {
}

public long getAssessment_Answer_Code() {
return this.assessment_Answer_Code;
}

public void setAssessment_Answer_Code(long assessment_Answer_Code) {
this.assessment_Answer_Code = assessment_Answer_Code;
}

public Answer_Option getAnswerOption() {
return this.answerOption;
}

public void setAnswerOption(Answer_Option answerOption) {
this.answerOption = answerOption;
}

public Assessment_Question getAssessmentQuestion() {
return this.assessmentQuestion;
}

public void setAssessmentQuestion(Assessment_Question assessmentQuestion) {
this.assessmentQuestion = assessmentQuestion;
}

public Profile getProfile() {
return this.profile;
}

public void setProfile(Profile profile) {
this.profile = profile;
}

// public long getProfile_Code() {
// return profile_Code;
// }
//
// public void setProfile_Code(long profile_Code) {
// this.profile_Code = profile_Code;
// }

public String getAssessment_Question_Code() {
return assessment_Question_Code;
}

public void setAssessment_Question_Code(String assessment_Question_Code) {
this.assessment_Question_Code = assessment_Question_Code;
}

public String getAnswer_Option_Code() {
return answer_Option_Code;
}

public void setAnswer_Option_Code(String answer_Option_Code) {
this.answer_Option_Code = answer_Option_Code;
}

public String getBiomedical_Authority_Code() {
return biomedical_Authority_Code;
}

public void setBiomedical_Authority_Code(String biomedical_Authority_Code) {
this.biomedical_Authority_Code = biomedical_Authority_Code;
}

}