package org.dbc.cda.serviceimpl;

import java.util.Optional;

import org.dbc.cda.dao.DepartmentDao;
import org.dbc.cda.dao.FacultyDao;
import org.dbc.cda.dao.UserDao;
import org.dbc.cda.entities.Department;
import org.dbc.cda.entities.FacultyProfile;
import org.dbc.cda.entities.User;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.repository.FacultyRepository;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveFaculty(FacultyProfile faculty, long userId, String dName) {

		Optional<User> optionaluser = userDao.findById(userId);
		if (optionaluser.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid user ID: " + userId).build();
		}
		Optional<Department> optionaldept = departmentDao.findByName(dName);
		if (optionaldept.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("Invalid Department Name: " + dName).build();
		}

		User user = optionaluser.get();
		Department department = optionaldept.get();
		
		faculty.setUsername(user.getUsername());
		faculty.setUser(user);
		faculty.setDepartment(department);
		
		FacultyProfile savefaculty = facultyDao.saveFaculty(faculty);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.CREATED.value())
				.message("YOUR PROFILE HAS BEEN CREATED SUCCESSFULLY").body(faculty).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.CREATED).body(rs);

		return re;
	}
}
