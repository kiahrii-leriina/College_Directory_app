package org.dbc.cda.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FacultyProfile {

	@Id
	private long uId;
	
	@OneToOne
	@MapsId
	private User user;
	private String username;
	private String photo;
	@ManyToOne
	private Department department;
	private String officeTime;
	
}
