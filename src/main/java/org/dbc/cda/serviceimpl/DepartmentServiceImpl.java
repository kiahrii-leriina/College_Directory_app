package org.dbc.cda.serviceimpl;

import org.dbc.cda.dao.DepartmentDao;

import org.dbc.cda.entities.Department;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.dbc.cda.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveDepartment(Department department) {

		Department savedepartment = departmentDao.saveDepartment(department);

		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.CREATED.value())
				.message("DEPARTMENT CREATED SUCCESSFULLY").body(savedepartment).build();
		
		ResponseEntity re = ResponseEntity.status(HttpStatus.CREATED).body(rs);
		return re;
	}
}
