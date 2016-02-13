package com.palmiterville.game.client.grid.exception;

public class GridBuilderException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GridBuilderException(String message) {
		super("A GridBuilderException has occured. Reason: " + message);
	}
	
	public GridBuilderException() {
		this("");
	}
}
