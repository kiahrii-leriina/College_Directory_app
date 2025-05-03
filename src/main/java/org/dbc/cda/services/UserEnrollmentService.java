package org.dbc.cda.services;

import org.springframework.http.ResponseEntity;

public interface UserEnrollmentService {

	ResponseEntity<?> findStudentEnrollment();

	ResponseEntity<?> findFacultyEnrollment();

	ResponseEntity<?> findAllEnrollment();

	ResponseEntity<?> findAdminEnrollment();

}
