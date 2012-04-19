package net.scandroidz.creature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.scandroidz.config.MainConfig;
import net.scandroidz.enums.CreatureEnums.Element;
import net.scandroidz.enums.CreatureEnums.Stat;
import net.scandroidz.skill.Skill;
import net.scandroidz.statseffects.StatsEffect;

public abstract class Creature implements Serializable{

	private static final long serialVersionUID = 5001184990469320543L;
	
	private String name;
	
	private int activeHealth;
	private int maxHealth;

	// The base stats is a backup of the creatures stats.
	// This is so at the end of the match we can also revert the creatures stats from effects.
	private Stats activeStats;
	private Stats baseStats;
	
	private Element element;
	
	private List<StatsEffect> statEffects = new ArrayList<StatsEffect>();
	
	private List<Skill> skillList = Arrays.asList(new Skill[MainConfig.CREATURE_SKILL_LIST_SIZE]);

	//For generation
	private Stat primaryStat;
	private Stat secondaryStat;
	
	public Creature(String name, int physicalOffense, int physicalDefence, int domainOffense, int domainDefence,
			int endurance, int speed, Element element, Stat primaryStat,
			Stat secondaryStat) {
		//This ism't nessicary?
		//super();
		
		this.name = name;
		this.maxHealth = endurance;
		this.baseStats = new Stats(physicalOffense,physicalDefence,domainOffense,domainDefence,endurance,speed);
		this.element = element;
		this.primaryStat = primaryStat;
		this.secondaryStat = secondaryStat;
	}
	
	// Call before each match so the base stats are copied to the active stats
	// We will need a more elegant solution
	public void copyStats()
	{
		this.activeStats = this.baseStats;
	}
	
	// Add a status aelment
	public void addStatusEffect(StatsEffect effect)
	{
		if (effect != null) {
			statEffects.add(effect);
		}
	}

	public void doStatEffects()
	{
		for( StatsEffect effect: statEffects)
		{
			effect.doEffect(activeStats);
		}
	}
	
	//For battles
	//private TargetNumber number;
	
	public String toString() {
		return name;
	}
	
	//Getters and Setters (Fuck java)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Stats getBaseStats()
	{
		return this.baseStats;
	}

	public Stats getActiveStats()
	{
		return this.activeStats;
	}
	
	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Stat getPrimaryStat() {
		return primaryStat;
	}

	public void setPrimaryStat(Stat primaryStat) {
		this.primaryStat = primaryStat;
	}

	public Stat getSecondaryStat() {
		return secondaryStat;
	}

	public void setSecondaryStat(Stat secondaryStat) {
		this.secondaryStat = secondaryStat;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}
	
	public int getActiveHealth() {
		return activeHealth;
	}

	public void setActiveHealth(int activeHealth) {
		this.activeHealth = activeHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}
