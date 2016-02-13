package com.palmiterville.game.client.grid.section.gui;

import com.google.gwt.user.client.ui.SimplePanel;
import com.palmiterville.game.client.grid.component.Coordinates;

public abstract class Section extends SimplePanel {

	private Coordinates coordinates;
	private boolean isLinesShowing = false;
	public Section(int row, int column) {
		this(new Coordinates(row, column));
		this.setStyleName("section_panel");
	}
	
	public Section(Coordinates coordinates) {
		super();
		this.coordinates = coordinates;
		this.enableGridLines(true);
	}
	
	public Coordinates getGridCoordinates() {
		return coordinates;
	}	
	
	public void setGridCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setImage(String imageURL) {
		this.getElement().getStyle().setBackgroundImage("url(" +imageURL + ")");
	}
	
	/**
	 * Hides or shows the outline of the grid.
	 * 
	 * @param show - shows if true, hides if false.
	 */
	public void enableGridLines(boolean show) {
		if (show) {
			this.setStyleDependentName("grid_lines_show", show);
		} else {
			this.setStyleDependentName("grid_lines_show", show);
		}
		isLinesShowing = show;
	}
	
	/**
	 * 
	 * @return the state of the grid lines showing.
	 */
	public boolean isGridLinesShowing() {
		return isLinesShowing;
	}
}
