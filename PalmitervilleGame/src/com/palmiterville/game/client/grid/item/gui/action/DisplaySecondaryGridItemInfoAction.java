package com.palmiterville.game.client.grid.item.gui.action;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.action.GridAction;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.gui.GridItemPopupPanel;
import com.palmiterville.game.client.gui.PopupGamePanel.PopupPosition;

public class DisplaySecondaryGridItemInfoAction implements GridAction {

	private static GridItemPopupPanel panel;
	private GridItem item;
	boolean display;
	
	public DisplaySecondaryGridItemInfoAction(GridItem item, boolean display) {
		this.item = item;
		this.display = display;
	}

	@Override
	public void processAction(GwtEvent e) {
		if (panel == null) {
			panel = new GridItemPopupPanel();
		}
		try {
			if (display) {
				panel.setPopupPosition(PopupPosition.BACKDROP_UPPER_MIDDLE, true);
				panel.displayGridItemInfo(item);
			} else {
				panel.hide();
			}
		} catch (GridException e1) {
			GWT.log(e1.getMessage(), e1);
		}
	}
}
