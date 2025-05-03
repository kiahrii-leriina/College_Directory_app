package org.dbc.cda.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dbc.cda.dao.UserEnrollmentDao;
import org.dbc.cda.entities.UserEnrollment;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.UserEnrollmentService;
import org.dbc.cda.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserEnrollmentServiceImpl implements UserEnrollmentService {

	@Autowired
	private UserEnrollmentDao userEnrollmentDao;

	@Override
	public ResponseEntity<?> findAllEnrollment() {
		List<UserEnrollment> all = userEnrollmentDao.findAll();
		if (all.isEmpty()) {
			throw UserNotFoundException.builder().message("No Enrollments Found").build();
		}

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of all Enrollments").body(all).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findStudentEnrollment() {

		List<UserEnrollment> all = userEnrollmentDao.findAll();
		if (all.isEmpty()) {
			throw UserNotFoundException.builder().message("No Enrollments Found").build();
		}

		List<UserEnrollment> studentsEnrollment = new ArrayList<>();
		for (UserEnrollment u : all) {
			if (u.getUser().getRole() == Role.STUDENT) {
				studentsEnrollment.add(u);
			}
		}

		if (studentsEnrollment.isEmpty()) {
			throw UserNotFoundException.builder().message("No Student Enrollments found").build();
		}

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("list of student enrollments").body(studentsEnrollment).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findFacultyEnrollment() {

		List<UserEnrollment> all = userEnrollmentDao.findAll();
		if (all.isEmpty()) {
			throw UserNotFoundException.builder().message("No Enrollments Found").build();
		}

		List<UserEnrollment> facultyEnrollments = new ArrayList<>();

		for (UserEnrollment u : all) {
			if (u.getUser().getRole() == Role.FACULTY) {
				facultyEnrollments.add(u);
			}
		}

		if (facultyEnrollments.isEmpty()) {
			throw UserNotFoundException.builder().message("No Faculty Enrollment Found").build();
		}

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of Faculty Enrollments").body(facultyEnrollments).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);

		return re;
	}

	@Override
	public ResponseEntity<?> findAdminEnrollment() {

		List<UserEnrollment> all = userEnrollmentDao.findAll();
		if (all.isEmpty()) {
			throw UserNotFoundException.builder().message("No Enrollments Found").build();
		}

		List<UserEnrollment> adminEnrollments = new ArrayList<>();
		for (UserEnrollment u : all) {
			if (u.getUser().getRole() == Role.ADMIN) {
				adminEnrollments.add(u);
			}
		}
		if (adminEnrollments.isEmpty()) {
			throw UserNotFoundException.builder().message("No Admin Enrollment found").build();
		}

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of Admin Enrollments: ").body(adminEnrollments).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);

		return re;
	}

}
