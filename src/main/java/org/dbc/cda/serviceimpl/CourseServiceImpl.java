package org.dbc.cda.serviceimpl;

import java.util.Optional;

import org.dbc.cda.dao.CourseDao;
import org.dbc.cda.dao.DepartmentDao;
import org.dbc.cda.dao.FacultyDao;
import org.dbc.cda.entities.Course;
import org.dbc.cda.entities.Department;
import org.dbc.cda.entities.FacultyProfile;
import org.dbc.cda.exceptionClass.DuplicateRegistrationException;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.NoFacultyException;
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

}



















