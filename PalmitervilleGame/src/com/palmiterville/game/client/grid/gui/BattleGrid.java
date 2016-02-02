package com.palmiterville.game.client.grid.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.component.Grid;
import com.palmiterville.game.client.grid.component.GridCoordinates;
import com.palmiterville.game.client.grid.exception.GridCreationException;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.item.action.GridItemAction;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.section.gui.GridSection;

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
	private GridSection selectedSection;
	
	/**
	 * Boolean value for the state of the selectedSection.
	 */
	boolean isHighlighted;
	
	/**
	 * Flag that determines the state of the BattleGrid's initiating 
	 * action method.
	 */
	boolean isActionInitiating = false;
	
	private List<GridSection> actionAffectedSections;
	private List<GridSection> nonAffectedSections;
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
		setSelectedGridSection(0);
		this.cursor = new Cursor(this);
		actionAffectedSections = new ArrayList<>();
		nonAffectedSections = new ArrayList<>();
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
			GridSection section;
			for (int j = 0; j < grid.getWidth(); j++) {
				section = grid.getGridSectionAt(i, j);
				tempPanel.add(section);
			}
			gridPanel.add(tempPanel);
		}
		this.add(gridPanel);
	}
	
	public void highlightActionSections(GridItemAction action) {
		//If action is Integer.Max_Value return all Occupied GridSection
		if (action.getActionRange() == Integer.MAX_VALUE) {
			for (GridSection section : grid.getGridSectionMap().values()) {
				if (section.isOccupied()) {
					section.setStyleName(action.getGridSectionHighlightStyleName());
					actionAffectedSections.add(section);
				}
			}
			return;
		}
		
		//Else iterate through all sections within action range
		for (int i = 1; i <= action.getActionRange(); i++) {
			List<GridCoordinates> affected = action.getSource().getCurrentGridCoordinates().getAdjacent(i);
			for (GridCoordinates coordinates : affected) {
				GridSection section = this.getGridSectionAt(coordinates);
				if (action.allowsActionOn(section)) {
					section.setStyleName(action.getGridSectionHighlightStyleName());
					actionAffectedSections.add(section);
				} else {
					nonAffectedSections.add(section);
					section.setStyleName("action_notAllowed");
				}
			}
		}
	}
	
	public void postInitiateAction() {
		for (GridSection section : actionAffectedSections) {
			section.deselect();
		}
		for (GridSection section : nonAffectedSections) {
			section.deselect();
		}
		this.actionAffectedSections.clear();
		setIsActionInitiating(false);
	}
	
	public List<GridSection> getActionAffectedSections() {
		return actionAffectedSections;
	}
	
	public GridSection getGridSectionAt(int index) {
		return grid.getGridSectionAt(index);
	}
	
	public GridSection getGridSectionAt(int rowIndex, int columnIndex) {
		return grid.getGridSectionAt(rowIndex, columnIndex);
	}
	
	public GridSection getGridSectionAt(GridCoordinates coordinates) {
		return getGridSectionAt(coordinates.getRow(), coordinates.getColumn());
	}
	
	public void setSelectedGridSection(GridSection section) {
		selectedSection = section;
	}
	
	public void setSelectedGridSection(int index) {
		setSelectedGridSection(getGridSectionAt(index));
	}
	
	public void setSelectedGridSection(int rowNum, int colNum) {
		setSelectedGridSection(getGridSectionAt(rowNum, colNum));
	}
	
	public void attachGridItemLabel(GridItemLabel item, int row, int col) throws GridException {
		getGridSectionAt(row, col).attachGridItem(item);
	}
	
	public void attachGridItemLabel(GridItemLabel label, GridCoordinates coordinates) throws GridException {
		attachGridItemLabel(label, coordinates.getRow(), coordinates.getColumn());
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
	
	/**
	 * Initiates an action and flags the grid that an action is about to
	 * be performed.
	 * 
	 * @param action - the action to initiate.
	 */
	public void initiateTurn(HasTurn hasTurn) {
		GridSection section = this.getGridSectionAt(hasTurn.getCurrentCoordinates());
		cursor.setCursorCoordinates(section.getGridCoordinates());
	}
	
	public boolean isActionInitiating() {
		return isActionInitiating;
	}
	
	public void setIsActionInitiating(boolean isInitiating) {
		this.isActionInitiating = isInitiating;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public GridSection getSelectedSection() {
		return selectedSection;
	}
	
	public void setSelectedSection(GridSection section) {
		this.selectedSection = section;
	}
	
}
