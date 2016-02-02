package com.palmiterville.game.client.grid.gui.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.palmiterville.game.client.grid.gui.action.FocusBattleGridAction;

public class FocusBattleGridClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		new FocusBattleGridAction().processAction(event);
	}

}
