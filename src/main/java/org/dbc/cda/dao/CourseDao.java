package org.dbc.cda.dao;

import java.util.List;
import java.util.Optional;

import org.dbc.cda.entities.Course;
import org.dbc.cda.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDao {
	
	@Autowired
	private CourseRepository courseRepository;

	public Optional<Course> findById(long cid) {		
		return courseRepository.findById(cid);
	}

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	public void deleteById(long id) {
		courseRepository.deleteById(id);
	}

	public List<Course> findAllCourse() {
		return courseRepository.findAll();
	}

}
