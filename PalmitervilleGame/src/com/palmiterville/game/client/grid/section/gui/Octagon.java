package com.palmiterville.game.client.grid.section.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.grid.section.handler.OctagonHoverHandler;

public class Octagon extends FlowPanel {

	private Label top;
	private Label middle;
	private Label bottom;
	
	public Octagon() {
		this.setStyleName("section_sub_panel");
//		this.setImage("img/grass_square.png");
		
		top = new Label();
		top.setStyleName("octagon_top");
		top.addMouseOverHandler(new OctagonHoverHandler(this));
		top.addMouseOutHandler(new OctagonHoverHandler(this));

		middle = new Label();
		middle.setStyleName("octagon");
		middle.addMouseOverHandler(new OctagonHoverHandler(this));
		middle.addMouseOutHandler(new OctagonHoverHandler(this));
		
		bottom = new Label();
		bottom.setStyleName("octagon_bottom");
		bottom.addMouseOverHandler(new OctagonHoverHandler(this));
		bottom.addMouseOutHandler(new OctagonHoverHandler(this));
		
		this.add(top);
		this.add(middle);
		this.add(bottom);
	}
	
	public void setImage(String imageURL) {
		this.getElement().getStyle().setBackgroundImage("url(" +imageURL + ")");
	}
	
	/**
	 * Highlights the Octagon with the hover style set in the style sheet.
	 * @param highlight
	 */
	public void highLightOctagon(boolean highlight) {
		if (highlight) {
			top.setStyleName("octagon_top_hover");
			middle.setStyleName("octagon_hover");
			bottom.setStyleName("octagon_bottom_hover");
		} else {
			top.setStyleName("octagon_top");
			middle.setStyleName("octagon");
			bottom.setStyleName("octagon_bottom");
		}
	}
	
}
