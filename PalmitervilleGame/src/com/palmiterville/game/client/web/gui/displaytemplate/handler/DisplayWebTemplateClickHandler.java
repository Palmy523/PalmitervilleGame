package com.palmiterville.game.client.web.gui.displaytemplate.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.palmiterville.game.client.web.gui.displaytemplate.action.DisplayWebTemplateAction;

public class DisplayWebTemplateClickHandler implements ClickHandler {

	public DisplayWebTemplateClickHandler() {
		
	}

	@Override
	public void onClick(ClickEvent event) {
		new DisplayWebTemplateAction().processAction(event);
	}
	
}
