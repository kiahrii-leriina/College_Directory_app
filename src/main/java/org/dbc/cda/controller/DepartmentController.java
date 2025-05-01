package org.dbc.cda.controller;

import org.dbc.cda.entities.Department;
import org.dbc.cda.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<?> saveDepartment(@RequestBody Department department){
		return departmentService.saveDepartment(department);
	}
}
