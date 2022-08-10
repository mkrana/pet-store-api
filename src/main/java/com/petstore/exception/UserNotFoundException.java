package com.petstore.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -822902301268409278L;

	public UserNotFoundException() {
		super("Resource Not Found");
	}

	public UserNotFoundException(String message) {
		super(message);

	}

	public UserNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public UserNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
