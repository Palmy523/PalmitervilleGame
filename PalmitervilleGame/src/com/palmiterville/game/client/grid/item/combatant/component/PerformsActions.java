package com.palmiterville.game.client.grid.item.combatant.component;

import java.util.List;

import com.palmiterville.game.client.grid.action.GridAction;
import com.palmiterville.game.client.grid.item.action.GridItemAction;

public interface PerformsActions {

	public List<GridItemAction> getActionsList();	
	public boolean addAction(GridItemAction action);
	public void removeAction(GridItemAction action);
	public GridItemAction removeAction(int index);
	
}
