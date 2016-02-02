package com.palmiterville.game.client.grid.item.gui;

import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.grid.item.component.GridItem;

/**
 * This class represents the label that contains the picture
 * and level of the GridItem.
 * 
 * @author Dave
 *
 */
public class GridItemPictureLabel extends Label {

	public GridItemPictureLabel(GridItem item) {
		this(item.getMenuImageURL());
	}
	
	public GridItemPictureLabel(String imageURL) {
		super();
		this.setStyleName("grid_item_info_image");
		setImage(imageURL);
	}
	
	public void setImage(String imageURL) {
		this.getElement().getStyle().clearBackgroundImage();
		this.getElement().getStyle().setBackgroundImage("url(" + imageURL + ")");
	}
}
