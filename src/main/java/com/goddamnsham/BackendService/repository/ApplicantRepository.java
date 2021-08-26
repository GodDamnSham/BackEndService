package com.goddamnsham.BackendService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goddamnsham.BackendService.model.Applicant;



@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

	
	Optional<Applicant> findByEmail(String email);
	
}
