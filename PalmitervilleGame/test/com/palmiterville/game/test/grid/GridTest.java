package com.palmiterville.game.test.grid;

import com.google.gwt.junit.client.GWTTestCase;
import com.palmiterville.game.client.grid.GridConstants;
import com.palmiterville.game.client.grid.component.Grid;
import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.exception.GridCreationException;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;


public class GridTest extends GWTTestCase {
	
	private Grid grid;

	public void testGridCreation() {
		assertTrue(true);
		boolean errorThrow = false;
		try {
			grid = new Grid(GridConstants.GRID_MAX_HEIGHT + 1, 20);
		} catch (GridCreationException e) {
			errorThrow = true;
		}
		assertTrue("Error was not thrown when max Grid"
				+ " height was exceeded.", true);
		
		//Ensure error is thrown when Grid width is exceeded.
		errorThrow = false;
		try {
			grid = new Grid(20, GridConstants.GRID_MAX_WIDTH + 1);
		} catch (GridCreationException e) {
			errorThrow = true;
		}
		assertTrue("Error was not thrown when max Grid"
				+ " width was exceeded.", errorThrow);
		
		errorThrow = false;
		try {
			grid = new Grid(-100, 100);
		} catch (GridCreationException e) {
			// TODO Auto-generated catch block
			errorThrow = true;
		}
		assertTrue("Error was not thrown when Grid"
				+ " height was negative.", errorThrow);
		
		errorThrow = false;
		try {
			grid = new Grid(100, -100);
		} catch (GridCreationException e) {
			errorThrow = true;
		}
		assertTrue("Error was not thrown when Grid"
				+ " width was negative.", errorThrow);
	}
	
	public void testGrid() {
		boolean errorThrow = false;
		int gridHeight = 10;
		int gridWidth = 20;
		
		//Test proper Grid creation.
		grid = Grid.serveDefault();
		try {
			grid = new Grid(gridHeight, gridWidth);
		} catch (GridCreationException e) {
			// TODO Auto-generated catch block
			errorThrow = true;
		}
		assertFalse("Error was thrown when creating Grid.", errorThrow);
		
		//Ensure proper grid height
		assertTrue("Grid Height does not match created height",
				gridHeight == grid.getHeight());
		
		//Ensure proper grid width
		assertTrue("Grid Width does not match created with", 
				gridWidth == grid.getWidth());
		
		//Ensure proper grid size
		assertTrue("Grid size of " + grid.size() + " does not match proper grid size of 200.",
				grid.size() == 200);
		
		//Ensure GridCoordinates are created properly and are assigned to the proper indices
		Coordinates coordinates = grid.getCoordinatesAt(50);
		Coordinates coordinates2 = grid.getCoordinatesAt(97);
		
		assertTrue("Coordinate returned column " + coordinates.getColumn() + ", expected 0", 
				coordinates.getColumn() == 0);
		assertTrue("Coordinate2 returned column " + coordinates.getColumn() + ", expected 7",
				coordinates2.getColumn() == 7);
		assertTrue("Coordinates returned row " + coordinates.getRow() + ", expected 5",
				coordinates.getRow() == 5);
		assertTrue("Coordinates2 returned row " + coordinates.getRow() + ", expected 9", 
				coordinates2.getRow() == 9);
		
		//Ensure GridSections mapping is correct
		GridSectionTemp section = grid.getGridSectionMap().get(coordinates);
		GridSectionTemp section2 = grid.getGridSectionMap().get(coordinates2);
		
		assertTrue("GridSection coords " + section.getGridCoordinates().toString() + " != " 
				+ coordinates.toString(), section.getGridCoordinates().equals(coordinates));
		assertTrue("GridSection coords " + section2.getGridCoordinates().toString() + " != " 
				+ coordinates2.toString(), section2.getGridCoordinates().equals(coordinates2));
		
		//Ensure map KeySet() and values() sizes are correct
		int gridSize = grid.size();
		int keySetSize = grid.getGridSectionMap().keySet().size();
		int valuesSize = grid.getGridSectionMap().values().size();
		assertTrue("Grid size of " + gridSize + " does not match KeySet() size of " 
				+ keySetSize, gridSize == keySetSize); 
		assertTrue("Grid size of " + gridSize + " does not macy values() size of "
				+ valuesSize, gridSize == valuesSize);
		}


	@Override
	public String getModuleName() {
		return "com.palmiterville.web.Palmiterville";
	}

}
