package com.palmiterville.game.client.grid.exception;

public class GridException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GridException() {
		super("Grid Error");
	}
	
	public GridException(String message) {
		super("GridError: " + message);
	}
	
}
