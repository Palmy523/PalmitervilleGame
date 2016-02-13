package com.palmiterville.game.client.grid.item.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;

public abstract class AbstractGridItemAction implements GridItemAction {

	private GridItem item;
	private GridSectionTemp recipient;
	
	public AbstractGridItemAction(GridItem item) {
		this.item = item;
	}
	
	@Override
	public void preProcessAction(GwtEvent e) {
		BattleController.getInstance().initiateAction(this);
		GridItemActionMenu.hideGridItemActionMenu();
	}
	
	@Override
	public void postProcessAction(GwtEvent e) {
	}
	
	@Override 
	public GridItem getSource() {
		return item;
	}
	
	public GridSectionTemp getRecipient() {
		return recipient;
	}
	
	public void setRecipient(GridSectionTemp recipient) {
		this.recipient = recipient;
	}
	
}
