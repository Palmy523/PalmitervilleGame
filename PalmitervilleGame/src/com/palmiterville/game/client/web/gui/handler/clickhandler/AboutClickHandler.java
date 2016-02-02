package com.palmiterville.game.client.web.gui.handler.clickhandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.palmiterville.game.client.web.gui.ContentPanel;
import com.palmiterville.game.client.web.gui.about.AboutPalmiterville;

public class AboutClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		ContentPanel.getInstance().loadContent(new AboutPalmiterville());
	}

}
