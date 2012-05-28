package net.scandroidz.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.scandroidz.enums.BattleEnums.MoveSelection;

//This class is just to make sure I don't fucking hardcode anything
public class MainConfig {

	public final static int CREATURE_SKILL_LIST_SIZE = 4; 
	
	/**
	 *  One						Four
	 *  Two						Five
	 *  Three					Six
	 */
	public final static int CREATURE_PER_BATTLE = 6;
	
	//This is the top value for our seed generation algorithm
	public final static long RANDOM_SEED_MAX = 9223372036854775807L;

	//For timers and accuracy
	public final static int NANOSECONDS_IN_SECOND = 1000000000;
	
	public static final Map<MoveSelection, Integer> SKILL_DIRECTION_MAP;
	
	//Initialize the Map
	static {
		Map<MoveSelection, Integer> tempMap = new HashMap<MoveSelection, Integer>();
		tempMap.put(MoveSelection.NORTH, 0);
		tempMap.put(MoveSelection.EAST, 1);
		tempMap.put(MoveSelection.SOUTH, 2);
		tempMap.put(MoveSelection.WEST, 3);
		tempMap.put(MoveSelection.CLICK, 0);
		tempMap.put(MoveSelection.NONE, 0);
		tempMap.put(null, 0);
		SKILL_DIRECTION_MAP = Collections.unmodifiableMap(tempMap);
	}
}