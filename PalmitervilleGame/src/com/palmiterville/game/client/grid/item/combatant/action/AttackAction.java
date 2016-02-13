package com.palmiterville.game.client.grid.item.combatant.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.item.action.AbstractGridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.AttackableGridItem;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.GridItem.PlayerType;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;

/**
 * Performs an attack from a combatant to an AttackableGridItem.
 * 
 * @author Dave
 *
 */
public class AttackAction extends AbstractGridItemAction {

	public boolean hasAttackPerformed = false;
	
	public AttackAction(Combatant initiator) {
		super(initiator);
	}

	@Override
	public void preProcessAction(GwtEvent e) {
		BattleController.getInstance().initiateAction(this);
		GridItemActionMenu.hideGridItemActionMenu();
	}
	
	@Override
	public boolean processAction(GwtEvent e) {
		GridItem recipient = this.getRecipient().getAttachedGridItemLabel().getGridItem();
		Combatant initiator = (Combatant) getSource();
		if (recipient instanceof AttackableGridItem) {
			AttackableGridItem attackable = (AttackableGridItem) recipient;
			initiator.attack((AttackableGridItem) recipient);
			if (attackable.getHealth() <= 0) {
				BattleController.getInstance().killGridItem(recipient.getCurrentGridCoordinates());
			}
			hasAttackPerformed = true;
			return true;
		}
		return false;
	}
	
	@Override
	public void postProcessAction(GwtEvent e) {
		hasAttackPerformed = false;
	}

	public String getActionName() {
		return "Attack";
	}
	
	@Override
	public String getActionStyleName() {
		return "action_attack";
	}

	@Override
	public boolean isSelfAction() {
		return false;
	}

	@Override
	public String getGridSectionHighlightStyleName() {
		return "action_highlight_attack";
	}

	@Override
	public ActionType getActionType() {
		return ActionType.ENEMY;
	}

	@Override
	public boolean allowsActionOn(GridSectionTemp section) {
		if (section.isOccupied()) {
			GridItem source = getSource();
			PlayerType sourceType = source.getPlayerType();
			GridItem recipient = section.getAttachedGridItemLabel().getGridItem();
			PlayerType recipientType = recipient.getPlayerType();
			if (sourceType == PlayerType.FRIENDLY) {
				if (recipientType == PlayerType.ENEMY || recipientType == PlayerType.NEUTRAL
						&& recipient instanceof AttackableGridItem) {
					return true;
				}
			}
			if (sourceType == PlayerType.ENEMY) {
				if (recipientType == PlayerType.FRIENDLY || recipientType == PlayerType.NEUTRAL 
						&& recipient instanceof AttackableGridItem) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getActionRange() {
		Combatant initiator = (Combatant) getSource();
		return initiator.getAttackRange();
	}

	@Override
	public int getActionDuration() {
		return 0;
	}

	@Override
	public boolean endsTurn() {
		return hasAttackPerformed;
	}

	@Override
	public boolean isDisabled() {
		return hasAttackPerformed;
	}
}
