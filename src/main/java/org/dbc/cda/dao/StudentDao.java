package org.dbc.cda.dao;

import java.util.List;
import java.util.Optional;

import org.dbc.cda.entities.StudentProfile;
import org.dbc.cda.repository.StudentReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

	@Autowired
	private StudentReposiroty studentReposiroty;
	
	public StudentProfile saveStudent(StudentProfile student) {
		return studentReposiroty.save(student);
	}

	public Optional<StudentProfile> findById(long sid) {
		return studentReposiroty.findById(sid);
	}

	public void deleteStudent(long sid) {
		studentReposiroty.deleteById(sid);
	}

	public List<StudentProfile> findAll() {
		return studentReposiroty.findAll();
	}


}
