package com.palmiterville.game.client.controller;

public class BattleException extends Throwable {

	public BattleException() {
		this("");
	}
	
	public BattleException(String message) {
		super("A BattleException Occured. " + message);
	}
	
	
	
}
