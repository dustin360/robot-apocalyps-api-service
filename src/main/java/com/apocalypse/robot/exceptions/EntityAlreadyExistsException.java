package com.apocalypse.robot.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException() {
		super("entity already exists");
	}

}
