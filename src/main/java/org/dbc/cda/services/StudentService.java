package org.dbc.cda.services;

import org.dbc.cda.entities.StudentProfile;
import org.springframework.http.ResponseEntity;

public interface StudentService {

	ResponseEntity<?> saveStudent(StudentProfile student, long userId, String dname);

	ResponseEntity<?> deleteStudent(long sid);

	ResponseEntity<?> findAllStudent();

	ResponseEntity<?> findStudentByDepartment(String deptName);

	
}
