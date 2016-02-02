package com.palmiterville.game.client.grid.section.action;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.gui.Backdrop;
import com.palmiterville.game.client.grid.gui.BattleGrid;
import com.palmiterville.game.client.grid.item.component.GridItem;
import com.palmiterville.game.client.grid.item.component.HasTurn;
import com.palmiterville.game.client.grid.item.exception.GridItemException;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.item.gui.GridItemLabel;
import com.palmiterville.game.client.grid.item.gui.action.DisplayActionMenuAction;
import com.palmiterville.game.client.grid.section.gui.GridSection;
import com.palmiterville.game.client.grid.section.gui.GridSection.SelectionType;

/**
 * This Action mimics the selection of a GridItem on the grid by changing the 
 * selection and updating the Backdrop GridItemInfoPanel.
 * 
 * @author Dave
 *
 */
public class GridSectionCursorSelectionAction extends GridSectionAction {

	private BattleGrid grid;
	private GridSection section;

	public GridSectionCursorSelectionAction(BattleGrid grid, GridSection section) {
		super(section);
		this.section = getGridSection();
		this.grid = grid;
	}

	@Override
	public void preProcessAction(GwtEvent e) {
		throw new UnsupportedOperationException("Method not supported.");
	}	

	@Override
	public void processAction(GwtEvent e) {
		
		GridItemLabel label = section.getAttachedGridItemLabel();
		if (!grid.isActionInitiating()) {
			//Performs a selection on the Grid Item
			section.select(SelectionType.NONE);
			GridItemActionMenu.hideGridItemActionMenu();
			//Updates the backdrop info panel to display the grid item
			if (label != null) {
				GridItem item = section.getAttachedGridItemLabel().getGridItem();
				try {
					Backdrop.getInstance().getGridItemPopupPanel1().displayGridItemInfo(item);
				} catch (GridItemException e1) {
					// TODO Auto-generated catch block
					GWT.log(e1.getMessage(), e1);
				}
				if (item instanceof HasTurn) {
					HasTurn hasTurn = (HasTurn) item;
					if (hasTurn.isTurn()) {
						new DisplayActionMenuAction(label).processAction(e);
					}
				}
			}
			grid.setSelectedSection(section);
		}
		
		if (grid.isActionInitiating() && section != null) {
				BattleController.getInstance().processInitiatedAction(section);
		}
		
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		throw new UnsupportedOperationException("Method not supported.");
	}

}
