package com.palmiterville.game.client.grid.exception;

public class GridCreationException extends GridException {

	
	public GridCreationException() {
		super("GridCreationError");
	}
	
	public GridCreationException(String message) {
		super("GridCreationError: " + message);
	}
}
