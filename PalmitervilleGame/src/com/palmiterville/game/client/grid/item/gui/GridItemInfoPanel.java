package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.combatant.gui.HealthBar;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.HasHealth;
import com.palmiterville.game.client.grid.item.exception.GridItemException;

public class GridItemInfoPanel extends HorizontalPanel {
	
	public GridItemInfoPanel(GridItem gridItem) throws GridItemException {
		super();
		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSize("100%", "30px");
		this.setStyleName("grid_item_info_panel");
		GridItemNameLabel nameLabel = new GridItemNameLabel(gridItem);
		GridItemPictureLabel pictureLabel = new GridItemPictureLabel(gridItem);
		rightPanel.add(nameLabel);
		if (gridItem instanceof HasHealth) {
			HasHealth hasHealth = (HasHealth) gridItem;
			HealthBar healthBar = new HealthBar(hasHealth);
			rightPanel.add(healthBar);
			rightPanel.setCellHorizontalAlignment(healthBar, HasHorizontalAlignment.ALIGN_CENTER);
			rightPanel.setCellVerticalAlignment(healthBar, HasVerticalAlignment.ALIGN_MIDDLE);
		}
		rightPanel.setCellHorizontalAlignment(nameLabel, HasHorizontalAlignment.ALIGN_CENTER);
		rightPanel.setCellVerticalAlignment(nameLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		this.add(pictureLabel);
		this.add(rightPanel);	
		this.setCellWidth(pictureLabel, "100px");
		this.setCellHorizontalAlignment(rightPanel, HasHorizontalAlignment.ALIGN_CENTER);
		
		
	}	
}
