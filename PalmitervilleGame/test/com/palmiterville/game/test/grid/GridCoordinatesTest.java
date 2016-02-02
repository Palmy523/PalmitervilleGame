package com.palmiterville.game.test.grid;

import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;
import com.palmiterville.game.client.grid.component.GridCoordinates;

public class GridCoordinatesTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.palmiterville.web.Palmiterville";
	}

	public void testCreation() {
		
		GridCoordinates coordinate = new GridCoordinates(13, 27);
		
		//Test initial row and column values
		assertEquals("Coordinate row does not match row constructed with",
				coordinate.getRow(), 13);
		assertEquals("Coordinate column does not match row constructed with",
				coordinate.getColumn(), 27);		
	}
	
	public void testDiagonalValidity() {
		
		//Ensure checkDiagonalValidity 
				GridCoordinates coordinateOriginate = new GridCoordinates(5, 5);
				GridCoordinates trueSouthEast = new GridCoordinates(8, 8);
				GridCoordinates trueNorthEast = new GridCoordinates(4, 6);
				GridCoordinates	trueNorthWest = new GridCoordinates(0, 0);
				GridCoordinates trueSouthWest = new GridCoordinates(9, 1);
				GridCoordinates falseSouthEast = new GridCoordinates(7, 8);
				GridCoordinates falseNorthEast = new GridCoordinates(4, 5);
				GridCoordinates falseNorthWest = new GridCoordinates(1, 2);
				GridCoordinates falseSouthWest = new GridCoordinates(10, 2);
				GridCoordinates falseInLineEast = new GridCoordinates(5, 10);
				GridCoordinates falseInLineNorth = new GridCoordinates(2, 5);
				GridCoordinates falseInLineWest = new GridCoordinates(5, 0);
				GridCoordinates falseInLineSouth = new GridCoordinates(8, 5);
				
				assertTrue("CoordinateOriginate and trueSouthEast failed validity check", 
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, trueSouthEast));
				assertTrue("CoordinateOriginate and trueNorthEast failed validity check", 
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, trueNorthEast));
				assertTrue("CoordinateOriginate and trueNorthWest failed validity check", 
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, trueNorthWest));
				assertTrue("CoordinateOriginate and trueSouthWest failed validity check", 
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, trueSouthWest));
				assertFalse("CoordinateOriginate and falseSouthEast passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseSouthEast));
				assertFalse("CoordinateOriginate and falseNorthEast passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseNorthEast));
				assertFalse("CoordinateOriginate and falseNorthWest passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseNorthWest));
				assertFalse("CoordinateOriginate and falseSouthWest passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseSouthWest));
				assertFalse("CoordinateOriginate and falseInLineEast passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseInLineEast));
				assertFalse("CoordinateOriginate and falseInLineNorth passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseInLineNorth));
				assertFalse("CoordinateOriginate and falseInLineWest passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseInLineWest));
				assertFalse("CoordinateOriginate and falseInLineSouth passed validity check",
						GridCoordinates.checkDiagonalValidity(coordinateOriginate, falseInLineSouth));
				
	}
	
	public void testAdjency() {
		//Test getAdjacenies method
		int range = 6;
		GridCoordinates originate = new GridCoordinates(5, 5);
		
		List<GridCoordinates> adjacencies = originate.getAdjacent(range);
		for (GridCoordinates coordinates : adjacencies) {
			int maxRow = Math.max(coordinates.getRow(), originate.getRow());
			int minRow = Math.min(coordinates.getRow(), originate.getRow());
			int rowDistance = maxRow - minRow;
			
			int maxCol = Math.max(coordinates.getColumn(), originate.getColumn());
			int minCol = Math.min(coordinates.getColumn(), originate.getColumn());
			int colDistance = maxCol - minCol;
			
			int distance = rowDistance + colDistance;
			assertEquals("Distance from originate is not correct", distance, range);
		}
		
		//ensure proper number of adjacencies
		range = 5;
		adjacencies = originate.getAdjacent(range);
		assertEquals("Number of created adjancies is incorrect", range * 4, adjacencies.size());
	}
}
