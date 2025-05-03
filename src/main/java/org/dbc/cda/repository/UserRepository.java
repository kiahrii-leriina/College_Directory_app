package org.dbc.cda.repository;



import java.util.Optional;

import org.dbc.cda.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByOtp(int otp);

	Optional<User> findByEmailAndPassword(String email, String password);

	

}
