package com.palmiterville.game.client.grid.item.gui.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;

public class HideActionMenuAction implements PalmitervilleAction {

	
	public HideActionMenuAction() {
	}

	@Override
	public void preProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAction(GwtEvent e) {
		GridItemActionMenu.displayGridItemActionMenu(null);
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}

}
