package com.palmiterville.game.client.web.gui.action;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Event;


public interface PalmitervilleAction {
	
	void preProcessAction(GwtEvent e);
	
	void processAction(GwtEvent e);
	
	void postProcessAction(GwtEvent e);
	
}
