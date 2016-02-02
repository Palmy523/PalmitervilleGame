package com.palmiterville.game.client.grid.gui.handler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.gui.BattleGrid;

public class BattleGridKeyHandler extends GridKeyHandler {

	private BattleGrid battleGrid;
	
	public BattleGridKeyHandler(BattleGrid battleGrid) {
		super(battleGrid);
		this.battleGrid = getBattleGrid();
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
	}
	
	public void onKeyDown(KeyDownEvent event) {
		GWT.log("KEY Downed");
		int keyCode = event.getNativeKeyCode();
		if (isDirectionKey(keyCode)) {
			//Cancel the event if an action is initiating.
			if (!battleGrid.isActionInitiating()) {
				battleGrid.getCursor().moveSelection(keyCode);
			}
		}
		
		if (battleGrid.isActionInitiating() && keyCode == KeyCodes.KEY_ESCAPE) {
			BattleController.getInstance().cancelAction();
		}
	}
	
	private boolean isDirectionKey(int keyCode) {
		return keyCode == KeyCodes.KEY_UP || keyCode == KeyCodes.KEY_DOWN
				|| keyCode == KeyCodes.KEY_LEFT || keyCode == KeyCodes.KEY_RIGHT;
	}
}
