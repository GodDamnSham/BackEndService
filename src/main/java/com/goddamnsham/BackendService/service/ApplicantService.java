package com.goddamnsham.BackendService.service;

import java.util.List;
import java.util.Optional;


import javax.swing.text.html.Option;

import com.goddamnsham.BackendService.model.Applicant;

public interface ApplicantService {

	
	public Applicant save(Applicant user);

	
	Optional<Applicant> findById(Long id);
	
	public Applicant findApplicantByEmail(String emailId);
	
	
}
