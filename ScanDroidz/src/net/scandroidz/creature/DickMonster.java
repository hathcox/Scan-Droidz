package net.scandroidz.creature;

import net.scandroidz.enums.CreatureEnums.Element;
import net.scandroidz.enums.CreatureEnums.Stat;
import net.scandroidz.skill.Fireball;
import net.scandroidz.skill.Heal;

public class DickMonster extends Creature {

	public DickMonster(String name, int physicalOffense, int physicalDefence, int domainOffense,
			int domainDefence,int endurance, int speed, Element element, Stat primaryStat,
			Stat secondaryStat) {
		
		super(name, physicalOffense, physicalDefence, domainOffense, domainDefence, endurance, speed, element,
				primaryStat, secondaryStat);
		
		// Anything specific to dickmonster creation, LOL
		this.getSkillList().set(0, new Fireball());
		this.getSkillList().set(1, new Heal());
		this.getSkillList().set(2, new Fireball());
		this.getSkillList().set(3, new Heal());

		this.setActiveHealth(endurance);
	}

	//Monster specific code
	
}
