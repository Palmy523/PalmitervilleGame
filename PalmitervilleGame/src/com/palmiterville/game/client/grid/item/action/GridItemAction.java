package com.palmiterville.game.client.grid.item.action;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.section.gui.GridSectionTemp;

public interface GridItemAction {
	
	/**
	 * Enumerator that represents an Action Type.
	 * 
	 * @author Dave
	 *
	 */
	public static enum ActionType {FRIENDLY, ENEMY, BOTH, SINGLE, MULTIPLE, RANGE};
	public static enum RangeType {SINGLE, MULTIPLE, RANGE};
	
	/**
	 * Pre-process the Action, this includes an initiating or GUI setup needed before the
	 * action is processed.
	 * 
	 * @param e
	 */
	public void preProcessAction(GwtEvent e);
	
	/**
	 * Process the Action.
	 * @param e
	 * @return
	 */
	public boolean processAction(GwtEvent e);
	
	/**
	 * Post action events to occur after the Action is processed.
	 * @param e
	 */
	public void postProcessAction(GwtEvent e);
	
	/**
	 * @return - the action name.
	 */
	public String getActionName();
	
	/**
	 * Return the ActionStyleName for the action to be performed, this should update
	 * the grid sections to play some sort of css animation for the action.
	 * 
	 * @return - the actionStyleName
	 */
	public String getActionStyleName();
	
	/**
	 * Returns the duration of the action to be performed, this duration should sync with
	 * the amount of time it takes for the animation to play. 
	 * 
	 * @return - the int amount of time in milliseconds for the action to be performed
	 * once processed.
	 */
	public int getActionDuration();
	
	/**
	 * Returns the string stylename to set the highlighted GridSection when the action
	 * is initiated (preProcessed).
	 * 
	 * @return - the string styleName of the highlight GridSection.
	 */
	public String getGridSectionHighlightStyleName();
	
	/**
	 * Returns a list of all allowable distances the action can be performed. 
	 * 
	 * @return - a List of integers containing the allowable action ranges. 
	 */
	public int getActionRange();
	
	/**
	 * Tells whether the action can be performed on the initiator or not.
	 * @return
	 */
	public boolean isSelfAction();
	
	/**
	 * @return - the GridItem that initiated the action.
	 */
	public GridItem getSource();
	
	/**
	 * @return - the ActionType that allows the GridItems with this type to be performed on.
	 */
	public ActionType getActionType();
	
	/**
	 * 
	 * @return - the GridSections in the Battle Grid set to be the recipient of the action.
	 */
	public GridSectionTemp getRecipient();
	
	/**
	 * Sets the recipient of the action.
	 * @param recipient
	 */
	public void setRecipient(GridSectionTemp recipient);
	
	/**
	 * Returns whether or not the action is allowed on a GridItem.
	 * @param item
	 */
	public boolean allowsActionOn(GridSectionTemp section);
	
	/**
	 * flag that sets whether performing the action ends the turn.
	 */
	public boolean endsTurn();
	
	/**
	 * 
	 * @return - whether or not this action should be disabled.
	 */
	public boolean isDisabled();
}
