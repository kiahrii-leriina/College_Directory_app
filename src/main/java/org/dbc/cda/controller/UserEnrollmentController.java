package org.dbc.cda.controller;

import org.dbc.cda.services.UserEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userEnrollment")
public class UserEnrollmentController {

	@Autowired
	private UserEnrollmentService userEnrollmentService;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllEnrollment(){
		return userEnrollmentService.findAllEnrollment();
	}
	
	@GetMapping("/student")
	public ResponseEntity<?> findStudentEnrollment(){
		return userEnrollmentService.findStudentEnrollment();
	}
	@GetMapping("/faculty")
	public ResponseEntity<?> findFacultyEnrollment(){
		return userEnrollmentService.findFacultyEnrollment();
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> findAdminEnrollment(){
		return userEnrollmentService.findAdminEnrollment();
	}
	
}
