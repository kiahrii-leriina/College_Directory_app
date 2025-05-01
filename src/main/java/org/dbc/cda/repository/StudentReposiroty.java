package org.dbc.cda.repository;

import java.util.Optional;

import org.dbc.cda.entities.StudentProfile;
import org.dbc.cda.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReposiroty extends JpaRepository<StudentProfile, Long>{

	Optional<User> findById(long uid);

}
