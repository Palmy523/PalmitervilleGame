package com.palmiterville.game.client.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.palmiterville.game.client.grid.component.Coordinates;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.exception.GridItemException;


/**
 * Creates a set of players to be attached to a grid and act as the players in a 
 * Battle. Automatically checks for duplicate GridItem entries, and throws errors for 
 * doubly occupied starting GridCoordinates for GridItems. 
 * 
 * @author Dave
 *
 */
public class Players {
	
	//private Map<GridItem, GridItem> playerMap;
	private Set<GridItem> players;
	private Set<Coordinates> occupiedCoordinates;
	private Set<HasTurn> playersWithTurns;
	
	public Players() {
		//playerMap = new HashMap<>();
		players = new TreeSet<>();
		occupiedCoordinates = new TreeSet<Coordinates>();
		playersWithTurns = new TreeSet<>();
	}
	
	/**
	 * Adds a GridItem to the list of players. Checks if the player already exists in the 
	 * players list. If so the player will not be added. Checks if the player coordinates 
	 * are equal to any other GridItem coordinates already added, if so the player will not 
	 * be added and an exception will be thrown. 
	 * 
	 * @param item - the GridItem to be added to the player list.
	 * @return - true if added, false if the list already contains the GridItem.
	 * @throws GridItemException - if the coordinates of the GridItem are equal to the coordinates
	 * of an already existing player in the players list.
	 */
	public boolean addPlayer(GridItem item) throws GridItemException {
		if (players.add(item)) {
			if (occupiedCoordinates.add(item.getStartingCoordinates())) {
				return (item instanceof HasTurn) ? playersWithTurns.add((HasTurn) item) : true;
			} else {
				players.remove(item);
				throw new GridItemException(item + " at " + item.getStartingCoordinates() 
						+ " cannot inherit the same coordinates as another GridItem");
			}
		}
		return false;
	}
	
	/**
	 * Removes a player from the player listing and the players GridCoordinates from 
	 * the coordinates listing.
	 * 
	 * @param item - the GridItem to remove from the listing.
	 * @return - true if the GridItem and the GridCoordinates were remove. False if either 
	 * failed to get removed.
	 */
	public boolean removePlayer(GridItem item) {
		if (players.remove(item)) {
			return occupiedCoordinates.remove(item.getStartingCoordinates());
		}
		return false;
	}
	
	public Set<GridItem> getPlayers() {
		return players;
	}
	
	public Set<HasTurn> getPlayersWithTurns() {
		return playersWithTurns;
	}
	
}
