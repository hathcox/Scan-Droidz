package net.scandroidz.skill;

import net.scandroidz.creature.Creature;

public class Defend extends Skill {

	public Defend () {
		this.setName("Defend");
		this.setCastTime(0.5);
		this.setCoolDown(15);
		this.setTimer(new SkillTimer(getCastTime(), getCoolDown()));
	}
	
	@Override
	public void perform(Creature selection, Creature target) {
		//This should be nessicary, but just in case
		if(this.isReady()) {
			//Pull down the RNG Engine
			//Ship the creatures to the RNG Engine and determine (damage / etc)
			//Update creatures with approreit changes
			selection.setActiveHealth(selection.getActiveHealth() + 5);
			
			getTimer().cast();
		}
	}

}
