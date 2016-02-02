package com.palmiterville.game.client.grid.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.section.gui.GridSection;


public interface GridAction {
	
	public void processAction(GwtEvent e);
	
}
