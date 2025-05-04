package org.dbc.cda.services;

import org.dbc.cda.entities.FacultyProfile;
import org.springframework.http.ResponseEntity;

public interface FacultyService {

	ResponseEntity<?> saveFaculty(FacultyProfile faculty, long userId, String dName);

	ResponseEntity<?> deleteFaculty(long fid);

	ResponseEntity<?> findAllFaculty();

	ResponseEntity<?> findFacultyByDepartment(String dName);

	
}
