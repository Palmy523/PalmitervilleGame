package com.palmiterville.game.client.grid.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.component.Grid;
import com.palmiterville.game.client.grid.exception.GridCreationException;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;
import com.palmiterville.game.client.grid.section.gui.Section;

/**
 * A widget that is a playable and interactable grid.
 * 
 * @author David Palmiter.
 *
 */
public class BattleGrid extends SimplePanel {
	
	/**
	 * Represents the selection cursor on the grid.
	 */
	private Cursor cursor;
	
	/**
	 * Represents the dimensions of the grid created.
	 */
	private Grid grid;
	
	/**
	 * The currently selected grid section.
	 */
	private Section selectedSection;
	
	/**
	 * Boolean value for the state of the selectedSection.
	 */
	boolean isHighlighted;
	
	/**
	 * The current instance of the BattleGrid.
	 */
	private static BattleGrid instance = null;
	
	/**
	 * Initializes the BattleGrid with the given number of rows and columns.
	 * 
	 * @param rows - the number of rows in the grid.
	 * @param columns - the number of columns in the grid.
	 * @throws GridCreationException - if the grid was constructed with illegal paremeters.
	 */
	public BattleGrid(int rows, int columns) throws GridCreationException {
		this(new Grid(rows, columns));
	}
	
	/**
	 * Initializes the grid with the given Grid.
	 * 
	 * @param dimension - the dimension to initialize the grid with.
	 */
	public BattleGrid(Grid grid) {
		super();
		this.grid = grid;
		this.setStyleName("battleGrid");
		createGrid();
		setSelectedSection(0);
		setInstance(this);
	}
	
	/**
	 * constructs the grid.
	 */
	private void createGrid() {
		this.clear();
		VerticalPanel gridPanel = new VerticalPanel();
		gridPanel.setStyleName("grid");
		HorizontalPanel tempPanel;
		for (int i = 0; i < grid.getHeight(); i++) {
			tempPanel = new HorizontalPanel();
			tempPanel.setStyleName("gridRow");
			Section section;
			for (int j = 0; j < grid.getWidth(); j++) {
				section = grid.getSectionAt(i, j);
				tempPanel.add(section);
			}
			gridPanel.add(tempPanel);
		}
		this.add(gridPanel);
	}
	
	public Section getSectionAt(int index) {
		return grid.getSectionAt(index);
	}
	
	public Section getSectionAt(int rowIndex, int columnIndex) {
		return grid.getSectionAt(rowIndex, columnIndex);
	}
	
	public Section getSectionAt(Coordinates coordinates) {
		return getSectionAt(coordinates.getRow(), coordinates.getColumn());
	}
	
	public void setSelectedSection(Section section) {
		selectedSection = section;
	}
	
	public void setSelectedSection(int index) {
		setSelectedSection(getSectionAt(index));
	}
	
	public void setSelectedSetion(int rowNum, int colNum) {
		setSelectedSection(getSectionAt(rowNum, colNum));
	}
	
	public static void setInstance(BattleGrid grid) {
		instance = grid;
	}
	
	public static BattleGrid getInstance() {
		return instance;
	}
	
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
	
	public Cursor getCursor() {
		return cursor;
	}
	
	
	public Grid getGrid() {
		return grid;
	}
	
}
