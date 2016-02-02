package com.palmiterville.game.client.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

public class ClientLogger {

	public static final boolean IS_TESTING = true;
	public static final Logger exceptionLogger = Logger.getLogger("Exception");
	public static final Logger errorLogger = Logger.getLogger("Error");
	
	private ClientLogger() {
		throw new UnsupportedOperationException("Class can't be instantiated.");
	}
	
	public static void logException(Level level, Throwable e, String message) {
		if (IS_TESTING) {
			GWT.log(message, e);
			Window.alert(message);
		}
		exceptionLogger.log(level, message, e);
	}
	
	public static void setLogLevel(Level level) {
		exceptionLogger.setLevel(level);
		errorLogger.setLevel(level);
	}
	
}
