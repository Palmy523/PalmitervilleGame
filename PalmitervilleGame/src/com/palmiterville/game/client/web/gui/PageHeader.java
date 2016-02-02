package com.palmiterville.game.client.web.gui;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.palmiterville.game.client.web.gui.handler.clickhandler.HomeClickHandler;

public class PageHeader extends SimplePanel {
	
	public static final int HEADER_HEIGHT = 100;
	
	private static PageHeader instance;
	
	private Image logo = new Image("img/Palmiterville_Logo_400x100.png");
	
	public PageHeader() {
		this.add(logo);
		setStyleName("pageHeader");
		logo.setStyleName("headerImage");
		logo.setTitle("Home");
		addHomeLink();
		setInstance(this);
	}
	
	private void addHomeLink() {
		logo.addClickHandler(new HomeClickHandler());
	}
	
	public static void setInstance(PageHeader header) {
		instance = header;
	}
	
	public static PageHeader getInstance() {
		return (instance != null) ? instance : new PageHeader();
	}
}
