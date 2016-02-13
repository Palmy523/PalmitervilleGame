package com.palmiterville.game.client.grid.section.action;

import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;
import com.palmiterville.game.client.grid.section.gui.Section;
import com.palmiterville.game.client.web.gui.action.PalmitervilleAction;

public abstract class SectionAction implements PalmitervilleAction {

	private Section section;
	
	public SectionAction(Section section) {
		this.section = section;
	}
	
	public Section getSection() {
		return section;
	}

}
