package com.palmiterville.game.client.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.palmiterville.game.client.component.Players;
import com.palmiterville.game.client.controller.turns.TurnQueue;
import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.action.GridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.GridItem.PlayerType;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;
import com.palmiterville.game.client.gui.PopupGamePanel.PopupPosition;
import com.palmiterville.game.client.web.gui.displaytemplate.MenuDisplayToolbarWidget;

/**
 * The BattleController controls setup, layout, and actions performed during 
 * the process of a Battle.
 * 
 * @author Dave
 *
 */
public class BattleController {

	/**
	 * The BattleController instance.
	 */
	private static BattleController instance;

	/**
	 * The backdrop to display, this is a panel the grid will be superimposed on.
	 */
	private Backdrop backdrop;

	/**
	 * The BattleGrid the players will interact with.
	 */
	private BattleGrid battleGrid;

	/**
	 * The Players that will partake in the Battle.
	 */
	private Players players;

	/**
	 * The queue to determine player turn order.
	 */
	private TurnQueue queue;

	/**
	 * The flag to determine if the controller is initiating a GridItem action.
	 */
	private GridItemAction initiatingAction;

	/**
	 * Determines the object on the grid whose turn it currently is.
	 */
	private HasTurn currentTurn;

	/**
	 * A list for storing the sections that are within the range and can 
	 * be affected by an action.
	 */
	private List<GridSectionTemp> actionAffectedSections;

	/**
	 * A list for storing the sections that are within the range of an action
	 * but cannot be affected by the action.
	 */
	private List<GridSectionTemp> nonAffectedSections;

	/**
	 * flag to determine if a battle is currently in progress.
	 */
	private boolean isBattleInProgress;

	private boolean isActionInitiating;

	/**
	 * Construct using the parts needed to start a Battle. 
	 * 
	 *  ---> NOTE: CONSIDER GROUPING THE PLAYERS, BACKDROP, AND GRID INTO A 
	 *  		   SINGLE CLASS CALLED 'BATTLE'
	 * 
	 * @param backdrop  - the backdrop the grid will be superimposed on.
	 * @param grid - the grid players will be attached to.
	 * @param players - a Players class that determines all the items to be
	 * attached to the grid.
	 */
	public BattleController(Backdrop backdrop, BattleGrid grid, Players players) {
		//Initialize Components
		this.backdrop = backdrop;
		this.battleGrid = grid;
		this.players = players;
		queue = new TurnQueue(this.players.getPlayersWithTurns());
		actionAffectedSections = new ArrayList<>();
		nonAffectedSections = new ArrayList<>();
		isBattleInProgress = false;
		isActionInitiating = false;
		setInstance(this);
	}

	/**
	 * Launches the Battle into the supplied panel. This method will clear the panel of all
	 * contents, displays a MenuDisplayToolbarWidget to allow the hiding of a header and footer
	 * in the page, attach the players to the grid based off their starting coordinates, add the 
	 * gui components to the panel, initialize the GridItemPopupPanels, and initiate the first turn.
	 *  
	 * @param panel - the panel the game is going to be launched into.
	 * @throws BattleException - thrown if this method is called and the Battle is already
	 * in progress.
	 * @throws GridException - thrown if there was an error attaching the grid items to
	 * the grid, or if there was an error displaying the GridItemInfoPanels.
	 */
	public void launchBattle(Panel panel) throws BattleException, GridException {
		if (!isBattleInProgress) {

			//attaches the instance BattleGrid to the backdrop.
			this.backdrop.attachGrid(this.battleGrid);

			//clears the panel of all contents
			panel.clear();

			//adds the backdrop (with attached grid) to the panel
			panel.add(backdrop);

			//inserts hide/show button to hide and show the header and footer.
			RootPanel.get().insert(new MenuDisplayToolbarWidget(), 0);

			//Attaches all players to the battlegrid
			for(GridItem item : players.getPlayers()) {
				battleGrid.attachGridSection(new GridItemLabel(item), item.getStartingCoordinates());
			}

			//sets the focus on the backdrop to allow key input.
			backdrop.setFocus(true);

			//Initializes the GridItemPopupPanels.
			backdrop.getGridItemPopupPanel1().setPopupPosition(PopupPosition.BACKDROP_UPPER_LEFT, false);
			backdrop.getGridItemPopupPanel2().setPopupPosition(PopupPosition.BACKDROP_UPPER_MIDDLE, false);

			//initiates the first turn
			initiateTurn();
			isBattleInProgress = true;
		} else {
			throw new BattleException("Battle is already in progress.");
		}
	}

	/**
	 * Initiates the next turn in the queue, will prompt the end of the game given the 
	 * circumstances for victory are complete.
	 */
	public void initiateTurn() {
		if (queue.size() == 1) {
			promptEnd();
		}
		initiateTurn(queue.pop());
	}

	/**
	 * Initiates the turn for the supplied Object that implements the HasTurn interface by 
	 * updating the cursor selection and setting the turnEnabled for the Object.
	 * 
	 * @param hasTurn - the Object that implements the HasTurn interface to initiate the turn of.
	 */
	private void initiateTurn(HasTurn hasTurn) {
		//Sets the current turn to the argument object.
		this.currentTurn = hasTurn;
		//enables the turn for the Object
		hasTurn.enableTurn(true);
		//Get the GridSection corresponding to the coordinates of the Object.
		GridSectionTemp section = battleGrid.getSectionAt(hasTurn.getCurrentCoordinates());
		//Set the cursor to the GridSection.
		battleGrid.getCursor().setCursorCoordinates(section.getGridCoordinates());
	}

	/**
	 * 
	 */
	public void cancelAction() {
		this.initiatingAction = null;
		this.isActionInitiating = false;
		unhighlightSections();
	}

	public void initiateAction(GridItemAction action) {
		this.isActionInitiating = true;
		this.populateAllowableActionSections(action);
		backdrop.setFocus(true);
		this.initiatingAction = action;
	}

	public void processInitiatedAction(GridSectionTemp section) {
		GridSectionTemp sourceSection = battleGrid.getSectionAt(initiatingAction.getSource()
				.getCurrentGridCoordinates());
		if (section != null && actionAffectedSections.contains(section) 
				&& initiatingAction.allowsActionOn(section)) {
			initiatingAction.setRecipient(section);
			if (!initiatingAction.processAction(null)) {
				return;
			};
			if (initiatingAction.endsTurn()) {
				//End the turn
				sourceSection.deselect();
				turnEnd(currentTurn);
				initiateTurn();
				initiatingAction.postProcessAction(null);
			}
			unhighlightSections();
			initiatingAction = null;
			this.isActionInitiating = false;
			this.actionAffectedSections.clear();
			//Set the cursor to the GridSection.
			battleGrid.getCursor().setCursorCoordinates(currentTurn.getCurrentCoordinates());
			return;
		}

		if (section != null && nonAffectedSections.contains(section)) {
			return;
		}
		

		cancelAction();
	}

	public void killGridItem(Coordinates coordinates) {
		GridSectionTemp section = battleGrid.getSectionAt(coordinates);
		queue.remove((Combatant) section.getAttachedGridItemLabel().getGridItem());
	}

	private void promptEnd() {
		if (queue.peek() instanceof GridItem) {
			GridItem item = (GridItem) queue.peek();
			if (item.getPlayerType() == PlayerType.FRIENDLY) {
				Window.alert("You kicked ass!!!");
			}
			if (item.getPlayerType() == PlayerType.ENEMY) {
				Window.alert("Beat your ass!!!!");
			}
		}
	}

	/**
	 * Highlights the Grid based off the 
	 * @param action
	 */
	private void populateAllowableActionSections(GridItemAction action) {
		GridSectionTemp self = battleGrid.getSectionAt(action.getSource().getCurrentGridCoordinates());
		
		//Determine if action is self action and add accordingly
		if (action.isSelfAction()) {
			self.setStyleName(action.getGridSectionHighlightStyleName());
			actionAffectedSections.add(self);
		} else {
			nonAffectedSections.add(self);
			self.setStyleName("action_notAllowed");
		}
		
		//If action is Integer.Max_Value return all Occupied GridSection
		if (action.getActionRange() == Integer.MAX_VALUE) {
			for (GridSectionTemp section : battleGrid.getGrid().getGridSectionMap().values()) {
				if (section == null) {
					continue;
				}
				if (section.isOccupied()) {
					if (action.allowsActionOn(section)) {
						self.setStyleName(action.getGridSectionHighlightStyleName());
						actionAffectedSections.add(self);	
					} else {
						nonAffectedSections.add(self);
						self.setStyleName("action_notAllowed");
					}
				}
			}
			return;
		}
		
		//Add all items within range
		for (int i = 1; i <= action.getActionRange(); i++) {
			List<Coordinates> affected = action.getSource().getCurrentGridCoordinates().getAdjacent(i);
			for (Coordinates coordinates : affected) {
				GridSectionTemp section = battleGrid.getSectionAt(coordinates);
				if (section == null) {
					continue;
				}
				if (action.allowsActionOn(section)) {
					section.setStyleName(action.getGridSectionHighlightStyleName());
					actionAffectedSections.add(section);
				} else {
					nonAffectedSections.add(section);
					section.setStyleName("action_notAllowed");
				}
			}
		}
	}

	public void unhighlightSections() {
		for (GridSectionTemp section : actionAffectedSections) {
			section.deselect();
		}
		for (GridSectionTemp section : nonAffectedSections) {
			section.deselect();
		}
	}

	public void turnEnd(HasTurn hasTurn) {
		hasTurn.enableTurn(false);
	}

	public static final void setInstance(BattleController gameController) {
		BattleController.instance = gameController;
	}

	public boolean isActionInitiating() {
		return isActionInitiating;
	}

	public static final BattleController getInstance() {
		return instance;
	}

}
