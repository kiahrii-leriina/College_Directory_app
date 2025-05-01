package org.dbc.cda.serviceimpl;

import org.dbc.cda.repository.CourseRepository;
import org.dbc.cda.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
}
