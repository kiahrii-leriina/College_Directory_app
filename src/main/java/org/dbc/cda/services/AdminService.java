package org.dbc.cda.services;

import org.dbc.cda.entities.AdminProfile;
import org.springframework.http.ResponseEntity;

public interface AdminService {

	ResponseEntity<?> saveAdmin(AdminProfile admin, long userId, String dName);

	ResponseEntity<?> deleteAdminProfile(long id);

	ResponseEntity<?> findAllAdmin();

	ResponseEntity<?> findAdminByDepartment(String dName);


}
