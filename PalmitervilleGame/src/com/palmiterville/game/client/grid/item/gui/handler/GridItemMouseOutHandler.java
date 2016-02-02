package com.palmiterville.game.client.grid.item.gui.handler;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.palmiterville.game.client.grid.action.GridAction;

public class GridItemMouseOutHandler implements MouseOutHandler {

	private GridAction action;
	
	public GridItemMouseOutHandler(GridAction action) {
		this.action = action;
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		action.processAction(event);
	}
	
	
	
}
