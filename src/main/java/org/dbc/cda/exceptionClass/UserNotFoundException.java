package org.dbc.cda.exceptionClass;

import lombok.Builder;

@Builder
public class UserNotFoundException extends RuntimeException{
	
	private String message;
	
	UserNotFoundException(){
		
	}
	public UserNotFoundException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}

}
