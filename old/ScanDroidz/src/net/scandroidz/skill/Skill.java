package net.scandroidz.skill;

import java.io.Serializable;

import net.scandroidz.creature.Creature;

public abstract class Skill implements Serializable {

	private static final long serialVersionUID = 5977925850862185454L;
	
	private String name;
	
	private double castTime;
	
	private double coolDown;
	
	private SkillTimer timer;
	
	abstract public void perform(Creature selection, Creature target);
	
	public double getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(double coolDown) {
		this.coolDown = coolDown;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCastTime(double castTime) {
		this.castTime = castTime;
	}

	public boolean isReady() {
		return timer.isReady();
	}

	public String getName() {
		return name;
	}

	public double getCastTime() {
		return castTime;
	}

	public SkillTimer getTimer() {
		return timer;
	}

	public void setTimer(SkillTimer timer) {
		this.timer = timer;
	}
	


}
