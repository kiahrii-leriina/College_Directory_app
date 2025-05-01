package org.dbc.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.dbc.cda.dao.UserDao;

import org.dbc.cda.entities.User;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailService emailService;

	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		int otp = OtpGenerator.getOtp();
		user.setStatus(UserStatus.INACTIVE);
		user.setOtp(otp);
		User saveuser = userDao.saveUser(user);
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

}
