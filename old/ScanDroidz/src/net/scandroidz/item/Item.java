package net.scandroidz.item;

import java.io.Serializable;

public abstract class Item implements Serializable {

	private static final long serialVersionUID = -7914476252783096925L;
	
	String name;
	//This should be between 
	//  1 <-------------------->99
	// Less Rare				More Rare
	private int rarity;
	
	//Shit for items, we haven't really discussed what these are or do yet

	public int getRarity() {
		return rarity;
	}
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
}
