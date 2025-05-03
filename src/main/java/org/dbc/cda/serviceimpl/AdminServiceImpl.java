package org.dbc.cda.serviceimpl;

import java.util.Optional;

import org.dbc.cda.dao.AdminDao;
import org.dbc.cda.dao.DepartmentDao;
import org.dbc.cda.dao.UserDao;
import org.dbc.cda.entities.AdminProfile;
import org.dbc.cda.entities.Department;
import org.dbc.cda.entities.User;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.repository.AdminRepository;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveAdmin(AdminProfile admin, long userId, String dName) {

		Optional<User> optionaluser = userDao.findById(userId);
		if (optionaluser.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid User id: " + userId).build();
		}

		Optional<Department> optionaldept = departmentDao.findByName(dName);
		if (optionaldept.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("Invalid Department Name: " + dName).build();
		}

		User user = optionaluser.get();
		Department department = optionaldept.get();

		admin.setUser(user);
		admin.setDepartment(department);
		admin.setUsername(user.getUsername());

		AdminProfile saveAdmin = adminDao.saveAdmin(admin);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.CREATED.value())
				.message("YOUR PROFILE HAS BEEN CREATED SUCCESSFULLY").body(saveAdmin).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.CREATED).body(rs);

		return re;
	}
}
