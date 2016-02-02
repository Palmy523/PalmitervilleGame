package com.palmiterville.game.client.grid.item.combatant.component;

import java.util.List;

import com.palmiterville.game.client.grid.item.component.AttackableGridItem;

public interface Attacks {

	/**
	 * Perform an attack on an AttackableGridItem.
	 * 
	 * @param gridItem - the gridItem to inflict damage on.
	 */
	public void attack(AttackableGridItem gridItem);
	
	public int getAttackPower();
	
	public void setAttackPower(int attackPower);
	
	public void setAttackRange(int range);
	
	public int getAttackRange();
}
