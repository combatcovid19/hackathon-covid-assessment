package com.hackathon.covid.assessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Country;
import com.hackathon.covid.assessment.entities.State;
import com.hackathon.covid.assessment.entities.StatePK;

@Repository
@Transactional(readOnly = true)
public interface StateRepo extends JpaRepository<State, StatePK> {

//	@Query("select ie from State ie where ie.Country_Code = :Country_Code")
	List<State> findStateByCountry(Country countryCode);

}
