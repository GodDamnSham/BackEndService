package com.goddamnsham.BackendService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.goddamnsham.BackendService.model.Applicant;
import com.goddamnsham.BackendService.model.BaseResponse;
import com.goddamnsham.BackendService.service.ApplicantService;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ApplicantController {
	
	
	
	@Autowired
    ApplicantService applicantService;
	
	

    private static final Logger logger = LoggerFactory.getLogger(ApplicantController.class);
    
	@RequestMapping(value = "/addApplicant", method = {RequestMethod.POST})
	public ResponseEntity<Object> addApplicant(@RequestBody(required = true) Applicant applicantData) throws JsonMappingException, JsonProcessingException {
	
		BaseResponse response= new BaseResponse();
        	
		try{
			
		
		String email = applicantData.getEmail();
		
		
		
		Date date = new Date();
		
			
			Applicant isExist = applicantService.findApplicantByEmail(email);
			System.out.println("isExist "+isExist);
			
			if(isExist != null) {
				response.setStatus("409");
				response.setMessageType("Failure");
				response.setMessage("User already exists");
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else{
				
				Applicant applicant = applicantService.save(applicantData);
				
			    
			    response.setStatus("200");
			    response.setMessageType("Success");
			    response.setMessage("Applicant is successfully registered");
			    Map<String, Object> map = new HashMap<String, Object>();
			    map.put("ApplicantDetails", applicant);
				response.setData(map);
			    return new ResponseEntity<>(response, HttpStatus.OK);
			
			}
			
	}catch(Exception e){
		logger.error("add failed : " , e);
    }
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	


	@RequestMapping(value = "/updateApplicant", method = RequestMethod.POST)
	public ResponseEntity<Object> updateUser(@RequestBody(required = true) Applicant applicantData) throws JsonMappingException, JsonProcessingException {
		BaseResponse response = new BaseResponse();
		Long id = applicantData.getId();
		try {
			if (id!=null) {
				Optional<Applicant> optionalApplicant = applicantService.findById(id);
				if (optionalApplicant.isPresent()) {
					
				
					Date date = new Date();
					Applicant applicant = optionalApplicant.get();
					applicant.setName(applicantData.getName());
					applicant.setEmail(applicantData.getEmail());
					applicant.setTelephoneNumber(applicantData.getTelephoneNumber());
					applicant.setDesiredPosition(applicantData.getDesiredPosition());
					applicant.setDesiredSalary(applicantData.getDesiredSalary());
					applicant.setStatus(applicantData.getStatus());
					
					applicantService.save(applicant);

					response.setStatus("200");
					response.setMessageType("Success");
					response.setMessage("Profile is successfully updated");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ApplicantDetails", applicant);
					response.setData(map);
					return new ResponseEntity<>(response, HttpStatus.OK);
				}else{
					response.setStatus("400");
					response.setMessageType("Failure");
					response.setMessage("Applicant does not exist");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
				
			} else {
				response.setStatus("400");
				response.setMessageType("Failure");
				response.setMessage("Request body parameter missing");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("updateUser failed : ", e);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    
    

	
}
