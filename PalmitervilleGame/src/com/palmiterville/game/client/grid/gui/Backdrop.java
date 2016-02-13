package com.palmiterville.game.client.grid.gui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.palmiterville.game.client.grid.gui.handler.BattleGridKeyHandler;
import com.palmiterville.game.client.grid.item.gui.GridItemPopupPanel;

public class Backdrop extends FocusPanel {

	private static Backdrop instance;
	private GridItemPopupPanel gridItemPopupPanel1;
	private GridItemPopupPanel gridItemPopupPanel2;
	
	public Backdrop() {
		super();
		setStyles();
		gridItemPopupPanel1 = new GridItemPopupPanel();
		gridItemPopupPanel2 = new GridItemPopupPanel();
		setInstance(this);
	}
	
	public void attachGrid(BattleGrid grid) {
		this.add(grid);
		grid.setSelectedSection(0);
		this.addKeyDownHandler(new BattleGridKeyHandler(grid));
		this.setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");
	}
	
	private void setStyles() {
		this.setStyleName("backdrop");
	}
	
	public void setBackGroundImage(String imageURL) {
		this.getElement().getStyle().clearBackgroundImage();
		this.getElement().getStyle().setBackgroundImage("url(" + imageURL + ")");
	}
	
	public static void setInstance(Backdrop backdrop) {
		instance = backdrop;
	}
	
	public static Backdrop getInstance() {
		return (instance != null) ? instance : new Backdrop();
	}
	
	public GridItemPopupPanel getGridItemPopupPanel1() {
		return gridItemPopupPanel1;
	}
	
	public GridItemPopupPanel getGridItemPopupPanel2() {
		return gridItemPopupPanel2;
	}
	
	
	
}
