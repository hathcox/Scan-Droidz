package net.scandroidz.statseffects;

import java.io.Serializable;

import net.scandroidz.creature.Stats;

public abstract class StatsEffect implements Serializable {
	
	private static final long serialVersionUID = 5977925850864185454L;
	
	private String name;
	private StatsEffectTimer timer;
	
	private int minPercentage;
	private int maxPercentage;
	// Percentage that was generated randomly
	private int randPercentage;
	
	// boolean that holds if the duration is over
	private boolean durationOver;
	
	abstract public void doEffect(Stats creatureStats);
	abstract public void revertEffect(Stats creatureStats);
	
	// If true it means duration is over
	public boolean isDurationOver()
	{
		return durationOver;
	}
	
	public void setDurationOver(boolean duration)
	{
		this.durationOver = duration;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinPercentage() {
		return minPercentage;
	}

	public void setMinPercentage(int minPercentage) {
		this.minPercentage = minPercentage;
	}

	public int getMaxPercentage() {
		return maxPercentage;
	}

	public void setMaxPercentage(int maxPercentage) {
		this.maxPercentage = maxPercentage;
	}
	public int getRandPercentage() {
		return randPercentage;
	}
	public void setRandPercentage(int randPercentage) {
		this.randPercentage = randPercentage;
	}
	
	public void setTimer(StatsEffectTimer timer) {
		this.timer = timer;
	}
	
}
