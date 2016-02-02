package com.palmiterville.game.client.grid.item.component;

import com.palmiterville.game.client.grid.component.GridCoordinates;

/**
 * Interface for handling turn based combat.
 * 
 * @author Dave
 *
 */
public interface HasTurn {

	/**
	 * 
	 * @return - whether or not it's the objects turn
	 */
	boolean isTurn();
	
	/**
	 * 
	 * @param - sets whether it's the objects turn
	 */
	void enableTurn(boolean turn);
	
	/**
	 * Getter for the turnIntervalValue. This value is value the turn will
	 * be reset to after the Object completes a turn. This does not represent 
	 * the amount of turns remaining until it is the objects turn, use 
	 * getCurrentTurnInterval() instead.
	 * @return - the turnInterval.
	 */
	int getTurnInterval();
	
	/**
	 * Sets the turnInterval.
	 * 
	 * @param time - the number of turns the object has to wait before 
	 * it is their turn, this is the value that is used to reset the turnInterval
	 * after a turn is made and does not represent the amount of turns until it is 
	 * the object's turn. Use setCurrentTurnInterval() instead.
	 */
	void setTurnInterval(int time);
	
	/**
	 * Getter for the currentStatus of the turn. This is the number of remaining turns
	 * the object has to wait before performing a turn.
	 * @return - an int value that represents the number of turns remaining until it is 
	 * the objects turn.
	 */
	int getCurrentTurnInterval();
	
	/**
	 * Sets the currentTurnInterval.
	 * 
	 * @param time - the number of turns the combatant has to wait before it is their turn.
	 */
	void setCurrentTurnInterval(int time);
	
	/**
	 * method to decrease the turn amount by after each turn is made by another object.
	 */
	void decreaseTurn();
	
	/**
	 * resets the currentTurnInterval to the turnInterval set.
	 */
	void resetCurrentTurnInterval();
	
	/**
	 * the current coordinates of the object that implements this interface.
	 */
	GridCoordinates getCurrentCoordinates();
	
}
