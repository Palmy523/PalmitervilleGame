package com.palmiterville.game.client.controller.display;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.palmiterville.game.client.grid.gui.GridPanel;

public class DisplayControlPanel extends FlowPanel {

	private GridPanel panel;
	private Button displayGridLinesButton;
	private boolean toggleShow = false;
	
	
	public DisplayControlPanel(GridPanel panel) {
		this.setStyleName("control_display_panel");
		this.panel = panel;
		displayGridLinesButton = new Button("Toggle Grid Lines");
		addDisplayGridLinesAction();
		this.add(displayGridLinesButton);
		toggleShow = panel.isGridLinesShowing();

	}
	
	private void addDisplayGridLinesAction() {
		displayGridLinesButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				toggleShow = !toggleShow;
				panel.enableGridLines(toggleShow);
			}
			
		});
	}
}
