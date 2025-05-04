package org.dbc.cda.exceptionhandler;

import org.dbc.cda.exceptionClass.NoCourseFound;
import org.dbc.cda.exceptionClass.NoDepartmentFoundException;
import org.dbc.cda.exceptionClass.NoFacultyException;
import org.dbc.cda.exceptionClass.UserNotFoundException;
import org.dbc.cda.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundException(UserNotFoundException e) {
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message(e.getMessage())
				.body(null).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
		return re;
	}

	@ExceptionHandler(NoDepartmentFoundException.class)

	public ResponseEntity<?> noDepartmentFoundException(NoDepartmentFoundException e) {
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message(e.getMessage())
				.body(null).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
		return re;
	}

	@ExceptionHandler(NoFacultyException.class)
	public ResponseEntity<?> noFacultyException(NoFacultyException e) {
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message(e.getMessage())
				.body(null).build();

		ResponseEntity re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
		return re;
	}

	@ExceptionHandler(NoCourseFound.class)
	public ResponseEntity<?> noCourseFound(NoCourseFound e) {
		ResponseStructure rs = ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message(e.getMessage())
				.body(e).build();
		ResponseEntity re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
		return re;
	}

}
