package com.palmiterville.game.client.grid.section.handler;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.palmiterville.game.client.grid.section.gui.Octagon;


public class OctagonHoverHandler implements MouseOverHandler, MouseOutHandler {

	private Octagon octagon;
	
	public OctagonHoverHandler(Octagon octagon) {
		this.octagon = octagon;
	}
	
	@Override
	public void onMouseOver(MouseOverEvent event) {
		octagon.highLightOctagon(true);
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		octagon.highLightOctagon(false);
	}

	
	
}
