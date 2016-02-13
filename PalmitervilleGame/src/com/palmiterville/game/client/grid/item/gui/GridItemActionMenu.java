package com.palmiterville.game.client.grid.item.gui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.palmiterville.game.client.controller.BattleController;
import com.palmiterville.game.client.grid.item.action.GridItemAction;
import com.palmiterville.game.client.grid.item.combatant.component.PerformsActions;
import com.palmiterville.game.client.grid.item.component.GridItem;

public class GridItemActionMenu extends VerticalPanel {
	
	private List<GridItemAction> actions;
	private static GridItemPopupPanel instance;
	private static GridItemLabel currentlyDisplayedLabel;
	
	public GridItemActionMenu(List<GridItemAction> actions) {
		super();
		this.actions = actions;
		initComponents();
		this.setWidth("100%");
	}
	
	public void initComponents() {
		for (final GridItemAction action : actions) {
			Label actionLabel = new Label(action.getActionName());
			
			if (action.isDisabled()) {
				actionLabel.setStyleName("grid_item_action_label_disabled");
			} else {
				actionLabel.setStyleName("grid_item_action_label");
				actionLabel.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						action.preProcessAction(event);
					}
					
				});
			}
			
			this.add(actionLabel);
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					if (instance != null && !BattleController.getInstance().isActionInitiating()) {
						if (instance.getPopupPosition() == null) {
							GridItemActionMenu.displayGridItemActionMenu(currentlyDisplayedLabel);
						}
					}
				}
				
			});
		}
	}
	
	/**
	 * Displays a GridItemActionMenu in this PopupPanel relative to the GridItemLabel.
	 * 
	 * @param menu
	 * @param label
	 */
	public static void displayGridItemActionMenu(GridItemLabel label) {
		hideGridItemActionMenu();
		instance = getInstance();
		if (label == null) {
			return;
		}
		currentlyDisplayedLabel = label;
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
		GridItemActionMenu.instance = instance;
	}
}
