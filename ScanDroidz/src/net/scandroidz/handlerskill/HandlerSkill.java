package net.scandroidz.handlerskill;

import java.io.Serializable;
import java.util.List;

import net.scandroidz.creature.Creature;

public abstract class HandlerSkill implements Serializable {

	private static final long serialVersionUID = 5977925850862185454L;
	
	private String name;
	
	private double castTime;
	
	private double coolDown;
	
	private HandlerSkillTimer timer;
	
	abstract public void perform(List<Creature> friendly, List<Creature> enemy);
	
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

	public HandlerSkillTimer getTimer() {
		return timer;
	}

	public void setTimer(HandlerSkillTimer timer) {
		this.timer = timer;
	}
	


}
