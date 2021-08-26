package com.goddamnsham.BackendService.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goddamnsham.BackendService.model.Applicant;
import com.goddamnsham.BackendService.repository.ApplicantRepository;
import com.goddamnsham.BackendService.service.ApplicantService;



@Service
public class ApplicantServiceImpl implements ApplicantService {

	
	@Autowired
	private ApplicantRepository applicantRepository;

	private static final Logger logger = LoggerFactory.getLogger(ApplicantServiceImpl.class);
	
	

	@Override
	public Applicant save(Applicant user) {
		
		return applicantRepository.save(user);
	}

	


	@Override
	public Optional<Applicant> findById(Long id) {
		logger.info("findById id : " + id);
		return applicantRepository.findById(id);
	}




	@Override
	public Applicant findApplicantByEmail(String emailId) {
		Applicant applicant = null;
		Optional<Applicant> applicantOptional = applicantRepository.findByEmail(emailId);
		if(applicantOptional.isPresent()) {
			applicant =  applicantOptional.get();
			logger.info("findUserByEmail userOptional : "+ applicant);
		}
		logger.info("findUserByEmail user : "+ applicant);
		return applicant;
	}
	
	
	
}
