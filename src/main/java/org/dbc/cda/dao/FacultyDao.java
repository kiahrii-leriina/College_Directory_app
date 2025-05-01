package org.dbc.cda.dao;

import org.dbc.cda.entities.FacultyProfile;
import org.dbc.cda.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyDao {

	@Autowired
	private FacultyRepository facultyRepository;

	public FacultyProfile saveFaculty(FacultyProfile faculty) {
		return facultyRepository.save(faculty);
	}
}
