package net.scandroidz.skill;

import net.scandroidz.enums.SkillEnums.TimerState;
import net.scandroidz.config.MainConfig;

//Wrapper for Timer so we don't have to be spammy about skills
public class SkillTimer {

	private boolean isReady = true;
	
	private TimerState state;
	
	private double lastDifference;
	
	private double castTime;
	
	private double coolDown;
	
	public SkillTimer(double castTime, double coolDown) {
		lastDifference = 0.0;
		this.castTime = castTime;
		this.coolDown = coolDown;
		this.state = TimerState.READY;
	}
	
	public boolean isReady() {
		update();
		return isReady;
	}
	
	public void update() {
		//If we are casting
		if(state == TimerState.CASTING) {
			double currentTime = (System.nanoTime() / MainConfig.NANOSECONDS_IN_SECOND);
			//If enough time has passed
			if((currentTime - lastDifference) >= castTime) {
				state = TimerState.COOLDOWN;
			}
		}
		//If we are on cooldown
		if(state == TimerState.COOLDOWN) {
			double currentTime = (System.nanoTime() / MainConfig.NANOSECONDS_IN_SECOND);
			//If enough time has passed
			if((currentTime - lastDifference) >= coolDown) {
				state = TimerState.READY;
				lastDifference = (System.nanoTime() / MainConfig.NANOSECONDS_IN_SECOND);
				isReady = true;
			}
		}
	}
	
	public void cast() {
		//If we are ready
		if(state == TimerState.READY) {
			state = TimerState.CASTING;
			lastDifference = (System.nanoTime() / MainConfig.NANOSECONDS_IN_SECOND);
			isReady = false;
		}
	}
	
	public String getStatus() {
		String status = "null";
		if(state == TimerState.READY) {
			status = "READY";
		}
		//If we are casting
		if(state == TimerState.CASTING) {
			status = "CASTING";
		}
		//If we are on cooldown
		if(state == TimerState.COOLDOWN) {
			status = "COOLDOWN";
		}
		return status;
	}
	
}
