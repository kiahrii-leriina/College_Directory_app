package org.dbc.cda.dao;

import org.dbc.cda.entities.AdminProfile;
import org.dbc.cda.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;
	
	public AdminProfile saveAdmin(AdminProfile admin) {
		return adminRepository.save(admin);
	}

}
