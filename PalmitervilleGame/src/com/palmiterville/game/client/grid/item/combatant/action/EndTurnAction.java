package com.palmiterville.game.client.grid.item.combatant.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.action.AbstractGridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;

public class EndTurnAction extends AbstractGridItemAction {

	public EndTurnAction(Combatant initiator) {
		super(initiator);
	}

	public void preProcessAction(GwtEvent e) {
		BattleController.getInstance().initiateAction(this);
		GridSectionTemp source = BattleGrid.getInstance().getSectionAt(getSource().getCurrentGridCoordinates());
		BattleController.getInstance().processInitiatedAction(source);
	}
	
	@Override
	public boolean processAction(GwtEvent e) {
		return true;
	}

	@Override
	public String getActionName() {
		return "End Turn";
	}

	@Override
	public String getActionStyleName() {
		return ".gridSection";
	}

	@Override
	public int getActionDuration() {
		return 0;
	}

	@Override
	public String getGridSectionHighlightStyleName() {
		return ".gridSection";
	}

	@Override
	public int getActionRange() {
		return 0;
	}

	@Override
	public boolean isSelfAction() {
		return true;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.BOTH;
	}

	@Override
	public boolean allowsActionOn(GridSectionTemp section) {
		return true;
	}

	@Override
	public boolean endsTurn() {
		return true;
	}

	@Override
	public boolean isDisabled() {
		return false;
	}

}
