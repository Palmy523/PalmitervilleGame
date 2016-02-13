package com.palmiterville.game.client.grid.section.gui;

import com.palmiterville.game.client.grid.component.Coordinates;

public class EmptySection extends Section {
	
	public EmptySection(int row, int column) {
		this(new Coordinates(row, column));
	}
	
	public EmptySection(Coordinates coordinates) {
		super(coordinates);
		this.setStyleName("empty_section");
	}
}
