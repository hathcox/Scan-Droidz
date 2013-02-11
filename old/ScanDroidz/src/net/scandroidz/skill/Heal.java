package net.scandroidz.skill;

import net.scandroidz.creature.Creature;
import net.scandroidz.skill.SkillTimer;

public class Heal extends Skill {
	
	private static final long serialVersionUID = -6921514711378775017L;

	public Heal() {
		setName("Heal");
		setCastTime(2.5);
		setCoolDown(4);
		setTimer(new SkillTimer(getCastTime(), getCoolDown()));
	}
	
	@Override
	public void perform(Creature selection, Creature target) {
		//This shouldn't be nessicary, but just in case
		if(this.isReady()) {
			//Pull down the RNG Engine
			//Ship the creatures to the RNG Engine and determine (damage / etc)
			//Update creatures with approreit changes
			target.setActiveHealth(target.getActiveHealth() + 10);
			
			getTimer().cast();
		}
	}

}
