package com.palmiterville.game.client.grid.item.combatant.gui;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.palmiterville.game.client.grid.item.component.HasHealth;
import com.palmiterville.game.client.grid.item.exception.GridItemException;

public class HealthBar extends HorizontalPanel {

	public static final String HEALTH_BAR_HEIGHT = "30px";
	public static final int HEALTH_BAR_WIDTH = 200;
	
	private int currentHealth;
	private int maxHealth;
	private Label currentHealthLabel = new Label();
	private Label healthRemovedLabel = new Label();
	private Label textualHealthLabel = new Label();
	
	public HealthBar(HasHealth hasHealth) throws GridItemException {
		this.currentHealth = hasHealth.getHealth();
		this.maxHealth = hasHealth.getMaxHealth();
		this.setStyleName("grid_item_healthbar");
		currentHealthLabel.setStyleName("grid_item_healthbar_current");
		healthRemovedLabel.setStyleName("grid_item_healthbar_removed");
		textualHealthLabel.setStyleName("grid_item_healthbar_text");
		this.add(textualHealthLabel);
		this.add(currentHealthLabel);
		this.add(healthRemovedLabel);
		
		//this.add(textualHealthLabel);
//		this.setCellVerticalAlignment(textualHealthLabel, HasVerticalAlignment.ALIGN_MIDDLE);
//		this.setCellHorizontalAlignment(textualHealthLabel, HasHorizontalAlignment.ALIGN_CENTER);
		updateHealth();
	}
	
	private void updateHealth() throws GridItemException {
		int percentage = 100 *currentHealth/maxHealth;
		int width = percentage * HEALTH_BAR_WIDTH / 100;
		currentHealthLabel.setWidth(width + "px");
		width = (HEALTH_BAR_WIDTH - width);
		healthRemovedLabel.setWidth(width + "px");
		textualHealthLabel.setWidth("100%");
		textualHealthLabel.setText(currentHealth +" / " + maxHealth);
	}
	
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
}
