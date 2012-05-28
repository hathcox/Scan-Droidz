package net.scandroidz.person;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.scandroidz.creature.Creature;
import net.scandroidz.handlerskill.HandlerSkill;
import net.scandroidz.item.Item;
import net.scandroidz.skill.Skill;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9085772672169810265L;

	/**
	 * According to wikipedia:
	 * 	In other words, only after generating 1 billion UUIDs 
	 * 	every second for the next 100 years, the probability 
	 * 	of creating just one duplicate would be about 50%.
	 */
	private UUID id;

	private String name;
	
	//Creatures on hand
	private Creature firstCreature;
	private Creature secondCreature;
	private Creature thirdCreature;
	
	//Items on hand
	private List<Item> inventory;
	
	//Skills you know?
	private List<Skill> skillBook;
	
	//Creatures stored
	private List<Creature> stash;
	
	//Creatures ever seen 
	private List<Creature> creatureDex;
	
	//Handler Skills
	private List<HandlerSkill> handlerSkills;
	
	private Long seed;
	
	public Player(String name) throws NoSuchAlgorithmException {
		super();
		this.name = name;
		id = UUID.randomUUID();
		
		firstCreature = null;
		secondCreature = null;
		thirdCreature = null;
		
		inventory = new ArrayList<Item>();
		skillBook = new ArrayList<Skill>();
		stash = new ArrayList<Creature>();
		creatureDex = new ArrayList<Creature>();
		handlerSkills = new ArrayList<HandlerSkill>();
		
		//TODO: Confirm the security of this? They might be able to force seeds
		this.seed = getRandomSeed();
		
	}
	
	private Long getRandomSeed() throws NoSuchAlgorithmException {
		//TODO: Back this up on the server
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		return random.nextLong();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Creature getFirstCreature() {
		return firstCreature;
	}

	public void setFirstCreature(Creature firstCreature) {
		this.firstCreature = firstCreature;
	}

	public Creature getSecondCreature() {
		return secondCreature;
	}

	public void setSecondCreature(Creature secondCreature) {
		this.secondCreature = secondCreature;
	}

	public Creature getThirdCreature() {
		return thirdCreature;
	}

	public void setThirdCreature(Creature thirdCreature) {
		this.thirdCreature = thirdCreature;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public List<Skill> getSkillBook() {
		return skillBook;
	}

	public void setSkillBook(List<Skill> skillBook) {
		this.skillBook = skillBook;
	}

	public List<Creature> getStash() {
		return stash;
	}

	public void setStash(List<Creature> stash) {
		this.stash = stash;
	}

	public List<Creature> getCreatureDex() {
		return creatureDex;
	}

	public void setCreatureDex(List<Creature> creatureDex) {
		this.creatureDex = creatureDex;
	}

	public Long getSeed() {
		return seed;
	}

	//TODO: This should be turned off if we don't ever need to reset the seed during the game!!
	public void setSeed(Long seed) {
		this.seed = seed;
	}

	public UUID getId() {
		return id;
	}
	public List<HandlerSkill> getHandlerSkills() {
		return handlerSkills;
	}
	public void setHandlerSkills(List<HandlerSkill> handlerSkills) {
		this.handlerSkills = handlerSkills;
	}
}
