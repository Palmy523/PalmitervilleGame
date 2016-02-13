package com.palmiterville.game.client.grid.section.gui;

import com.palmiterville.game.client.grid.component.Coordinates;


public class BackgroundSection extends Section {

	private static String DEFAULT_IMAGE_URL = "img/grass_square.png";
	
	public BackgroundSection(int row, int column) {
		this(new Coordinates(row, column));
	}
	
	public BackgroundSection(Coordinates coordinates) {
		super(coordinates);
		this.setStyleName("background_section");
		this.setBackgroundImage(DEFAULT_IMAGE_URL);
	}
	
	public void setBackgroundImage(String imageUrl) {
		this.getElement().getStyle().setBackgroundImage("url(" + imageUrl + ")");
	}
}
