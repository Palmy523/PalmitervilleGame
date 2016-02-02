package com.palmiterville.game.client.grid.item.gui.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;

public class DisplayActionMenuAction implements PalmitervilleAction {

	private GridItemLabel label;
	
	
	public DisplayActionMenuAction(GridItemLabel label) {
		this.label = label;
	}

	@Override
	public void preProcessAction(GwtEvent e) {
	}

	@Override
	public void processAction(GwtEvent e) {
		GridItemActionMenu.displayGridItemActionMenu(label);
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
