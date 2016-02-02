package com.palmiterville.game.client.grid.section.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.section.action.GridSectionCursorSelectionAction;
import com.palmiterville.game.client.grid.section.gui.GridSection;

public class CursorSelectionClickHandler implements ClickHandler {

	private GridSection section;
	
	public CursorSelectionClickHandler(GridSection section) {
		this.section = section;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		new GridSectionCursorSelectionAction(BattleGrid.getInstance(), section).processAction(event);
	}
}
