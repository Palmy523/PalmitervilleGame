package com.palmiterville.game.client.grid.section.action;

import com.palmiterville.game.client.grid.section.gui.GridSection;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;

public abstract class GridSectionAction implements PalmitervilleAction {

	private GridSection section;
	
	public GridSectionAction(GridSection section) {
		this.section = section;
	}
	
	public GridSection getGridSection() {
		return section;
	}

}
