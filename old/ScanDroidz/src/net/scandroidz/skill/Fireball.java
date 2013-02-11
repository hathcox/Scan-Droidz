package net.scandroidz.skill;

import net.scandroidz.creature.Creature;

public class Fireball extends Skill {
	
	private static final long serialVersionUID = -7489532275107457063L;

	public Fireball() {
		setName("FireBall");
		setCastTime(1.00);
		setCoolDown(2.5);
		setTimer(new SkillTimer(getCastTime(), getCoolDown()));
	}
	
	@Override
	public void perform(Creature selection, Creature target) {
		//Pull down the RNG Engine
		//Ship the creatures to the RNG Engine and determine (damage / etc)
		//Update creatures with approreit changes
		target.setActiveHealth(target.getActiveHealth() - 10);
		
		getTimer().cast();
	}

}
