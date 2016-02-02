package com.palmiterville.game.client.controller.turns;

import java.util.Comparator;

import com.palmiterville.game.client.grid.item.combatant.component.Combatant;
import com.palmiterville.game.client.grid.item.component.HasTurn;

public class TurnComparator implements Comparator<HasTurn> {

	public TurnComparator() {
		
	}
	
	@Override
	public int compare(HasTurn o1, HasTurn o2) {
		return o1.getCurrentTurnInterval() - o2.getCurrentTurnInterval();
	}
}
