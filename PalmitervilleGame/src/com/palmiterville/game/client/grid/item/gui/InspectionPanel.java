package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.exception.GridItemException;
import com.palmiterville.game.client.gui.PopupGamePanel;

public class InspectionPanel extends PopupGamePanel {

	private static InspectionPanel instance;
	private HorizontalPanel content;
	
	public InspectionPanel() {
		super();
		content = new HorizontalPanel();
		this.add(content);
		this.enableClose(true);
		setInstance(this);
	}
	
	public void display(GridItem primary, GridItem compareTo) throws GridItemException {
		content.clear();
		content.add(new GridItemInfoPanel(primary));
		content.add(new GridItemInfoPanel(compareTo));
		this.center();
	}
	
	
	public static InspectionPanel getInstance() {
		return (instance != null) ? instance : new InspectionPanel();
	}
	
	public void setInstance(InspectionPanel panel) {
		instance = panel;
	}
	
}
