package org.dbc.cda.serviceimpl;

import java.util.List;


import java.util.Optional;

import org.dbc.cda.dao.AdminDao;
import org.dbc.cda.dao.FacultyDao;
import org.dbc.cda.dao.StudentDao;
import org.dbc.cda.dao.UserDao;
import org.dbc.cda.dao.UserEnrollmentDao;
import org.dbc.cda.entities.User;
import org.dbc.cda.entities.UserEnrollment;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.UserService;
import org.dbc.cda.util.EmailService;
import org.dbc.cda.util.OtpGenerator;
import org.dbc.cda.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserEnrollmentDao userEnrollmentDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private AdminDao adminDao;
	
	
	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		int otp = OtpGenerator.getOtp();
		user.setStatus(UserStatus.INACTIVE);
		user.setOtp(otp);
		User saveuser = userDao.saveUser(user);
		
		UserEnrollment enrollment = new UserEnrollment();
		enrollment.setUser(saveuser);
		UserEnrollment saveEnrollment = userEnrollmentDao.saveEnrollment(enrollment);
		emailService.registeredEmail(saveuser);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.CREATED.value())
				.message("USER SAVED SUCCESSFULLY").body(saveuser).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.CREATED).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> fetchAllUsers() {
		List<User> allusers = userDao.fetchAllUsers();
		if (allusers.isEmpty()) {
			throw UserNotFoundException.builder().message("No User Found").build();
		}

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.FOUND.value()).message("All Users")
				.body(allusers).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.FOUND).body(rs);

		return re;
	}

	@Override
	public ResponseEntity<?> activateAccount(long userid, int otp) {

		Optional<User> byId = userDao.findById(userid);
		if (byId.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid user ID: " + userid).build();
		}
		Optional<User> byOtp = userDao.findByOtp(otp);
		if (byOtp.isEmpty()) {
			throw UserNotFoundException.builder().message(" Invalid otp").build();
		}

		User user = byId.get();
		user.setStatus(UserStatus.ACTIVE);
		userDao.saveUser(user);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message(" ACCOUNT ACTIVATED SUCCESSFULLY").body(user).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re;
	}

	@Override
	public ResponseEntity<?> deleteUser(long userid) {
		Optional<User> byId = userDao.findById(userid);
		if (byId.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid user ID: " + userid).build();
		}
		User user = byId.get();
		long uid = user.getId();
		userDao.deleteUser(user);
		studentDao.deleteStudent(uid);
		facultyDao.deleteFaculty(uid);
		adminDao.deleteById(uid);
		
		
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User deleted successfully (delete id " + userid + ")").body(user).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);
		return re;
	}

	@Override
	public ResponseEntity<?> UserLogin(String email, String password) {

		Optional<User> optional = userDao.findByEmailAndPassword(email, password);
		if (optional.isEmpty()) {
			throw UserNotFoundException.builder().message("Invalid credentials").build();
		}

		User user = optional.get();

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.OK.value()).message("Login successfull")
				.body(user).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.OK).body(rs);

		return re;
	}

}
