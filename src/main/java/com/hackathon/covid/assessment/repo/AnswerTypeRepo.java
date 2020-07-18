package com.hackathon.covid.assessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Answer_Type;

@Repository
@Transactional(readOnly = true)
public interface AnswerTypeRepo extends JpaRepository<Answer_Type, Short> {

}
