package com.palmiterville.game.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.palmiterville.game.client.camera.exception.CameraException;
import com.palmiterville.game.client.camera.gui.CameraControllerPanel;
import com.palmiterville.game.client.grid.exception.GridBuilderException;
import com.palmiterville.game.client.grid.exception.GridCreationException;
import com.palmiterville.game.client.grid.gui.GridPanel;
import com.palmiterville.game.client.grid.section.gui.MoveableSection;
import com.palmiterville.game.client.grid.section.gui.UnmoveableSection;
import com.palmiterville.game.client.gui.GameControlPanel;
import com.palmiterville.game.client.logger.ClientLogger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PalmitervilleGame implements EntryPoint {

	private static final boolean isTesting = false;
	private static GameControlPanel gamePanel;
	private static GridPanel gridPanel;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.getBodyElement().setId("rootPanel");
		RootPanel.get().setStyleName("rootPanel");

		gamePanel = new GameControlPanel();
		try {
			gridPanel = new GridPanel(40, 40);
			gridPanel.createTemplate();
			for (int i = 0; i < gridPanel.getGrid().getWidth(); i++) {
				for (int j = 0; j < gridPanel.getGrid().getHeight(); j++) {
						gridPanel.addSection(new UnmoveableSection(j, i));
				}
			}
			gamePanel.add(gridPanel);
			RootPanel.get().add(gamePanel);
		} catch (GridCreationException | GridBuilderException e) {
			ClientLogger.logException(Level.WARNING, e, e.getMessage());
		}
		
		addCameraController();
		
		disableScroll();
	}
	
	/**
	 * Adds a camera controller to the gridPanel.
	 */
	private void addCameraController() {
		GWT.log("GridPanel Position: " + gridPanel.getElement().getStyle().getPosition());
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				try {
					CameraControllerPanel cameraController = new CameraControllerPanel(gridPanel);
					gamePanel.setCameraControls(cameraController);
				} catch (CameraException e) {
					ClientLogger.logException(Level.WARNING, e, e.getMessage());
				}
			}
		});
	}
	
	private void disableScroll() {
		Window.enableScrolling(false);
	}
	
	public static GridPanel getGridPanel() {
		return gridPanel;
	}
}
