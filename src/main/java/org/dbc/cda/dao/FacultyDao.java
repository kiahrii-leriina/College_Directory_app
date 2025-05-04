package org.dbc.cda.dao;

import java.util.List;
import java.util.Optional;

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

	public Optional<FacultyProfile> findById(long fid) {
		return facultyRepository.findById(fid);
	}

	public void deleteFaculty(long fid) {
		facultyRepository.deleteById(fid);
	}

	public List<FacultyProfile> findAllFaculty() {
		return facultyRepository.findAll();
	}
}
