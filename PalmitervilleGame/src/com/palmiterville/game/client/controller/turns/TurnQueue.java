package com.palmiterville.game.client.controller.turns;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.HasTurn;

/**
 * Represents a queue that signifies the order of turns for a List of Combatants.
 * 
 * @author Dave
 *
 */
public class TurnQueue {

	/**
	 * The queue
	 */
	private List<HasTurn> queue;
	
	/**
	 * The Comparator used to determine insert and remove positions in the queue.
	 */
	private static final TurnComparator comparator = new TurnComparator();
	
	/*
	 * Constructs a TurnQueue based on a list of Combatants
	 */
	public TurnQueue (Set<HasTurn> players) {
		queue = new ArrayList<>();
		queue.addAll(players);
		organizeTurns();
	}
	
	/**
	 * Re-arranges the GridItems in the queue to determine turn order.
	 */
	private void organizeTurns() {
		Collections.sort(queue, comparator);
	}
	
	/**
	 * Inserts the combatant into the proper place in the TurnQueue based on
	 * turnInterval.
	 * 
	 * @param hasTurn - the combatant to insert into the queue. 
	 * @return - true if the add operation was successful, false if failed or the queue already
	 * contains an instance of the combatant.
	 */
	public boolean add(HasTurn hasTurn) {
		int insertionPoint = Collections.binarySearch(queue, hasTurn, comparator);
		if (insertionPoint < 0) {
			insertionPoint = Math.abs(insertionPoint) - 1;
			if (insertionPoint == queue.size()) {
				queue.add(hasTurn);
			} else {
				queue.add(insertionPoint, hasTurn);
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Permanently removes a Combatant from the TurnQueue, this method does not re-insert
	 * the Combatant at the appropriate position in the Queue.
	 * 
	 * @param combatant - the combatant to be removed from the queue.
	 * @return - 
	 */
	public boolean remove(Combatant combatant) {
		int removeIndex = Collections.binarySearch(queue, combatant, comparator);
		if (removeIndex >= 0) {
			queue.remove(removeIndex);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Removes the Combatant at the head of the queue and places him/her back into
	 * the queue at the appropriate position. The Combatant being popped from the head
	 * signifies their turn, when re-inserted the timeInterval will be reset for the Combatant.
	 * This also signifies that a turn has been made and the time intervals for each Combatant 
	 * will be decreases by 1.
	 * 
	 * @return - the Combatant removed from the head of the queue.
	 */
	public HasTurn pop() {
		HasTurn hasTurn = queue.remove(0);
		decreaseAllTurns();
		hasTurn.resetCurrentTurnInterval();
		add(hasTurn);
		return hasTurn;
	}
	
	/**
	 * Returns but does not remove the head of the queue.
	 * 
	 * @return - the Combatant at the head of the Queue.
	 */
	public HasTurn peek() {
		return queue.get(0);
	}
	
	/**
	 * Performs a turn decrease on all Combatants in the queue.
	 */
	private void decreaseAllTurns() {
		for (HasTurn hasTurn : queue) {
			hasTurn.decreaseTurn();
		}
	}
	
	/**
	 * Performs a turn decrease for a single Combatant in the queue.
	 * @param hasTurn - the combatant to decrease the turn of.
	 * @return - true if the combatant is in the queue and the turn was decrease,
	 * false if otherwise.
	 */
	public boolean decreaseTurn(HasTurn hasTurn) {
		int index = Collections.binarySearch(queue, hasTurn, comparator);
		if (index < 0) {
			return false;
		} else {
			queue.get(index).decreaseTurn();
			return true;
		}
	}
	
	public int size() {
		return queue.size();
	}
	
}
