package com.palmiterville.game.client.grid.item.exception;

import com.palmiterville.game.client.grid.exception.GridException;

public class GridItemException extends GridException {

	public GridItemException(String message) {
		super("An error occured interacting with the GridItem reason: " + message);
	}
	
	public GridItemException() {
		this("");
	}
	
}
