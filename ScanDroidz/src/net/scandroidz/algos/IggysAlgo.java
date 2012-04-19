package net.scandroidz.algos;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import net.scandroidz.item.Item;
import net.scandroidz.person.Player;

public class IggysAlgo {

	public static Item getItem(long data, Player player, List<Item> itemList) throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		//TODO: Deal with the long ceiling and max data situations
		random.setSeed(player.getSeed() + data);
		long roll = Math.abs(random.nextLong());
		long rarityRoll = roll % 100;
		List<Item> itemListCopy = new ArrayList<Item>(itemList);
		List<Item> removedItems = new ArrayList<Item>();
		
		for(Item item: itemListCopy) {
			if (item.getRarity() > rarityRoll) {
				removedItems.add(item);
			}
		}
		
		itemListCopy.removeAll(removedItems);
		Item selection = itemListCopy.get((int) roll % itemListCopy.size());
		return selection;
	}
	
}
