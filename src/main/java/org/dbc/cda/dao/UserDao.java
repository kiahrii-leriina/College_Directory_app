package org.dbc.cda.dao;

import java.util.List;
import java.util.Optional;

import org.dbc.cda.entities.User;
import org.dbc.cda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findByOtp(int otp) {
		return userRepository.findByOtp(otp);
	}

	public void deleteById(long userid) {
		userRepository.deleteById(userid);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	




	

}
