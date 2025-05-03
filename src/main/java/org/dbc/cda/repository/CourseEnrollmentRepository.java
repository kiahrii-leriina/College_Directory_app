package org.dbc.cda.repository;

import org.dbc.cda.entities.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long>{

}
