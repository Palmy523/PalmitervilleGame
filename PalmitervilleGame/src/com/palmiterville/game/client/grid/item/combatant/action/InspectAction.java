package com.palmiterville.game.client.grid.item.combatant.action;

import java.util.logging.Level;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.item.action.AbstractGridItemAction;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.exception.GridItemException;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.item.gui.InspectionPanel;
import com.palmiterville.game.client.grid.section.gui.GridSection;
import com.palmiterville.game.client.logger.ClientLogger;

public class InspectAction extends AbstractGridItemAction {
	
	public GridItem source;
	
	public InspectAction(GridItem source) {
		super(source);
	}
	
	public String getActionName() {
		return "Inspect";
	}
	
	@Override
	public String getActionStyleName() {
		return "gridSection";
	}

	@Override
	public int getActionRange() {
		return Integer.MAX_VALUE;
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
	public void preProcessAction(GwtEvent e) {
		BattleController.getInstance().initiateAction(this);
		GridItemActionMenu.hideGridItemActionMenu();
	}

	@Override
	public boolean processAction(GwtEvent e) {
		try {
			GridItem recipient = this.getRecipient().getAttachedGridItemLabel().getGridItem();
			InspectionPanel.getInstance().display(this.getSource(), recipient);
		} catch (GridItemException e1) {
			String message = "Failure Displaying Inspection Panel";
			ClientLogger.logException(Level.WARNING, e1, message);
			return false;
		}
		return true;
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		InspectionPanel.getInstance().hide();
	}

	@Override
	public String getGridSectionHighlightStyleName() {
		return "action_highlight_inspect";
	}

	@Override
	public boolean allowsActionOn(GridSection section) {
		if (section.isOccupied()) {
			return true;
		}
		return false;
	}

	@Override
	public int getActionDuration() {
		return 0;
	}
}
