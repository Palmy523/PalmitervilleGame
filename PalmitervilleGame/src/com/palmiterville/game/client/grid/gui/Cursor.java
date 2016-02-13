package com.palmiterville.game.client.grid.gui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.section.action.SectionCursorSelectionAction;
import com.palmiterville.game.client.grid.section.gui.Section;

/**
 * This is the Cursor for the BattleGrid that performs selection of items on the grid.
 * 
 * @author Dave
 *
 */
public class Cursor {

	public static enum CursorSelectionType {SINGLE, MULTIPLE};

	private Coordinates selectedCoordinates;

	private GridPanel gridPanel;
	private int numRows;
	private int numCols;

	/**
	 * The owning BattleGrid to display the Cursor on.
	 * @param gridPanel
	 */
	public Cursor(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
		numRows = gridPanel.getGrid().getHeight();
		numCols = gridPanel.getGrid().getWidth();
	}

	/**
	 * Sets the cursor selection based off the GridCoordinates of a section.
	 * 
	 * @param coordinates - the GridCoordinates of the GridSection to move the 
	 * cursor to.
	 */
	public void setCursorCoordinates(Coordinates coordinates) {
		this.setCursorCoordinates(coordinates.getRow(), coordinates.getColumn());
	}

	/**
	 * Sets the cursor selection based off the row and col index in the grid. Will
	 * not provide a selection if the row and column are out of the bounds of the grid.
	 * 
	 * @param row - the row index.
	 * @param col - the column index.
	 */
	public void setCursorCoordinates(int row, int col) {
		if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
			Section section = gridPanel.getSectionAt(row, col);
			new SectionCursorSelectionAction(gridPanel, section).processAction(null);;
		}
	}

	/**
	 * Moves the selection of the cursor based off a key using arrow keys, currently
	 * the only supported keys are direction key up, down, left, and right.
	 * 
	 * @param key - the key code
	 */
	public void moveSelection(int key) {
		int column = gridPanel.getSelectedSection().getGridCoordinates().getColumn();
		int rowIndex = gridPanel.getSelectedSection().getGridCoordinates().getColumn();
		switch(key) {
			case KeyCodes.KEY_UP : setCursorCoordinates(rowIndex - 1, column); break;
			case KeyCodes.KEY_DOWN : setCursorCoordinates(rowIndex + 1, column); break;
			case KeyCodes.KEY_LEFT : setCursorCoordinates(rowIndex, column - 1); break;
			case KeyCodes.KEY_RIGHT : setCursorCoordinates(rowIndex, column + 1); break;
		}
	}

}
