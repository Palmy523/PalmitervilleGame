package com.palmiterville.game.client.grid.section.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


public class Unmoveable extends FlowPanel {

	public Unmoveable() {
		this.setStyleName("section_sub_panel");
//		Label label = new Label();
//		label.setStyleName("unmoveable");
//		this.getElement().getStyle().setBackgroundImage("url(img/grass_square.png)");
//		this.add(label);
	}
	
	public void setBackgroundImage(String imageUrl) {
		this.getElement().getStyle().setBackgroundImage("url(" + imageUrl + ")");
	}
	
}
