package com.palmiterville.game.client.grid.gui.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;

public class FocusBattleGridAction implements PalmitervilleAction {

	@Override
	public void preProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAction(GwtEvent e) {
		Backdrop backdrop = Backdrop.getInstance();
		if (backdrop != null) {
			backdrop.setFocus(true);
		}
		
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}


}
