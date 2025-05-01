package org.dbc.cda.services;

import org.dbc.cda.entities.Department;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {

	ResponseEntity<?> saveDepartment(Department department);

}
