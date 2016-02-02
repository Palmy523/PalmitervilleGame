package com.palmiterville.game.client.web.gui.toolbar;

import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.web.gui.handler.clickhandler.HomeClickHandler;

public class HomeToolBarItem extends Label{

	public HomeToolBarItem() {
		super("Home");
		this.addClickHandler(new HomeClickHandler());
		setStyleName("toolBar_item");
	}
	
}
