package net.scandroidz.creature;

public class Stats {
	
	private int physicalOffense;
	private int physicalDefence;
	private int domainOffense;
	private int domainDefence;
	private int endurance;
	private int speed;
	
	public Stats(int physicalOffense, int physicalDefence, 
			int domainOffense, int domainDefence, int endurance, int speed)
	{
		setPhysicalOffense(physicalOffense);
		setPhysicalDefence(physicalDefence);
		setDomainOffense(domainOffense);
		setDomainDefence(domainDefence);
		setEndurance(endurance);
		setSpeed(speed);
	}

	public int getPhysicalOffense() {
		return physicalOffense;
	}

	public void setPhysicalOffense(int physicalOffense) {
		this.physicalOffense = physicalOffense;
	}

	public int getPhysicalDefence() {
		return physicalDefence;
	}

	public void setPhysicalDefence(int physicalDefence) {
		this.physicalDefence = physicalDefence;
	}

	public int getDomainOffense() {
		return domainOffense;
	}

	public void setDomainOffense(int domainOffense) {
		this.domainOffense = domainOffense;
	}

	public int getDomainDefence() {
		return domainDefence;
	}

	public void setDomainDefence(int domainDefence) {
		this.domainDefence = domainDefence;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	

}
