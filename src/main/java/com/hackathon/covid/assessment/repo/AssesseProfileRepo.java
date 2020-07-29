package com.hackathon.covid.assessment.repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Profile;

@Repository

@Transactional(readOnly = true)

public interface AssesseProfileRepo extends JpaRepository<Profile, Long> {

	@Transactional

	@Modifying(clearAutomatically = true, flushAutomatically = true)

	@Query(value = "update dbo.Profile set Assessment_Score =:assessmentScore where Profile_Code =:profileCode ", nativeQuery = true)

	int setAssessmentScore(@Param("assessmentScore") BigDecimal assessmentScore,
			@Param("profileCode") String profileCode);

}