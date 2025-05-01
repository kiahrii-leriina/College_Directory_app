package org.dbc.cda.exceptionClass;

import lombok.Builder;

@Builder
public class NoDepartmentFoundException extends RuntimeException{

	private String message;
	
	NoDepartmentFoundException(){
		
	}
	public NoDepartmentFoundException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
