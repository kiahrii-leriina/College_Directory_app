package org.dbc.cda.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dbc.cda.dao.CourseDao;
import org.dbc.cda.dao.DepartmentDao;
import org.dbc.cda.dao.FacultyDao;
import org.dbc.cda.entities.AdminProfile;
import org.dbc.cda.entities.Course;
import org.dbc.cda.entities.Department;
import org.dbc.cda.entities.FacultyProfile;
import org.dbc.cda.exceptionClass.DuplicateRegistrationException;
import org.dbc.cda.exceptionClass.NoCourseFound;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.NoFacultyException;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.repository.CourseRepository;
import org.dbc.cda.repository.DepartmentRepository;
import org.dbc.cda.repository.FacultyRepository;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private FacultyDao facultyDao;

	@Override
	public ResponseEntity<?> saveCourse(Course course, long did, long fid) {

		long cid = course.getId();
		Optional<Course> optional = courseDao.findById(cid);
		if (optional.isPresent()) {
			throw DuplicateRegistrationException.builder().message("Course Exists").build();
		}

		long deptId = did;
		Optional<Department> doptional = departmentDao.findById(deptId);
		if (doptional.isEmpty()) {
			NoDepartmentFoundException.builder().message("Invalid Department ID: " + deptId).build();
		}

		long facultyId = fid;
		Optional<FacultyProfile> foptional = facultyDao.findById(facultyId);
		if (foptional.isEmpty()) {
			throw NoFacultyException.builder().message("Invalid Faculty ID: " + facultyId).build();
		}

		Department department = doptional.get();
		FacultyProfile facultyProfile = foptional.get();

		course.setDepartment(department);
		course.setFaculty(facultyProfile);
		Course savecourse = courseDao.saveCourse(course);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course saved Successfully").body(savecourse).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re;
	}
	
	@Override
	public ResponseEntity<?> deleteCourse(long id) {
		Optional<Course> byId = courseDao.findById(id);
		if (byId.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid Course id " + id).build();
		}
		courseDao.deleteById(id);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course deleted successfully").body(null).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
		return re;	
		
	}
	
	@Override
	public ResponseEntity<?> findAllCourse() {
		List<Course> allCourse = courseDao.findAllCourse();
		if (allCourse.isEmpty()) {
			throw UserNotFoundException.builder().message("No admin profile found").build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of all Courses").body(allCourse).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;	
		
	}
	
	
	@Override
	public ResponseEntity<?> findCourseByDepartment(String dName) {
		List<Course> allCourse = courseDao.findAllCourse();
		if (allCourse.isEmpty()) {
			throw NoCourseFound.builder().message("No admin profile found").build();
		}

		Optional<Department> byName = departmentDao.findByName(dName);
		if (byName.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("No Department Found").build();
		}

		List<Course> faabd = new ArrayList<>();
		for (Course u : allCourse) {
			if (u.getDepartment().getName().equalsIgnoreCase(dName)) {
				faabd.add(u);
			}
		}

		if (faabd.isEmpty()) {
			throw NoCourseFound.builder().message("No Course found in the department " + dName).build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of Course in the department " + dName).body(faabd).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);

		return re;	}

}



















