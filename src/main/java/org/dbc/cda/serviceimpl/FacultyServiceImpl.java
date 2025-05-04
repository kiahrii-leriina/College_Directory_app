package org.dbc.cda.serviceimpl;

import java.util.ArrayList;
import java.util.List;
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

	@Override
	public ResponseEntity<?> deleteFaculty(long fid) {

		Optional<FacultyProfile> byId = facultyDao.findById(fid);
		if (byId.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid user id").build();
		}

		facultyDao.deleteFaculty(fid);
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Deleted Successfully").body(null).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findAllFaculty() {

		List<FacultyProfile> allFAculty = facultyDao.findAllFaculty();
		if (allFAculty.isEmpty()) {
			throw UserNotFoundException.builder().message("No Faculty found").build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of all Faculties").body(allFAculty).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findFacultyByDepartment(String dName) {

		List<FacultyProfile> allFaculty = facultyDao.findAllFaculty();
		if (allFaculty.isEmpty()) {
			throw UserNotFoundException.builder().message("No Faculty Found").build();
		}

		Optional<Department> byName = departmentDao.findByName(dName);
		if (byName.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("NO Department found " + dName).build();
		}

		List<FacultyProfile> afind = new ArrayList<>();
		for (FacultyProfile u : allFaculty) {
			if (u.getDepartment().getName().equalsIgnoreCase(dName)) {
				afind.add(u);
			}
		}

		if (afind.isEmpty()) {
			throw UserNotFoundException.builder().message("No Faculty Found in the Department " + dName).build();
		}

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of All faculties in the department " + dName).body(afind).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;
	}


}

























