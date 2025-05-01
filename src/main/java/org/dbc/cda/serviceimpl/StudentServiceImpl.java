package org.dbc.cda.serviceimpl;

import java.util.Optional;

import org.dbc.cda.dao.DepartmentDao;
import org.dbc.cda.dao.UserDao;
import org.dbc.cda.entities.Department;
import org.dbc.cda.entities.StudentProfile;
import org.dbc.cda.entities.User;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.repository.StudentReposiroty;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentReposiroty studentReposiroty;
	@Autowired
	private UserDao userdao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveStudent(StudentProfile student, long userId, String dname) {


		Optional<User> optionalUser = userdao.findById(userId);
		Optional<Department> optionalDept = departmentDao.findByName(dname);

		if (optionalUser.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid user ID: " + userId).build();
		}
		
		if (optionalDept.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("Invalid Department Name: " + dname).build();
		}


		Department department = optionalDept.get();

		User user = optionalUser.get();

		student.setName(user.getName());
		student.setUser(user);
		student.setDepartment(department);
		StudentProfile savestudent = studentReposiroty.save(student);
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.CREATED.value())
				.message("YOUR PROFILE IS CREATED SUCCESSFULLY").body(savestudent).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.CREATED).body(rs);

		return re;
	}
}
