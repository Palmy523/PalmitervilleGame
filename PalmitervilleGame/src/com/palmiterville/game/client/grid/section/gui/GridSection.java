package com.palmiterville.game.client.grid.section.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.GridConstants;
import com.palmiterville.game.client.grid.component.GridCoordinates;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.section.handler.CursorSelectionClickHandler;

public class GridSection extends SimplePanel implements Comparable<GridSection> {

	public static final String STYLE = "gridSection";
	public static final String SELECTED_STYLE = "gridSection_selected";
	public static final String HASTURN_STYLE = "gridSection_hasTurn";
	public static final String SELECTED_HASTURN_STYLE = "gridSection_selected_hasTurn";
	private static GridSection selectedSection = null;
	public static enum SelectionType {NONE, FRIENDLY, ENEMY};
	private GridItemLabel attachedGridItemLabel;
	private GridCoordinates coordinates;
	private boolean isOccupied = false;
	private VerticalPanel content;

	public GridSection(GridCoordinates coordinates) {
		super();
		content = new VerticalPanel();
		this.add(content);
		this.coordinates = coordinates;
		this.setStyleName(STYLE);
		this.sinkEvents(Event.ONCLICK);
		this.addHandler(new CursorSelectionClickHandler(this), ClickEvent.getType());
	}

	public GridSection(int rowIndex, int columnIndex) {
		this(new GridCoordinates(rowIndex, columnIndex));
	}

	public GridCoordinates getGridCoordinates() {
		return coordinates;
	}

	public int getColumnIndex() {
		return coordinates.getColumn();
	}

	public int getRowIndex() {
		return coordinates.getRow();
	}

	public GridItemLabel getAttachedGridItemLabel() {
		return attachedGridItemLabel;
	}

	/**
	 * Attaches the selected GridItem to this section. Updates the 
	 * Style so the GridItem displays appropriately.
	 * @param gridItem
	 * @throws GridException 
	 */
	public boolean attachGridItem(GridItemLabel gridItem) throws GridException {
		if (!isOccupied) {
			this.attachedGridItemLabel = gridItem;
			attachedGridItemLabel.setGridSection(this);
			content.add(gridItem);
			gridItem.getGridItem().setCurrentGridCoordinates(this.getGridCoordinates());
			return (isOccupied = true);
		} else {
			throw new GridException("GridSection is already occupied, try removing the existing"
					+ " GridItem using the GridSection.detach() method before adding another "
					+ " GridItem.");
		}
	}

	public void detach() {
		content.clear();
		this.setStyleName(STYLE);
		this.isOccupied = false;
	}

	/**
	 * Performs a selection on the GridSection. This updates the GridSection Style to reflect
	 * the selected state based off the currently attached GridItem and display the info 
	 * of the GridItem in the InfoPopupPanel.
	 * 
	 * @param type - the type of selection to perform.
	 */
	public void select(SelectionType type) {
		if (selectedSection != null) {
			selectedSection.deselect();
		}
		if (type == SelectionType.NONE) {
			if (this.isOccupied) {
				GridItem item = this.getAttachedGridItemLabel().getGridItem();
				if (item instanceof HasTurn) {
					HasTurn hasTurn = (HasTurn) item;
					if (hasTurn.isTurn()) {
						this.setStyleName(GridSection.SELECTED_HASTURN_STYLE);
						selectedSection = this;
						return;
					}
				}
			}
		}
		this.setStyleName(GridSection.SELECTED_STYLE);
		selectedSection = this;
	}

	/**
	 * Performs a deselection of the grid section.
	 */
	public void deselect() {
		Backdrop.getInstance().getGridItemPopupPanel1().clearGridItemInfo();
		if (this.isOccupied) {
			GridItem item = this.getAttachedGridItemLabel().getGridItem();
			if (item instanceof HasTurn) {
				HasTurn hasTurn = (HasTurn) item;
				if (hasTurn.isTurn()) {
					this.setStyleName(GridSection.HASTURN_STYLE);
					return;
				}
			}
		} 
		this.setStyleName(STYLE);
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	@Override
	public String toString() {
		return "GridSection {RowIndex = " + getRowIndex() + ", "
				+ "Column = " + getColumnIndex() + ", "
				+ "GridItem = " + attachedGridItemLabel + "}";
	}


	@Override
	public int compareTo(GridSection section) {
		return (GridConstants.getGridSectionValue(this) 
				- GridConstants.getGridSectionValue(section));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
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
		if (!(obj instanceof GridSection))
			return false;
		GridSection other = (GridSection) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}




}
