package com.palmiterville.game.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.palmiterville.game.client.gui.action.ClosePanelAction;

public class CloseButton extends Button {

	private Panel parent;
	
	public CloseButton(Panel parent) {
		super("X");
		this.parent = parent;
		addCloseAction();
		this.setStyleName("closeButton");
	}
	
	private void addCloseAction() {
		this.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				new ClosePanelAction(parent).processAction(event);
			}
		});
	}
}
