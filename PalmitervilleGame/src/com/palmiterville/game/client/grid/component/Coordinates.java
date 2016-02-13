package com.palmiterville.game.client.grid.component;

import java.util.ArrayList;
import java.util.List;

import com.palmiterville.game.client.grid.GridConstants;

/**
 * Represents the Coordinates of a point on the grid as a (row, column) point.
 * 
 * @author Dave
 */
public class Coordinates implements Comparable<Coordinates> {
	
	/**
	 * The row of the GridCoordinates.
	 */
	private int row;
	
	/**
	 * The column of the GridCoordinates.
	 */
	private int col;
	
	/**
	 * Constructs a set of GridCoordinates with the given row and column.
	 * 
	 * @param row
	 * @param col
	 */
	public Coordinates(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return col;
	}
	
	/**
	 * Returns a List of the adjacent GridCoordinates that are
	 * the supplied amount of spaces away from the grid. 
	 * 
	 * @param range - the distance requirement away from the 
	 * GridCoordinates to get the adjacent GridCoordinates of.
	 * Supplying the method with 1 will get the GridCoordinates
	 * directly north, south, east, and west of the GridCoordinates.
	 * Supplying the method with 2 will get all the GridCoordinates that 
	 * are exactly 2 spaces away. A shift in any diagonal position 
	 * (ie: northeast, or southwest) is considered 2 spaces away. Therefore
	 * supplying the method with 2 will retrieve all the GridCoordinates
	 * 2 spaces north, east, south, west, and 1 space northeast, northwest,
	 * southeast, southwest. A call of any positive number will return the
	 * sections that are diagonally in line with the north, east, south, and
	 * west GridCoordinates.
	 * 
	 * @return - a list of GridCoordinates that are exactly equal to the range
	 * spaces away from the GridCoordinates. Supplying an argument of 2 will
	 * not retrieve GridCoordinates that are 1 space away.
	 */
	public List<Coordinates> getAdjacent(int range) {
		List<Coordinates> adjacents = new ArrayList<>();
			
			Coordinates north = new Coordinates(row - range, col);
			if (north.isLegal()) {
				adjacents.add(north);
			}
			
			Coordinates south = new Coordinates(row + range, col);
			if (south.isLegal()) {
				adjacents.add(south);
			}
			
			Coordinates west = new Coordinates(row, col - range);
			if (west.isLegal()) {
				adjacents.add(west);
			}
			
			Coordinates east = new Coordinates(row, col + range);
			if (east.isLegal()) {
				adjacents.add(east);
			}
			
			int adjacentRange = range/2;
			if (adjacentRange == 1 || adjacentRange > 0) {
				adjacents.addAll(getDiagonals(north, east));
				adjacents.addAll(getDiagonals(north, west));
				adjacents.addAll(getDiagonals(south, west));
				adjacents.addAll(getDiagonals(south, east));
			}
			
		return adjacents;
	}
	
	/**
	 * 
	 * @return - true if the supplied row and col are legal GridCoordinate
	 * values.
	 */
	public boolean isLegal() {
		return row >=0 && col >= 0 
				&& row < GridConstants.GRID_MAX_HEIGHT - 1
				&& col < GridConstants.GRID_MAX_WIDTH - 1;
	}
	
	public boolean isLegal(Grid gridDimension) {
		if (isLegal()) {
			return row <= gridDimension.getWidth() && col <= gridDimension.getHeight();
		}
		return false;
	}
	
	/**
	 * Returns a list of GridCoordinates that are diagonally in line with two GridCoordinates
	 * excluding the start and end GridCoordinates.
	 * 
	 * @param start - the start GridCoordinates.
	 * @param end - the end GridCoordinates.
	 * @return - a list of all GridCoordinates diagonally in line between the two.
	 */
	public static List<Coordinates> getDiagonals(Coordinates start, Coordinates end) {
		List<Coordinates> diagonals = new ArrayList<>();
		if (!checkDiagonalValidity(start, end)) {
			return diagonals;
		}
		
		int startRow = start.getRow();
		int startColumn = start.getColumn();
		int endRow = end.getRow();
		int endColumn = end.getColumn();
	
		int iterate = Math.abs(startRow - endRow);
		Coordinates coordinates;
		if (startRow < endRow) {
			if (startColumn < endColumn) {
				for (int i = 1; i < iterate; i++) {
					coordinates = new Coordinates(startRow + i, startColumn + i);
					if (coordinates.isLegal()) {
						diagonals.add(coordinates);
					}
				}
			}
			if (startColumn > endColumn) {
				for (int i = 1; i < iterate ; i++) {
					coordinates = new Coordinates(startRow + i, startColumn - i);
					if (coordinates.isLegal()) {
						diagonals.add(coordinates);
					}
				}
			}
		}
		
		if (startRow > endRow) {
			if (startColumn < endColumn) {
				for (int i = 1; i < iterate; i++) {
					coordinates = new Coordinates(startRow - i, startColumn + i);
					if (coordinates.isLegal()) {
						diagonals.add(coordinates);
					}
				}
			}
			if (startColumn > endColumn) {
				for (int i = 1; i < iterate; i++) {
					coordinates = new Coordinates(startRow - i, startColumn - i);
					if (coordinates.isLegal()) {
						diagonals.add(coordinates);
					}
				}
			}
		}
		
		return diagonals;
	}
	
	public static boolean checkDiagonalValidity(Coordinates start, Coordinates end) {		
		if (start.getRow() == end.getRow() || start.getColumn() == end.getColumn()) {
			return false;
		}
		int rowDistance = Math.abs(start.getRow() - end.getRow());
		int columnDistance = Math.abs(start.getColumn() - end.getColumn());
		return rowDistance == columnDistance;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.col = column;
	}
	
	/**
	 * Compares a GridCoordinates instance to this instance returning
	 * the int value of the difference between this instance and the 
	 * supplied argument instance. Compares by using the value of the 
	 * row and column supplied.
	 * 
	 * return - an int greater than 0 if this instance is greater than
	 * the supplied instance. An int less than 0 if this instance is less
	 * than the supplied instance, or 0 if the two instances are equivalent.
	 */
	@Override
	public int compareTo(Coordinates coordinates) {
		return GridConstants.getCoordinateValue(this) 
				- GridConstants.getCoordinateValue(coordinates);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Coordinates))
			return false;
		Coordinates other = (Coordinates) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	public String toString() {
		return "GridCoordinates {row = " + getRow() + ", col = " + getColumn() + "}";
	}
	
	
}
