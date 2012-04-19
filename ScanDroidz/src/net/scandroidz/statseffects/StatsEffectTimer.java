package net.scandroidz.statseffects;

import net.scandroidz.config.MainConfig;

public class StatsEffectTimer {
	private boolean isDone = false;
	
	private double lastDifference;
	private double duration;
	private StatsEffect effect;
	public StatsEffectTimer(double duration) {
		this.duration = duration;
	}
	
	public void setStatusEffect(StatsEffect effect)
	{
		this.effect = effect;
	}
	
	public boolean isReady() {
		update();
		return isDone;
	}
	
	public void update() {	

		double currentTime = (System.nanoTime() / MainConfig.NANOSECONDS_IN_SECOND);
		//If enough time has passed
		if((currentTime - lastDifference) >= duration) {
			lastDifference = (System.nanoTime() / MainConfig.NANOSECONDS_IN_SECOND);
			isDone = true;
			effect.setDurationOver(isDone);
		}
	}
}
