package org.dbc.cda.exceptionClass;

import lombok.Builder;

@Builder
public class NoFacultyException extends RuntimeException{

	private String message;
	NoFacultyException(){
		
	}
	public NoFacultyException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
