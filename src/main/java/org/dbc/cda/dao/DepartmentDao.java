package org.dbc.cda.dao;

import java.util.Optional;

import org.dbc.cda.entities.Department;
import org.dbc.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class DepartmentDao {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		
		
		return departmentRepository.save(department);
	}

	public Optional<Department> findById(long did) {
		return departmentRepository.findById(did);
	}

	public Optional<Department> findByName(String dname) {
		return departmentRepository.findByName(dname);
	}

}
