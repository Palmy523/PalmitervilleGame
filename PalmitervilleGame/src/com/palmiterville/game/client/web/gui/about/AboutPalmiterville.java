package com.palmiterville.game.client.web.gui.about;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.web.gui.GUIUtil;

public class AboutPalmiterville extends VerticalPanel {

	private Label label = new Label("It's about the Palmiter's... Duh");
	
	public AboutPalmiterville() {
		label.setStyleName("warningLabel");
		add(label);
		this.setSize("100%", "100%");
		GUIUtil.centerCellContent(this, label);
	}
	
	
}
