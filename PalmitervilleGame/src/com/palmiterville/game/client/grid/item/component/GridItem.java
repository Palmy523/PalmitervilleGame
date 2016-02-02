package com.palmiterville.game.client.grid.item.component;

import java.util.ArrayList;
import java.util.List;

import com.palmiterville.game.client.grid.action.GridAction;
import com.palmiterville.game.client.grid.component.GridCoordinates;
import com.palmiterville.game.client.grid.item.gui.GridItemNameLabel;
import com.palmiterville.game.client.grid.item.gui.GridItemPictureLabel;

public abstract class GridItem implements Comparable<GridItem> {

	public static enum PlayerType {FRIENDLY, ENEMY, NEUTRAL};
	
	private static final String DEFAULT_IMAGE_URL = "img/cube_combatant.png";
	
	private static final GridCoordinates STARTING_COORDINATES = new GridCoordinates(0, 0);
	
	private int id;
	private String name;
	private GridItemPictureLabel pictureLabel;
	private GridItemNameLabel nameLabel;
	private String imageURL;
	private String menuImageURL;
	private List<GridAction> actions;
	private GridCoordinates startingCoordinates;
	private GridCoordinates currentCoordinates;
	private PlayerType playerType;
	
	/**
	 * Default constructor with no variable values set.
	 */
	public GridItem(int id, String name) {
		this.id = id;
		setName(name);
		this.setImageURL(DEFAULT_IMAGE_URL);
		this.setMenuImageURL(DEFAULT_IMAGE_URL);
		this.setPlayerType(PlayerType.FRIENDLY);
		this.setStartingCoordinates(STARTING_COORDINATES);
		actions = new ArrayList<>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public boolean allowsOccupation() {
		return false;
	}
	
	public boolean allowsAction() {
		return true;
	}
	
	public void setMenuImageURL(String menuImageURL) {
			this.menuImageURL = menuImageURL;
	}
	
	public String getMenuImageURL() {
		return menuImageURL;
	}
	
	public void addGridAction(GridAction action) {
		this.actions.add(action);
	}
	
	public GridCoordinates getStartingCoordinates() {
		return startingCoordinates;
	}
	
	public void setStartingCoordinates(GridCoordinates coordinates) {
		this.startingCoordinates = coordinates;
	}
	
	public GridCoordinates getCurrentGridCoordinates() {
		return currentCoordinates;
	}
	
	public void setCurrentGridCoordinates(GridCoordinates coordinates) {
		this.currentCoordinates = coordinates;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}
	
	public void setPlayerType(PlayerType type) {
		this.playerType = type;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int compareTo(GridItem item) {
		return this.id - item.getID();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nameLabel == null) ? 0 : nameLabel.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GridItem))
			return false;
		GridItem other = (GridItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameLabel == null) {
			if (other.nameLabel != null)
				return false;
		} else if (!nameLabel.equals(other.nameLabel))
			return false;
		return true;
	}
}
