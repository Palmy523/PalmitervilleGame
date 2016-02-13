package com.palmiterville.game.test.grid;

import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;
import com.palmiterville.game.client.grid.component.Coordinates;

public class GridCoordinatesTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.palmiterville.web.Palmiterville";
	}

	public void testCreation() {
		
		Coordinates coordinate = new Coordinates(13, 27);
		
		//Test initial row and column values
		assertEquals("Coordinate row does not match row constructed with",
				coordinate.getRow(), 13);
		assertEquals("Coordinate column does not match row constructed with",
				coordinate.getColumn(), 27);		
	}
	
	public void testDiagonalValidity() {
		
		//Ensure checkDiagonalValidity 
				Coordinates coordinateOriginate = new Coordinates(5, 5);
				Coordinates trueSouthEast = new Coordinates(8, 8);
				Coordinates trueNorthEast = new Coordinates(4, 6);
				Coordinates	trueNorthWest = new Coordinates(0, 0);
				Coordinates trueSouthWest = new Coordinates(9, 1);
				Coordinates falseSouthEast = new Coordinates(7, 8);
				Coordinates falseNorthEast = new Coordinates(4, 5);
				Coordinates falseNorthWest = new Coordinates(1, 2);
				Coordinates falseSouthWest = new Coordinates(10, 2);
				Coordinates falseInLineEast = new Coordinates(5, 10);
				Coordinates falseInLineNorth = new Coordinates(2, 5);
				Coordinates falseInLineWest = new Coordinates(5, 0);
				Coordinates falseInLineSouth = new Coordinates(8, 5);
				
				assertTrue("CoordinateOriginate and trueSouthEast failed validity check", 
						Coordinates.checkDiagonalValidity(coordinateOriginate, trueSouthEast));
				assertTrue("CoordinateOriginate and trueNorthEast failed validity check", 
						Coordinates.checkDiagonalValidity(coordinateOriginate, trueNorthEast));
				assertTrue("CoordinateOriginate and trueNorthWest failed validity check", 
						Coordinates.checkDiagonalValidity(coordinateOriginate, trueNorthWest));
				assertTrue("CoordinateOriginate and trueSouthWest failed validity check", 
						Coordinates.checkDiagonalValidity(coordinateOriginate, trueSouthWest));
				assertFalse("CoordinateOriginate and falseSouthEast passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseSouthEast));
				assertFalse("CoordinateOriginate and falseNorthEast passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseNorthEast));
				assertFalse("CoordinateOriginate and falseNorthWest passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseNorthWest));
				assertFalse("CoordinateOriginate and falseSouthWest passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseSouthWest));
				assertFalse("CoordinateOriginate and falseInLineEast passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseInLineEast));
				assertFalse("CoordinateOriginate and falseInLineNorth passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseInLineNorth));
				assertFalse("CoordinateOriginate and falseInLineWest passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseInLineWest));
				assertFalse("CoordinateOriginate and falseInLineSouth passed validity check",
						Coordinates.checkDiagonalValidity(coordinateOriginate, falseInLineSouth));
				
	}
	
	public void testAdjency() {
		//Test getAdjacenies method
		int range = 6;
		Coordinates originate = new Coordinates(5, 5);
		
		List<Coordinates> adjacencies = originate.getAdjacent(range);
		for (Coordinates coordinates : adjacencies) {
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
