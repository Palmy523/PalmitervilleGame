package com.palmiterville.game.client.grid.section.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.palmiterville.game.client.grid.gui.GridPanel;
import com.palmiterville.game.client.grid.section.action.SectionCursorSelectionAction;
import com.palmiterville.game.client.grid.section.gui.Section;

public class CursorSelectionClickHandler implements ClickHandler {

	private Section section;
	private GridPanel panel;
	
	public CursorSelectionClickHandler(GridPanel panel, Section section) {
		this.section = section;
		this.panel = panel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		new SectionCursorSelectionAction(panel, section).processAction(event);
	}
}
