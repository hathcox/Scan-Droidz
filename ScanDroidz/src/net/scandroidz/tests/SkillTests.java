package net.scandroidz.tests;

import net.scandroidz.creature.DickMonster;
import net.scandroidz.enums.CreatureEnums.Element;
import net.scandroidz.enums.CreatureEnums.Stat;
import net.scandroidz.skill.Fireball;

public class SkillTests {
	
	public static void main(String[] args) throws InterruptedException {
		Fireball firstFireball = new Fireball();
		DickMonster dick = new DickMonster("DickBalls Dragon", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO);
		DickMonster dick2 =new DickMonster("Cat", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO);
		
		while(true) {
			//Thread.sleep(8000);
			if(firstFireball.isReady()) {
				System.out.println("Cast!");
				firstFireball.perform(dick, dick2);
			}
			//System.out.println("Tried");
		}
	}
}
