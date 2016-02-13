package com.palmiterville.game.client.grid.section.action;

import com.google.gwt.event.shared.GwtEvent;
import com.palmiterville.game.client.grid.gui.GridPanel;
import com.palmiterville.game.client.grid.item.gui.GridItemActionMenu;
import com.palmiterville.game.client.grid.section.gui.Section;

/**
 * This Action mimics the selection of a GridItem on the grid by changing the 
 * selection and updating the Backdrop GridItemInfoPanel.
 * 
 * @author Dave
 *
 */
public class SectionCursorSelectionAction extends SectionAction {

	private GridPanel grid;

	public SectionCursorSelectionAction(GridPanel grid, Section section) {
		super(section);
		this.grid = grid;
	}

	@Override
	public void preProcessAction(GwtEvent e) {
		throw new UnsupportedOperationException("Method not supported.");
	}	

	@Override
	public void processAction(GwtEvent e) {
		//Performs a selection on the Grid Item
		getSection().enableGridLines(true);
		GridItemActionMenu.hideGridItemActionMenu();
		grid.setSelectedSection(getSection());
	}

	@Override
	public void postProcessAction(GwtEvent e) {
		// TODO Auto-generated method stub
		
	}
}
