package org.dbc.cda.controller;

import org.dbc.cda.entities.FacultyProfile;
import org.dbc.cda.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	@PostMapping("/{userId}/{dName}")
	public ResponseEntity<?> saveFaculty(@RequestBody FacultyProfile faculty, @PathVariable long userId, @PathVariable String dName ){
		return facultyService.saveFaculty(faculty, userId, dName);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFaculty(@PathVariable long fid){
		return facultyService.deleteFaculty(fid);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllFaculty(){
		return facultyService.findAllFaculty();
	}
	
	@GetMapping("/{dName}")
	public ResponseEntity<?> findFacultyByDepartment(@PathVariable String dName){
		return facultyService.findFacultyByDepartment(dName);
	}

}
