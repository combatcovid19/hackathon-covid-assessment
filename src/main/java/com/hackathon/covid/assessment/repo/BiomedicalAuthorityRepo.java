package com.hackathon.covid.assessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.covid.assessment.entities.Biomedical_Authority;
import com.hackathon.covid.assessment.entities.Country;

@Repository
@Transactional(readOnly = true)
public interface BiomedicalAuthorityRepo extends JpaRepository<Biomedical_Authority, Short> {

//	BioMedical_Authority findBioMedical_AuthorityByCountry(@Param("Country_Code") Country countryCode);

}
