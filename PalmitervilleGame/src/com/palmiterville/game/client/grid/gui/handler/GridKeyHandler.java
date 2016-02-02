package com.palmiterville.game.client.grid.gui.handler;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.palmiterville.game.client.grid.gui.BattleGrid;

public abstract class GridKeyHandler implements KeyPressHandler, KeyDownHandler {

	private BattleGrid grid;
	
	public GridKeyHandler(BattleGrid grid) {
		this.grid = grid;
	}
	
	public BattleGrid getBattleGrid() {
		return grid;
	}

	public void onKeyPress(KeyPressEvent event) {
		// TODO Auto-generated method stub
		
	}
}
