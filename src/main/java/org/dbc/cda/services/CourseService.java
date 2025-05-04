package org.dbc.cda.services;

import org.dbc.cda.entities.Course;
import org.springframework.http.ResponseEntity;

public interface CourseService {

	ResponseEntity<?> saveCourse(Course course, long did, long fid);

	ResponseEntity<?> deleteCourse(long id);

	ResponseEntity<?> findAllCourse();

	ResponseEntity<?> findCourseByDepartment(String dName);


}
