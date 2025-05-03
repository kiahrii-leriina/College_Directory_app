package org.dbc.cda.dao;

import java.util.List;

import org.dbc.cda.entities.UserEnrollment;
import org.dbc.cda.repository.UserEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserEnrollmentDao {
	
	@Autowired
	private UserEnrollmentRepository userEnrollmentRepository;

	public UserEnrollment saveEnrollment(UserEnrollment enrollment) {
		return userEnrollmentRepository.save(enrollment);
	}

	public List<UserEnrollment> findAll() {
		return userEnrollmentRepository.findAll();
	}

	

}
