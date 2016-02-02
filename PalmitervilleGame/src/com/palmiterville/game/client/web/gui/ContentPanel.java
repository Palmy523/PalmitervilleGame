package com.palmiterville.game.client.web.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.palmiterville.game.client.grid.gui.handler.FocusBattleGridClickHandler;
import com.palmiterville.game.client.web.gui.footer.Footer;
import com.palmiterville.game.client.web.gui.home.Home;
import com.palmiterville.game.client.web.gui.toolbar.HorizontalToolBar;

public class ContentPanel extends SimplePanel {

	private static final int DEFAULT_HEIGHT = 720;
	private static final int BUFFER_HEIGHT = HorizontalToolBar.TOOL_BAR_HEIGHT + Footer.FOOTER_HEIGHT;
	
	private static ContentPanel instance;
	
	public ContentPanel() {
		super();
		this.setStyleName("contentPanel");
		int height = Window.getClientHeight() - BUFFER_HEIGHT;
		if (height < 0) {
			height = DEFAULT_HEIGHT;
		}
		this.setHeight(height + "px");
		this.add(new Home());
		setInstance(this);
		this.sinkEvents(Event.ONCLICK);
		this.addHandler(new FocusBattleGridClickHandler(), ClickEvent.getType());
	}
	
	public void loadContent(Widget widget) {
		this.clear();
		this.add(widget);
		this.setSize("100%", (widget.getOffsetHeight() + BUFFER_HEIGHT) + "px");
	}
	
	public static void setInstance(ContentPanel panel) {
		instance = panel;
	}
	
	public static ContentPanel getInstance() {
		if (instance == null) {
			setInstance(new ContentPanel());
		}
		return instance;
	}
	
}
