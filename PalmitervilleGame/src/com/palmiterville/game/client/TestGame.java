package com.palmiterville.game.client;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.palmiterville.game.client.component.Players;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.controller.BattleException;
import com.palmiterville.game.client.grid.component.Grid;
import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.GridItem.PlayerType;

public class TestGame {

	Backdrop backdrop;
	BattleController controller;
	Widget widget;
	
	public TestGame() throws GridException {
		String hello =  "hello";
		hello.concat("wut?");
		Grid grid = new Grid(20, 25);
		BattleGrid battleGrid = new BattleGrid(grid);
		
		GridItem combatant1 = new Combatant(0, "Dude Man");
		combatant1.setPlayerType(PlayerType.FRIENDLY);
		combatant1.setStartingCoordinates(new Coordinates(4, 4));

		GridItem combatant2 = new Combatant(1, "Stinky");
		combatant2.setPlayerType(PlayerType.ENEMY);
		combatant2.setStartingCoordinates(new Coordinates(4, 3));
		
		Players players = new Players();
		players.addPlayer(combatant1);
		players.addPlayer(combatant2);
		
		backdrop = new Backdrop();
		//backdrop.setBackGroundImage("img/GridLayer.png");
		String butt = "butt";
		butt.charAt(index)
		controller = new BattleController(backdrop, battleGrid, players);
	}
	
	public void launchGame(Panel panel) throws BattleException, GridException {
		controller.launchBattle(panel);
	}
}
