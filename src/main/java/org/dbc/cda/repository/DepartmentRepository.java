package org.dbc.cda.repository;

import java.util.Optional;

import org.dbc.cda.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	
	Optional<Department> findByName(String dname);

}
