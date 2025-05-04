package org.dbc.cda.serviceimpl;

import java.util.ArrayList;
import java.util.List;
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

	@Override
	public ResponseEntity<?> deleteAdminProfile(long id) {

		Optional<AdminProfile> byId = adminDao.findById(id);
		if (byId.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid user id " + id).build();
		}
		adminDao.deleteById(id);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Admin Profile deleted successfully").body(null).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findAllAdmin() {

		List<AdminProfile> allAdmin = adminDao.findAllAdmin();
		if (allAdmin.isEmpty()) {
			throw UserNotFoundException.builder().message("No admin profile found").build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("Lisst of all Admins").body(allAdmin).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> findAdminByDepartment(String dName) {

		List<AdminProfile> allAdmin = adminDao.findAllAdmin();
		if (allAdmin.isEmpty()) {
			throw UserNotFoundException.builder().message("No admin profile found").build();
		}

		Optional<Department> byName = departmentDao.findByName(dName);
		if (byName.isEmpty()) {
			throw NoDepartmentFoundException.builder().message("No Department Found").build();
		}

		List<AdminProfile> faabd = new ArrayList<>();
		for (AdminProfile u : allAdmin) {
			if (u.getDepartment().getName().equalsIgnoreCase(dName)) {
				faabd.add(u);
			}
		}

		if (faabd.isEmpty()) {
			throw UserNotFoundException.builder().message("No admin found in the department " + dName).build();
		}
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value())
				.message("List of Admins in the department " + dName).body(faabd).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);

		return re;
	}

}
