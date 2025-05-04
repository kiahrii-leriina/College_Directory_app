package org.dbc.cda.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dbc.cda.dao.DepartmentDao;
import org.dbc.cda.dao.StudentDao;
import org.dbc.cda.dao.UserDao;
import org.dbc.cda.entities.Department;
import org.dbc.cda.entities.StudentProfile;
import org.dbc.cda.entities.User;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private StudentDao studentDao;

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
		StudentProfile savestudent = studentDao.saveStudent(student);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.CREATED.value())
				.message("YOUR PROFILE IS CREATED SUCCESSFULLY").body(savestudent).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.CREATED).body(rs);

		return re;
	}

	@Override
	public ResponseEntity<?> deleteStudent(long sid) {

		Optional<StudentProfile> optional = studentDao.findById(sid);
		if (optional.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid Student id ").build();
		}
		studentDao.deleteStudent(sid);
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student profile deleted successfully").body(null).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findAllStudent() {

		List<StudentProfile> allStudent = studentDao.findAll();
		if (allStudent.isEmpty()) {
			throw UserNotFoundException.builder().message("No student profile found").build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("All Student Profiles").body(allStudent).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findStudentByDepartment(String deptName) {

		List<StudentProfile> all = studentDao.findAll();
		if (all.isEmpty()) {
			throw UserNotFoundException.builder().message("No Student profile found").build();
		}
		
		Optional<Department> byName = departmentDao.findByName(deptName);
		if(byName.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("No department found "+deptName).build();
		}

		List<StudentProfile> deptStudents = new ArrayList<>();
		for (StudentProfile u : all) {
			if (u.getDepartment().getName().equalsIgnoreCase(deptName)) {
				deptStudents.add(u);
			}
		}

		if (deptStudents.isEmpty()) {
			throw UserNotFoundException.builder().message("No Students Found in the Department " + deptName).build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("Student in " + deptName + " Department").body(deptStudents).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;
	}

}
