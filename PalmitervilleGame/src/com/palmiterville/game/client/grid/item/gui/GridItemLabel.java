package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.gui.action.DisplaySecondaryGridItemInfoAction;
import com.palmiterville.game.client.grid.item.gui.handler.GridItemMouseOutHandler;
import com.palmiterville.game.client.grid.item.gui.handler.GridItemMouseOverHandler;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;
import com.palmiterville.game.client.grid.section.handler.CursorSelectionClickHandler;

public class GridItemLabel extends Label {
	
	private GridItem gridItem;
	private GridSectionTemp hostGridSection;
	
	public GridItemLabel(GridItem gridItem) {
		this.gridItem = gridItem;
		this.setStyleName("grid_item");
		setBackgroundImage();
		DisplaySecondaryGridItemInfoAction action = new DisplaySecondaryGridItemInfoAction(gridItem, true);
		this.addMouseOverHandler(new GridItemMouseOverHandler(action));
		action = new DisplaySecondaryGridItemInfoAction(gridItem, false);
		this.addMouseOutHandler(new GridItemMouseOutHandler(action));
	}
	
	public GridItem getGridItem() {
		return gridItem;
	}
	
	private void setBackgroundImage() {
		this.getElement().getStyle().setBackgroundImage("url('" + gridItem.getImageURL() + "')");
	}
	
	public GridSectionTemp getHostingGridSection() {
		return hostGridSection;
	}
	
	public void setGridSection(GridSectionTemp section) {
		this.hostGridSection = section;
	}
}
