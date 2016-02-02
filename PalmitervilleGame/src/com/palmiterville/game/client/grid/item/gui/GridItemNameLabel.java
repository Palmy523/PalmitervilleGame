package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.grid.item.component.GridItem;

public class GridItemNameLabel extends Label {

	public GridItemNameLabel(GridItem item) {
		super(item.getName());
		this.setStyleName("grid_item_info_label");
	}
	
}
