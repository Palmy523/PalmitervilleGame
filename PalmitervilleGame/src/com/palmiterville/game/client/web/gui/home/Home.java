package com.palmiterville.game.client.web.gui.home;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Home extends VerticalPanel {

	private Label label = new Label("This page is currently under construction");
	
	public Home() {
		this.add(label);
		this.setSize("100%", "100%");
		label.setStyleName("warningLabel");
		this.setCellHorizontalAlignment(label, HasHorizontalAlignment.ALIGN_CENTER);
		this.setCellVerticalAlignment(label, HasVerticalAlignment.ALIGN_MIDDLE);
	}
	
	
}
