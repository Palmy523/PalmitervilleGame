package com.palmiterville.game.client.grid.item.component;

public class AttackableGridItem extends GridItem implements HasHealth {

	private int health;
	private int maxHealth;
	
	public AttackableGridItem(int id, String name, int maxHealth) {
		super(id, name);
		this.health = maxHealth;
		this.maxHealth = maxHealth;
	}
	
	public void receiveDamage(int damageType, int damageAmount) {
		int healthToSet = health - damageAmount;
		if (healthToSet < 0) {
			healthToSet = 0;
		}
		this.health = healthToSet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + health;
		result = prime * result + maxHealth;
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
		if (!(obj instanceof AttackableGridItem))
			return false;
		AttackableGridItem other = (AttackableGridItem) obj;
		if (health != other.health)
			return false;
		if (maxHealth != other.maxHealth)
			return false;
		return true;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void setMaxHealth(int health) {
		this.maxHealth = health;
	}
	
	
	
	

	
}
