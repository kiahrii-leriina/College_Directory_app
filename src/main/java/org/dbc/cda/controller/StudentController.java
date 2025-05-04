package org.dbc.cda.controller;

import org.dbc.cda.entities.StudentProfile;
import org.dbc.cda.services.StudentService;
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
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/{userId}/{dname}")
	public ResponseEntity<?> saveStudent(@RequestBody StudentProfile student, @PathVariable long userId, @PathVariable String dname){
		return studentService.saveStudent(student, userId, dname);
	}
	
	@DeleteMapping("/{sid}")
	public ResponseEntity<?> deleteStudent(@PathVariable long sid){
		return studentService.deleteStudent(sid);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllStudent(){
		return studentService.findAllStudent();
	}
	@GetMapping("/deptstudent/{deptName}")
	public ResponseEntity<?> findStudentByDepartment(@PathVariable String deptName){
		return studentService.findStudentByDepartment(deptName);
	}
	
}
