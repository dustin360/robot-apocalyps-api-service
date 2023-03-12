package com.apocalypse.robot.exceptions;


public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super("not found");
	}

}
