package org.dbc.cda.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

	@Id
	private long id;
	private String name;
	private String description;
	
	@ManyToOne
	private Department department;
	@ManyToOne
	private FacultyProfile faculty;
}
