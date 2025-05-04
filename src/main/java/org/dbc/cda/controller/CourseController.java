package org.dbc.cda.controller;

import org.dbc.cda.entities.Course;
import org.dbc.cda.services.CourseService;
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
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/{did}/{fid}")
	public ResponseEntity<?> saveCourse(@RequestBody Course course, @PathVariable long did, @PathVariable long fid){
		return courseService.saveCourse(course, did, fid);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable long id){
		
		return courseService.deleteCourse(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllCourse(){
		return courseService.findAllCourse();
	}
	
	@GetMapping("/{dName}")
	public ResponseEntity<?> findCourseByDepartment(@PathVariable String dName) {
		return courseService.findCourseByDepartment(dName);
	}
	
}
