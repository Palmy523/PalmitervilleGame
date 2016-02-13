package com.palmiterville.game.client.grid.component;

import com.palmiterville.game.client.grid.GridConstants;
import com.palmiterville.game.client.grid.exception.GridCreationException;

/**
 * Class used to represent a rectangular grid as a width (number
 * of columns) and height (number of rows) in the grid. Each grid
 * width, height number represents a GridSection and the appropriate
 * position in the grid.
 * 
 * @author Dave
 *
 */
public class Grid {

	/**
	 * The default grid height to serve.
	 */
	private static final int DEFAULT_GRID_HEIGHT = 10;
	
	/**
	 * The default grid width to serve.
	 */
	private static final int DEFAULT_GRID_WIDTH = 20;
	
	/**
	 * Represents the width of the grid. Each int value represents a 
	 * column in a grid.
	 */
	private int width;
	
	/**
	 * Represents the height of the grid. Each int value represents a
	 * row in a grid.
	 */
	private int height;
	
	/**
	 * Creates the default size Grid.
	 * 
	 * @throws GridCreationException - should not be thrown as the default values
	 * are within the bounds for the Grid. 
	 */
	public Grid() throws GridCreationException {
		this(DEFAULT_GRID_HEIGHT, DEFAULT_GRID_WIDTH);
	}
	
	/**
	 * Creates a grid with the given height and width values. 
	 * The grid utilizes 0 based indexing. A grid of height 10
	 * and width 20 will create a grid with 200 GridSections.
	 * 
	 * 
	 * @param height - the number of rows the grid will have.
	 * @param width - the number of columns the grid will have.
	 * @throws GridCreationException - thrown if the maximum height
	 * or width values have been exceeded.
	 */
	public Grid(int height, int width) throws GridCreationException {
		
		//****************THROW ERRORS FOR IMPROPER GRID CREATION***************//
		//Throw error if max height is exceeded.
		if (height > GridConstants.GRID_MAX_HEIGHT) {
			throw new GridCreationException("Exceeded maximum grid creation height of "  
					+ GridConstants.GRID_MAX_HEIGHT);
		}
		
		//Throw error if max width is exceeded.
		if (width > GridConstants.GRID_MAX_WIDTH) {
			throw new GridCreationException("Exceeded maximum grid creation width of "  
					+ GridConstants.GRID_MAX_WIDTH);
		}
		
		//Throw error if negative values are used
		if (height <= 0) {
			throw new GridCreationException("Height cannot be less than 1.");
		}
		
		if (width <= 0) {
			throw new GridCreationException("Width cannot be less than 1.");
		}
		
		//******************************INITIALIZE******************************//
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @return - the width (number of columns) in the grid.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return - the height (number of rows) in the grid.
	 */
	public int getHeight() {
		return height;
	}
	
	public Coordinates getCoordinatesAt(int index) {
		int row = index / height;
		int column = index - (row * height);
		return new Coordinates(row, column);
	}

	/**
	 * 
	 * @return - the size of the grid, represents the number of
	 * GridSections created. size = width * height.
	 */
	public int size() {
		return width * height;
	}
	
	/**
	 * Serves the default grid if an error was thrown during creation.
	 * @return - the Grid with the default height and width settings.
	 */
	public static Grid serveDefault() {
		try {
			return new Grid(DEFAULT_GRID_HEIGHT, DEFAULT_GRID_WIDTH);
			//Error should not be thrown. 
		} catch (GridCreationException e) {
			return null;
		}
	}
	
	
}
