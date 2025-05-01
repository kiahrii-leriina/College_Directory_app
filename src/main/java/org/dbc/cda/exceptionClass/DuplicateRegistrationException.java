package org.dbc.cda.exceptionClass;

import lombok.Builder;

@Builder
public class DuplicateRegistrationException extends RuntimeException{

	private String message;
	DuplicateRegistrationException(){
		
	}
	public DuplicateRegistrationException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
