package com.palmiterville.game.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.palmiterville.game.client.controller.BattleException;
import com.palmiterville.game.client.grid.exception.GridException;
import com.palmiterville.game.client.gui.TemplateLoader;
import com.palmiterville.game.client.logger.ClientLogger;
import com.palmiterville.game.client.web.gui.ContentPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PalmitervilleGame implements EntryPoint {

	private static final boolean isTesting = false;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//Set Log Levels
		if (ClientLogger.IS_TESTING) {
			ClientLogger.setLogLevel(Level.ALL);
		} else {
			ClientLogger.setLogLevel(Level.WARNING);
		}
		
		//Disable Scrolling
		disableScroll();
		
		//Load Template
		ContentPanel panel = TemplateLoader.loadTemplate(RootPanel.get());
		
		if (isTesting) {
			//Insert Code Here to test and bypass Game Setup
		} else {
			//Launch The Test Game
			TestGame game;
			try {
				game = new TestGame();
				game.launchGame(panel);
			} catch (GridException | BattleException e) {
				ClientLogger.logException(Level.SEVERE, e, "There was an error launching the game.");
			}
		}
	}
	
	private void disableScroll() {
		Window.enableScrolling(false);
	}
}
