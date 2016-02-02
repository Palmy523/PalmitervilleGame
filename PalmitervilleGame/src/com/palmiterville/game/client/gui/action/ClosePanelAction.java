package com.palmiterville.game.client.gui.action;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Panel;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;

public class ClosePanelAction implements PalmitervilleAction {

	private Panel panel;
	
	public ClosePanelAction(Panel panel) {
		this.panel = panel;
	}
	
	@Override
	public void preProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processAction(GwtEvent e) {
		panel.setVisible(false);
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}

}
