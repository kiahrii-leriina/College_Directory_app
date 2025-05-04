package org.dbc.cda.exceptionClass;

import lombok.Builder;

@Builder
public class NoCourseFound extends RuntimeException{

	private String message;
	
	NoCourseFound(){
		
	}
	public NoCourseFound(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
