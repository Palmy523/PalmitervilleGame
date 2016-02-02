package com.palmiterville.game.client.grid.item.combatant.component;

public interface Levelable {

	double getExp();
	void setExp(double exp);
	int getLevel();
	void setLevel(int level);
	void levelUp();
	void scaleStats();
	
}
