package net.scandroidz.handlerskill;

import java.util.List;

import net.scandroidz.creature.Creature;
import net.scandroidz.handlerskill.HandlerSkillTimer;

public class HealAll extends HandlerSkill {
	
	private static final long serialVersionUID = -6921514711378775017L;

	public HealAll() {
		setName("HealAll");
		setCastTime(2.5);
		setCoolDown(4);
		setTimer(new HandlerSkillTimer(getCastTime(), getCoolDown()));
	}
	
	@Override
	public void perform(List<Creature> friendly, List<Creature> enemy) {
		//This shouldn't be nessicary, but just in case
		if(this.isReady()) {
			//Pull down the RNG Engine
			//Ship the creatures to the RNG Engine and determine (damage / etc)
			//Update creatures with approreit changes
			for(Creature creature: friendly)
			{
				creature.setActiveHealth(creature.getActiveHealth() + 10);
			}		
			
			getTimer().cast();
		}
	}

}
