package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.exception.GridItemException;
import com.palmiterville.game.client.gui.PopupGamePanel;

public class GridItemPopupPanel extends PopupGamePanel {
	
	private boolean hasContent;
	
	public GridItemPopupPanel() {
		super();
		this.enableClose(false);
	}
	
	public void displayGridItemInfo(GridItem gridItem) throws GridItemException {
		clearGridItemInfo();
		this.show();
		contentPanel.setSize("100%", "100%");
		contentPanel.add(new GridItemInfoPanel(gridItem));
		hasContent = true;
	}
	
	public void clearGridItemInfo() {
		this.hide();
		if (hasContent) {
			contentPanel.remove(1);
			hasContent = false;
		}
	}
	


}
