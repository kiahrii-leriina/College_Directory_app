package org.dbc.cda.controller;

import org.dbc.cda.entities.User;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.UserEnrollmentService;
import org.dbc.cda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@GetMapping
	public ResponseEntity<?> fetchAllUsers(){
		return userService.fetchAllUsers();
	}
	
	@PatchMapping("/activate/{userid}/{otp}")
	public ResponseEntity<?> activeAccount(@PathVariable long userid, @PathVariable int otp){
		return userService.activateAccount(userid, otp);
	}
	@DeleteMapping("/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable long userid){
		return userService.deleteUser(userid);
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<?> UserLogin(@PathVariable String email, @PathVariable String password){
		return userService.UserLogin(email, password);
	}
	
}
