package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.exception.GridItemException;

public class GridItemMenu extends PopupPanel {

	private static GridItemMenu instance;
	private GridItem gridItem;
	private VerticalPanel contentPanel;
	private GridItemInfoPanel infoPanel;
	private GridItemActionMenu actionMenu;
	
	public GridItemMenu() {
		super();
		this.setStyleName("gridSectionActionMenu");
		contentPanel = new VerticalPanel();
		this.add(contentPanel);
		setInstance(this);
		setVisible(false);
	}
	
	public GridItem getGridItem() {
		return gridItem;
	}
	
	public void setGridItem(GridItem gridItem) throws GridItemException {
		this.gridItem = gridItem;
		contentPanel.clear();
		infoPanel = new GridItemInfoPanel(gridItem);
		if (gridItem instanceof Combatant) {
			Combatant combatant = (Combatant) gridItem;
			actionMenu = new GridItemActionMenu(combatant.getActionsList());
		}
		contentPanel.add(infoPanel);
		contentPanel.add(actionMenu);
	}
	
	public void setInstance(GridItemMenu instance) {
		GridItemMenu.instance = instance;
	}
	
	public static GridItemMenu getInstance() {
		return instance;
	}
	
	
	
}
