package com.palmiterville.game.client.web.gui.toolbar;

import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.web.gui.handler.clickhandler.AboutClickHandler;

public class AboutToolBarItem extends Label {

	public AboutToolBarItem() {
		super("About Palmiterville");
		setStyleName("toolBar_item");
		this.addClickHandler(new AboutClickHandler());
	}
	
}
