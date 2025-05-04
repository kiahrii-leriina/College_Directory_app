package org.dbc.cda.dao;

import java.util.List;
import java.util.Optional;

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

	public Optional<AdminProfile> findById(long id) {
		return adminRepository.findById(id);
	}

	public void deleteById(long id) {
		adminRepository.deleteById(id);
	}

	public List<AdminProfile> findAllAdmin() {
		return adminRepository.findAll();
	}

}
