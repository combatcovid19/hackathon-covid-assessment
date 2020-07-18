package com.hackathon.covid.assessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Country;

@Repository
@Transactional(readOnly = true)
public interface AssessmentRepo extends JpaRepository<Country, Short> {

}
