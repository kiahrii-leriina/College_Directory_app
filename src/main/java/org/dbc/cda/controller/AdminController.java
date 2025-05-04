package org.dbc.cda.controller;

import org.dbc.cda.entities.AdminProfile;
import org.dbc.cda.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/{userId}/{dName}")
	public ResponseEntity<?> saveAdmin(@RequestBody AdminProfile admin, @PathVariable long userId, @PathVariable String dName){
		return adminService.saveAdmin(admin, userId, dName);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable long id){
		
		return adminService.deleteAdminProfile(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllAdmin(){
		return adminService.findAllAdmin();
	}
	
	@GetMapping("/{dName}")
	public ResponseEntity<?> findAdminByDepartment(@PathVariable String dName) {
		return adminService.findAdminByDepartment(dName);
	}
	
	
}
