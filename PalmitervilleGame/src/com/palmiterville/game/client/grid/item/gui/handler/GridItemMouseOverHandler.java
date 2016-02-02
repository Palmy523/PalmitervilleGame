package com.palmiterville.game.client.grid.item.gui.handler;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.palmiterville.game.client.grid.action.GridAction;

public class GridItemMouseOverHandler implements MouseOverHandler {

	private GridAction action;
	
	public GridItemMouseOverHandler(GridAction action) {
		this.action = action;
	}
	
	@Override
	public void onMouseOver(MouseOverEvent event) {
		action.processAction(event);
	}
}
