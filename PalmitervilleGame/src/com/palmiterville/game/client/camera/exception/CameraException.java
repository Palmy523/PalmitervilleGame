package com.palmiterville.game.client.camera.exception;

public class CameraException extends Throwable {

	public CameraException(String message) {
		super("A CameraException has occurred. Reason: " + message);
	}
	
	public CameraException() {
		this("None");
	}
	
}
