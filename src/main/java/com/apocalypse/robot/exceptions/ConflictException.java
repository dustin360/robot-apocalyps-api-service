package com.apocalypse.robot.exceptions;


public class ConflictException extends RuntimeException {

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException() {
		super("a conflict was found");
	}

}
