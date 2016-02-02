package com.palmiterville.game.client.web.gui.displaytemplate;

import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.web.gui.displaytemplate.handler.DisplayWebTemplateClickHandler;

public class MenuDisplayToolbarWidget extends Label {

	private static MenuDisplayToolbarWidget instance;
	
	public MenuDisplayToolbarWidget() {
		super(">");
		setStyleName("menuDisplayToolbarWidget");
		this.setTitle("Hide/Display Header and Footer");
		this.addClickHandler(new DisplayWebTemplateClickHandler());
		setInstance(this);
	}
	
	public static void setInstance(MenuDisplayToolbarWidget widget) {
		instance = widget;
	}
	
	public static MenuDisplayToolbarWidget getInstance() {
		return (instance != null) ? instance : new MenuDisplayToolbarWidget();
	}
	
}
