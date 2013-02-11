package net.scandroidz.statseffects;

import net.scandroidz.creature.Stats;

public class Slow extends StatsEffect{

	private static final long serialVersionUID = -525996691161008371L;

	public Slow()
	{
		setName("Slow");
		setMinPercentage(5);
		setMaxPercentage(50);
		setTimer(new StatsEffectTimer(20));
	}
	
	@Override
	public void doEffect(Stats creatureStats) {
		// Use the RNG to determine how large the effect is from
		// minpercentage to max percentage
		int percentageToDoc = getMinPercentage();
		// i know math is wrong but i hate math so fix it
		int newValue = creatureStats.getSpeed() / percentageToDoc;
		creatureStats.setSpeed(newValue);
		setRandPercentage(newValue);
		
	}

	@Override
	public void revertEffect(Stats creatureStats) {
		// TODO Auto-generated method stub
		// Incorrect math just showing how we would set it back
		creatureStats.setSpeed(creatureStats.getSpeed() * getRandPercentage());
		
	}

}