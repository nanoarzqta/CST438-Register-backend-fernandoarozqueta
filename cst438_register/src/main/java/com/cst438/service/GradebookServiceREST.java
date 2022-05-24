package com.cst438.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;										   									   
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.EnrollmentDTO;


public class GradebookServiceREST extends GradebookService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${gradebook.url}")
	String gradebook_url;
	
	public GradebookServiceREST() {
		System.out.println("REST grade book service");
	}

	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		
		/this class will send a new student enrollment to the GradeBook 
		//service using HTTP POST and an EnrollmentDTO object.
		
		//Create new EnrollmentDTO object
		EnrollmentDTO newStudent = new EnrollmentDTO(student_email, student_name, course_id);

		System.out.println("Gradebook URL: "+gradebook_url + "/enrollment");
		//Send to gradebook_url
		System.out.println("Sending http message: "+newStudent);
		ResponseEntity<EnrollmentDTO> response = restTemplate.postForEntity(
				gradebook_url + "/enrollment",   		// URL
				newStudent,				// data to send
				EnrollmentDTO.class);   // return data type
		
		HttpStatus rc = response.getStatusCode();
		System.out.println("HttpStatus: "+rc);
		EnrollmentDTO returnObject = response.getBody();
		System.out.println(returnObject);
		//return returnObject;									
	}

}
