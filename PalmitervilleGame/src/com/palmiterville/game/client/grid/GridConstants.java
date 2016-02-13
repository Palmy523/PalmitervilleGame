package com.palmiterville.game.client.grid;

import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;


public class GridConstants {
	
	private GridConstants() {
		throw new UnsupportedOperationException("Class is not meant to be "
				+ "instantiated.");
	}
	
	public static enum Direction{NORTH, SOUTH, EAST, WEST, 
			NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST};

	/**-
	 * App constant for maximum grid creation height, if the number
	 * of digits changes a re-evaluation of the ROW_MULTIPLIER and
	 * COLUMN_MULTIPLIER may need to be made to ensure compatibility
	 * with compareTo() and equals() methods in the Grid classes.
	 */
	public static final int GRID_MAX_HEIGHT = 999;
	
	/**
	 * App constant for maximum grid creation width, if the number
	 * of digits changes a re-evaluation of the ROW_MULTIPLIER and
	 * COLUMN_MULTIPLIER may need to be made to ensure compatibility
	 * with compareTo() and equals() methods in the Grid classes.
	 */
	public static final int GRID_MAX_WIDTH = 999;
	
	/**
	 * Value for the multiplier of the column. Used in the compareTo()
	 * and equals() methods to ensure comparison.
	 */
	public static final int COLUMN_MULTIPLIER = 1;
	
	/**
	 * Value for the multiplier of the row. Used in the compareTo()
	 * and equals methods to ensure comparison.
	 */
	public static final int ROW_MULTIPLIER = 1000;
	
	public static int getGridSectionValue(GridSectionTemp section) {
		return getCoordinateValue(section.getGridCoordinates());
	}
	
	public static int getCoordinateValue(Coordinates coordinate) {
		return getRowColumnValue(coordinate.getRow(), coordinate.getColumn());
	}
	
	public static int getRowColumnValue(int rowIndex, int columnIndex) {
		return (rowIndex * ROW_MULTIPLIER) + (columnIndex * COLUMN_MULTIPLIER);
	}
	
}
