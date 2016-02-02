package com.palmiterville.game.client.grid.item.combatant.action;

import java.util.logging.Level;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.action.AbstractGridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.Moves;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.section.gui.GridSection;
import com.palmiterville.game.client.logger.ClientLogger;

public class MoveAction extends AbstractGridItemAction {

	public MoveAction(GridItem item) {
		super(item);
	}

	@Override
	public String getActionName() {
		return "Move";
	}
	
	@Override
	public void preProcessAction(GwtEvent e) {
		BattleController.getInstance().initiateAction(this);
		GridItemActionMenu.hideGridItemActionMenu();
	}
	
	@Override
	public boolean processAction(GwtEvent e) {
		GridItem item = this.getSource();
		GridSection destination = this.getRecipient();
		GridSection current = BattleGrid.getInstance().getGridSectionAt(item.getCurrentGridCoordinates());
		GridItemLabel label = current.getAttachedGridItemLabel();
		current.detach();
		try {
			destination.attachGridItem(label);
			return true;
		} catch (GridException e1) {
			ClientLogger.logException(Level.WARNING, e1, 
					"There was an error performing the 'Move' Action.");
		}
		return false;
	}

	@Override
	public String getActionStyleName() {
		return null;
	}

	@Override
	public String getGridSectionHighlightStyleName() {
		return "action_highlight_move";
	}

	@Override
	public int getActionRange() {
		Moves moves = (Moves) this.getSource();
		return moves.getMoveDistance();
	}

	@Override
	public boolean isSelfAction() {
		return false;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.BOTH;
	}

	@Override
	public int getActionDuration() {
		return 0;
	}

	@Override
	public boolean allowsActionOn(GridSection section) {
		if (section.isOccupied()) {
			return false;
		}
		return true;
	}

}
