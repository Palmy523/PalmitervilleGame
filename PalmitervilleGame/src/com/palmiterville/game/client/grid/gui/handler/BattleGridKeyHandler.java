package com.palmiterville.game.client.grid.gui.handler;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;

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
		//Prevent the default action from performing.
		event.preventDefault();
		int keyCode = event.getNativeKeyCode();
		
		//If an action is processing, change the behavior of the KeyDownEvent
		BattleController controller = BattleController.getInstance();
		if (controller != null) {
			if (controller.isActionInitiating()) {
				processActionEvent(keyCode, event);
				return;
			}
		}
		
		//Move the cursor
		if (isDirectionKey(keyCode)) {
			battleGrid.getCursor().moveSelection(keyCode);
		}
		
		if (keyCode == KeyCodes.KEY_ESCAPE) {
			GridItemActionMenu.hideGridItemActionMenu();
		}
	}
	
	private void processActionEvent(int keyCode, KeyDownEvent event) {
		//Cancel the event if an action is initiating.
		if (keyCode == KeyCodes.KEY_ESCAPE) {
			BattleController.getInstance().cancelAction();
		}
	}
	
	private boolean isDirectionKey(int keyCode) {
		return keyCode == KeyCodes.KEY_UP || keyCode == KeyCodes.KEY_DOWN
				|| keyCode == KeyCodes.KEY_LEFT || keyCode == KeyCodes.KEY_RIGHT;
	}
}
