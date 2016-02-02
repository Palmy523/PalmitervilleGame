package com.palmiterville.game.client.grid.item.combatant.component;


import java.util.ArrayList;
import java.util.List;

import com.palmiterville.game.client.grid.component.GridCoordinates;
import com.palmiterville.game.client.grid.item.action.GridItemAction;
import com.palmiterville.game.client.grid.item.combatant.action.AttackAction;
import com.palmiterville.game.client.grid.item.combatant.action.InspectAction;
import com.palmiterville.game.client.grid.item.combatant.action.MoveAction;
import com.palmiterville.game.client.grid.item.component.AttackableGridItem;
import com.palmiterville.game.client.grid.item.component.DamageType;
import com.palmiterville.game.client.grid.item.component.HasTurn;

/**
 * A Combatant.
 * 
 * @author Dave
 *
 */
public class Combatant extends AttackableGridItem implements Levelable, 
	Attacks, Moves, PerformsActions, HasTurn {
	
	/**
	 * The combatants default starting health.
	 */
	public static final int START_HEALTH = 80;
	
	/**
	 * The combatants default starting attack power.
	 */
	public static final int START_ATTACK_POWER = 6;
	
	/**
	 * The Default Attack Range of a combatant.
	 */
	public static final int DEFAULT_ATTACK_RANGE = 1;
	
	/**
	 * The Default Move Range of a combatant.
	 */
	public static final int DEFAULT_MOVE_RANGE = 5;
	
	/**
	 * Default Time Interval between turns for a combatant.
	 */
	public static final int DEFAULT_TURN_INTERVAL = 15;
	
	/**
	 * This classes damage type.
	 */
	public int damageType = DamageType.MELEE_TYPE;

	/**
	 * The Default combatant image;
	 */
	public final String DEFAULT_COMBATANT_IMAGE = "img/cube_combatant.png";
	
	/**
	 * The current level of the combatant.
	 */
	private int level = 1;
	
	/**
	 * The combatants attack power.
	 */
	private int attackPower;
	
	/**
	 * Current Experience.
	 */
	private double exp;
	
	/**
	 * The turn interval time for the combatant.
	 */
	private int turnInterval;
	
	/**
	 * The current timeInterval status for the combatant.
	 */
	private int currentTurnInterval;
	
	/**
	 * The current turn state of the Combatant.
	 */
	private boolean isTurn;
	
	private List<GridItemAction> actions;
	
	private int attackRange;
	
	
	/**
	 * Constructs a combatant with the given name.
	 * 
	 * @param name - the name to give the Combatant.
	 */
	public Combatant(int id, String name) {
		this(id, name, 1, START_HEALTH, START_ATTACK_POWER, DEFAULT_TURN_INTERVAL);
	}
	
	/**
	 * Creates a Combatant.
	 * 
	 * @param startingGridSection - the GridSection this combatant should be
	 * hosted by.
	 * @param name - the name of the Combatant.
	 * @param level - the level of the combatant;
	 * @param maxHealth
	 * @param attackPower
	 * @param image
	 */
	public Combatant(int id, String name, int level,
			int maxHealth, int attackPower, int turnInterval) {
		super(id, name, maxHealth);
		setLevel(level);
		setTurnInterval(turnInterval);
		setCurrentTurnInterval(turnInterval);
		setHealth(maxHealth);
		setMaxHealth(maxHealth);
		setAttackPower(attackPower);
		setExp(0);
		actions = new ArrayList<>();
		actions.add((GridItemAction) new AttackAction(this));
		actions.add((GridItemAction) new InspectAction(this));
		actions.add((GridItemAction) new MoveAction(this));
		setAttackRange(DEFAULT_ATTACK_RANGE);
		scaleStats();
	}
	
	@Override
	public void setLevel(int level) {
		
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	public int getAttackPower() {
		return attackPower;
	}
	
	public void levelUp() {
		level++;
	}

	/**
	 * Scales the stats according to class.
	 */
	@Override
	public void scaleStats() {
		
	}

	@Override
	public void attack(AttackableGridItem combatant) {
		combatant.receiveDamage(damageType, attackPower);
	}

	@Override
	public double getExp() {
		return exp;
	}

	@Override
	public void setExp(double exp) {
		this.exp = exp;
		
	}
	
	@Override
	public void setAttackRange(int range) {
		this.attackRange = range;
	}

	@Override
	public int getAttackRange() {
		return attackRange;
	}

	@Override
	public int getMoveDistance() {
		return DEFAULT_MOVE_RANGE;
	}

	@Override
	public List<GridItemAction> getActionsList() {
		return actions;
	}

	@Override
	public void removeAction(GridItemAction action) {
		actions.remove(action);
	}

	@Override
	public GridItemAction removeAction(int index) {
		return actions.remove(index);
	}

	@Override
	public int getTurnInterval() {
		return turnInterval;
	}

	@Override
	public void setTurnInterval(int time) {
		this.turnInterval = time;
	}
	
	@Override
	public void resetCurrentTurnInterval() {
		setCurrentTurnInterval(getTurnInterval());
	}
	
	@Override
	public void setCurrentTurnInterval(int timeInterval) {
		this.currentTurnInterval = timeInterval;
	}
	
	@Override
	public void decreaseTurn() {
		currentTurnInterval--;
	}

	@Override
	public int getCurrentTurnInterval() {
		return currentTurnInterval;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attackPower;
		result = prime * result + currentTurnInterval;
		long temp;
		temp = Double.doubleToLongBits(exp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + level;
		result = prime * result + turnInterval;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Combatant))
			return false;
		Combatant other = (Combatant) obj;
		if (attackPower != other.attackPower)
			return false;
		if (currentTurnInterval != other.currentTurnInterval)
			return false;
		if (Double.doubleToLongBits(exp) != Double.doubleToLongBits(other.exp))
			return false;
		if (level != other.level)
			return false;
		return true;
	}

	@Override
	public boolean isTurn() {
		return isTurn;
	}

	@Override
	public void enableTurn(boolean turn) {
		this.isTurn = turn;
	}

	@Override
	public GridCoordinates getCurrentCoordinates() {
		return super.getCurrentGridCoordinates();
	}

	@Override
	public boolean addAction(GridItemAction action) {
		return actions.add(action);
	}
}
