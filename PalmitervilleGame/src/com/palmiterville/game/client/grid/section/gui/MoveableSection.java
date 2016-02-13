package com.palmiterville.game.client.grid.section.gui;

import com.palmiterville.game.client.grid.component.Coordinates;

public class MoveableSection extends Section {

	public static enum SectionShape {OCTAGON, SQUARE, UNMOVEABLE};
	private SectionShape shape;
	
	public MoveableSection(int row, int column) {
		this(new Coordinates(row, column));
	}
	
	public MoveableSection(Coordinates coordinates) {
		super(coordinates);
	}
	
	public void setShape(SectionShape shape) {
		switch(shape) {
			case OCTAGON :
				this.clear();
				this.add(new Octagon());
				this.setStyleName("octagon_panel");
				break;
			case SQUARE :
				this.clear();
				this.add(new Square());
				this.setStyleName("section_panel");
				break;
		}
		this.shape = shape;
	}
	
	public SectionShape getShape() {
		return shape;
	}
}
