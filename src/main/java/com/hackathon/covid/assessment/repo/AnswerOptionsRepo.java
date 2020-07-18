package com.hackathon.covid.assessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Answer_Option;
import com.hackathon.covid.assessment.entities.Assessment_Question;

@Repository
@Transactional(readOnly = true)
public interface AnswerOptionsRepo extends JpaRepository<Answer_Option, Short> {

//	List<Answer_Options> findAnswer_OptionsByAssessmentQuestion(
//			@Param("Questions_Id") Assessment_Question assessmentQuestion);

}
