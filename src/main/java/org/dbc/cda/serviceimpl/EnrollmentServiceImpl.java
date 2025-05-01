package org.dbc.cda.serviceimpl;

import org.dbc.cda.repository.EnrollmentRepository;
import org.dbc.cda.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;

public class EnrollmentServiceImpl implements EnrollmentService{

	@Autowired
	private EnrollmentRepository enrollmentRepository;
}
