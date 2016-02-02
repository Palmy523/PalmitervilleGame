package com.palmiterville.game.client.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.palmiterville.game.client.component.Players;
import com.palmiterville.game.client.controller.turns.TurnQueue;
import com.palmiterville.game.client.grid.component.GridCoordinates;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.action.GridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.GridItem.PlayerType;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.section.gui.GridSection;
import com.palmiterville.game.client.gui.PopupGamePanel.PopupPosition;
import com.palmiterville.game.client.web.gui.displaytemplate.MenuDisplayToolbarWidget;

public class BattleController {

	private static BattleController instance;
	private Backdrop backdrop;
	private BattleGrid battleGrid;
	private Players players;
	private TurnQueue queue;
	private boolean isBattleInProgress = false;
	private GridItemAction initiatingAction;
	private HasTurn currentTurn;
	
	public BattleController(Backdrop backdrop, BattleGrid grid, Players players) {
		this.backdrop = backdrop;
		this.battleGrid = grid;
		this.backdrop.attachGrid(this.battleGrid);
		this.players = players;
		queue = new TurnQueue(this.players.getPlayersWithTurns());
		setInstance(this);
	}
	
	public void launchBattle(Panel panel) throws BattleException, GridException {
		if (!isBattleInProgress) {
			panel.clear();
			panel.add(backdrop);
			RootPanel.get().insert(new MenuDisplayToolbarWidget(), 0);
			for(GridItem item : players.getPlayers()) {
				battleGrid.attachGridItemLabel(new GridItemLabel(item), item.getStartingCoordinates());
			}
			backdrop.setFocus(true);
			backdrop.getGridItemPopupPanel1().setPopupPosition(PopupPosition.BACKDROP_UPPER_LEFT, false);
			backdrop.getGridItemPopupPanel2().setPopupPosition(PopupPosition.BACKDROP_UPPER_MIDDLE, false);
			initiateTurn();
			isBattleInProgress = true;
		} else {
			throw new BattleException("Battle is already in progress.");
		}
	}
	
	public void initiateTurn() {
		if (queue.size() == 1) {
			promptEnd();
		}
		initiateTurn(queue.pop());
	}
	
	private void initiateTurn(HasTurn hasTurn) {
		this.currentTurn = hasTurn;
		hasTurn.enableTurn(true);
		GridSection section = battleGrid.getGridSectionAt(hasTurn.getCurrentCoordinates());
		battleGrid.getCursor().setCursorCoordinates(section.getGridCoordinates());
	}
	
	public void cancelAction() {
		this.initiatingAction = null;
		battleGrid.postInitiateAction();
	}
	
	public void initiateAction(GridItemAction action) {
		battleGrid.setIsActionInitiating(true);
		battleGrid.highlightActionSections(action);
		backdrop.setFocus(true);
		this.initiatingAction = action;
	}
	
	public void processInitiatedAction(GridSection section) {
		if (section != null && battleGrid.getActionAffectedSections().contains(section)) {
			initiatingAction.setRecipient(section);
			if (!initiatingAction.processAction(null)) {
				return;
			};
			battleGrid.setIsActionInitiating(false);
			
			//Deselect the GridItem with the current turn.
			GridSection sourceSection = battleGrid.getGridSectionAt(initiatingAction.getSource()
					.getCurrentGridCoordinates());
			turnEnd(currentTurn);
			sourceSection.deselect();
			initiatingAction = null;
			battleGrid.postInitiateAction();
			this.initiateTurn();
			return;
		}
		cancelAction();
		
	}
	
	public void killGridItem(GridCoordinates coordinates) {
		GridSection section = battleGrid.getGridSectionAt(coordinates);
		queue.remove((Combatant) section.getAttachedGridItemLabel().getGridItem());
	}
	
	public void promptEnd() {
		if (queue.peek() instanceof GridItem) {
			GridItem item = (GridItem) queue.peek();
			if (item.getPlayerType() == PlayerType.FRIENDLY) {
				Window.alert("YOU HAVE WON!!!");
			}
			if (item.getPlayerType() == PlayerType.ENEMY) {
				Window.alert("YOU HAVE LOST!!!");
			}
		}
	}
	
	public void turnEnd(HasTurn hasTurn) {
		hasTurn.enableTurn(false);
	}
	
	public static final void setInstance(BattleController gameController) {
		BattleController.instance = gameController;
	}
	
	public static final BattleController getInstance() {
		return instance;
	}
	
}
