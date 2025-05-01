package org.dbc.cda.services;

import org.dbc.cda.entities.User;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<ResponseStructure<User>> saveUser(User user);

	ResponseEntity<?> fetchAllUsers();

	ResponseEntity<?> activateAccount(long userid, int otp);


}
