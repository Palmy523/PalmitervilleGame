package com.palmiterville.game.client.grid.item.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.grid.action.GridAction;
import com.palmiterville.game.client.grid.item.action.GridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.PerformsActions;
import com.palmiterville.game.client.grid.item.component.GridItem;

public class GridItemActionMenu extends VerticalPanel {
	
	private static int DEFAULT_MENU_WIDTH = 200;
	private List<GridItemAction> actions;
	private static GridItemPopupPanel instance;
	
	public GridItemActionMenu(List<GridItemAction> actions) {
		super();
		this.actions = actions;
		initComponents();
		this.setWidth("100%");
	}
	
	public void initComponents() {
		for (final GridItemAction action : actions) {
			Label actionLabel = new Label(action.getActionName());
			actionLabel.setStyleName("grid_item_action_label");
			actionLabel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					action.preProcessAction(event);
				}
				
			});
			this.add(actionLabel);
		}
	}
	
	/**
	 * Displays a GridItemActionMenu in this PopupPanel relative to the GridItemLabel.
	 * 
	 * @param menu
	 * @param label
	 */
	public static void displayGridItemActionMenu(GridItemLabel label) {
		instance = getInstance();
		if (label == null) {
			return;
		}
		GridItem item = label.getGridItem();
		if (item instanceof PerformsActions) {
			PerformsActions performsAction = (PerformsActions) item;
			instance.contentPanel.clear();
			instance.contentPanel.add(new GridItemActionMenu(performsAction.getActionsList()));
			instance.setPopupPosition(label.getAbsoluteLeft() + label.getOffsetWidth(),
					label.getAbsoluteTop());
			instance.show();
			return;
		}
	}
	
	public static void hideGridItemActionMenu() {
		if (instance != null) {
			instance.hide();
		}
	}
	
	public static GridItemPopupPanel getInstance() {
		return (instance != null) ? instance : (instance = new GridItemPopupPanel());
	}
	
	public void setInstance(GridItemPopupPanel instance) {
		this.instance = instance;
	}
}
