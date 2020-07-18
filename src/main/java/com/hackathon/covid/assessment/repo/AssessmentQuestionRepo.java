package com.hackathon.covid.assessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Assessment_Question;
import com.hackathon.covid.assessment.entities.Biomedical_Authority;

@Repository
@Transactional(readOnly = true)
public interface AssessmentQuestionRepo extends JpaRepository<Assessment_Question, Short> {

	List<Assessment_Question> findAssessment_QuestionByBiomedicalAuthority(
			@Param("biomedicalAuthority") Biomedical_Authority bioMedicalAuthority);

}
