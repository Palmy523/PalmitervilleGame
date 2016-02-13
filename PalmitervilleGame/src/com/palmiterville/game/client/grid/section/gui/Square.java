package com.palmiterville.game.client.grid.section.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class Square extends FlowPanel {

	public Square() {
		this.setStyleName("section_sub_panel");
		Label label = new Label();
		label.setStyleName("square");
		this.add(label);
//		this.getElement().getStyle().setBackgroundImage("url(img/grass_square.png)");
	}
	
}
