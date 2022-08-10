package com.petstore.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.petstore.api.v1.model.ResponseWrapper;
import com.petstore.exception.UserNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String RESOURCE_NOT_PRESENT = "RESOURCE_NOT_PRESENT";

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseWrapper> handleResourceNotFoundException(Exception exception, WebRequest request) {
		return new ResponseEntity<>(
				new ResponseWrapper(101, RESOURCE_NOT_PRESENT, "User Not Present:" + exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}

}
