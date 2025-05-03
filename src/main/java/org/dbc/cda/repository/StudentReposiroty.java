package org.dbc.cda.repository;



import org.dbc.cda.entities.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentReposiroty extends JpaRepository<StudentProfile, Long>{


}
