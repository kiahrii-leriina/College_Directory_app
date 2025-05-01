package org.dbc.cda.repository;

import org.dbc.cda.entities.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminProfile, Long> {

}
