package com.palmiterville.game.client.grid.section.gui;

import com.palmiterville.game.client.grid.component.Coordinates;

public class UnmoveableSection extends Section {

	public UnmoveableSection(int row, int col) {
		this(new Coordinates(row, col));
	}
	
	public UnmoveableSection(Coordinates coordinates) {
		super(coordinates);
		this.add(new Unmoveable());
		this.setStyleName("section_panel");
	}

}
