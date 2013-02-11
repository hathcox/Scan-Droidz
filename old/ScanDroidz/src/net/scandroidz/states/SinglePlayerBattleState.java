package net.scandroidz.states;

import java.util.Arrays;
import java.util.List;

import net.scandroidz.config.MainConfig;
import net.scandroidz.core.State;
import net.scandroidz.creature.Creature;
import net.scandroidz.enums.BattleEnums.BattleState;
import net.scandroidz.enums.BattleEnums.MoveSelection;
import net.scandroidz.person.Player;
import net.scandroidz.skill.Defend;
import net.scandroidz.skill.Skill;
import net.scandroidz.widgets.flickstick.FlickReleaseEvent;
import net.scandroidz.widgets.flickstick.FlickReleaseEventListener;
import net.scandroidz.widgets.flickstick.FlickStick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SinglePlayerBattleState implements State, FlickReleaseEventListener {
	
	//Rendering 
	private SpriteBatch spriteBatch;
	
	//Left Side
	private Rectangle targetOne;
	private Rectangle targetTwo;
	private Rectangle targetThree;
	
	//Right Side
	private Rectangle targetFour;
	private Rectangle targetFive;
	private Rectangle targetSix;
	
	//Flick Stick
	private FlickStick flickStick;
	
	//Handler Buttons
	private Rectangle handlerButtonOne;
	private Rectangle handlerButtonTwo;
	private Rectangle handlerButtonThree;
	private Rectangle handlerButtonFour;
	
	//Current State Variables
	BattleState battleState = null;
	int firstTarget = -1;
	MoveSelection move = null;
	int secondTarget = -1;
	
	//Players
	Player playerOne;
	
	//Creature List and Skill Lists
	List<Creature> currentCreatures = Arrays.asList(new Creature[MainConfig.CREATURE_PER_BATTLE]);

	//Textures
	//TODO: Dynamicly load the creatures
	Texture one = new Texture(Gdx.files.internal("data/old/one.png"));
	Texture two = new Texture(Gdx.files.internal("data/old/two.png"));
	Texture three = new Texture(Gdx.files.internal("data/old/three.png"));
	Texture four = new Texture(Gdx.files.internal("data/old/four.png"));
	Texture five = new Texture(Gdx.files.internal("data/old/five.png"));
	Texture six = new Texture(Gdx.files.internal("data/old/six.png"));
	
	BitmapFont font = new BitmapFont(Gdx.files.internal("data/font16.fnt"),
	         Gdx.files.internal("data/font16.png"), false);

	Texture handlerOne = new Texture(Gdx.files.internal("data/old/handlerOne.png"));
	Texture handlerTwo = new Texture(Gdx.files.internal("data/old/handlerTwo.png"));
	Texture handlerThree = new Texture(Gdx.files.internal("data/old/handlerThree.png"));
	Texture handlerFour = new Texture(Gdx.files.internal("data/old/handlerFour.png"));


	//YOU BETTER FUCKING CALL SETUP FAGGOT
	public SinglePlayerBattleState() {
		startUp();
	}
	
	//This should be called directly after the constructor
	public void setup(Creature targetOne, Creature targetTwo, Creature targetThree, Creature targetFour, Creature targetFive, Creature targetSix, Player playerOne) {
		//Link creatures to their location
		currentCreatures.set(0, targetOne);
		currentCreatures.set(1, targetTwo);
		currentCreatures.set(2, targetThree);
		currentCreatures.set(3, targetFour);
		currentCreatures.set(4, targetFive);
		currentCreatures.set(5, targetSix);
		
		this.playerOne = playerOne;
		//More startup code, possible to moves and lazy fetching
	}
	
	@Override
	public void startUp() {
		
		spriteBatch = new SpriteBatch();
		flickStick = new FlickStick(new Vector2((int)(Gdx.graphics.getWidth()/2 + (Gdx.graphics.getWidth()/4 * 1.2 )), 15), 64,  (float)2);
		//flickStick.addClickListener(this);
		flickStick.addReleaseListener(this);
		
		//Setup the Target boxes
		targetOne = new Rectangle(0, Gdx.graphics.getHeight() -  Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		targetTwo = new Rectangle(targetOne.x, targetOne.y -  Gdx.graphics.getHeight()/4, targetOne.width, targetOne.height);
		targetThree = new Rectangle(targetTwo.x, targetTwo.y -  Gdx.graphics.getHeight()/4, targetOne.width, targetOne.height);
		
		targetFour = new Rectangle(Gdx.graphics.getWidth()/2 + Gdx.graphics.getWidth()/4, targetOne.y, targetOne.width, targetOne.height);
		targetFive = new Rectangle(targetFour.x, targetFour.y - Gdx.graphics.getHeight()/4, targetOne.width, targetOne.height);
		targetSix = new Rectangle(targetFour.x, targetFive.y - Gdx.graphics.getHeight()/4, targetOne.width, targetOne.height);
		
		//Setup handler abilities 
		handlerButtonOne = new Rectangle(Gdx.graphics.getWidth()/4, 0, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/4);
		handlerButtonTwo = new Rectangle(handlerButtonOne.x + Gdx.graphics.getWidth()/8, 0, handlerButtonOne.width, handlerButtonOne.height);
		handlerButtonThree = new Rectangle(handlerButtonTwo.x + Gdx.graphics.getWidth()/8, 0, handlerButtonOne.width, handlerButtonOne.height);
		handlerButtonFour = new Rectangle(handlerButtonThree.x + Gdx.graphics.getWidth()/8, 0, handlerButtonOne.width, handlerButtonOne.height);

		
		//Set to SELECTION since were starting
		battleState = BattleState.SELECTION;
		
	}

	@Override
	public void render() {
		//Clear Screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		//render flickStick
		flickStick.render();
		
		spriteBatch.begin();
		//render the creatures
		spriteBatch.draw(one, targetOne.x, targetOne.y, targetOne.width, targetOne.height);
		spriteBatch.draw(two, targetTwo.x, targetTwo.y, targetTwo.width, targetTwo.height);
		spriteBatch.draw(three, targetThree.x, targetThree.y, targetThree.width, targetThree.height);
		spriteBatch.draw(four, targetFour.x, targetFour.y, targetFour.width, targetFour.height);
		spriteBatch.draw(five, targetFive.x, targetFive.y, targetFive.width, targetFive.height);
		spriteBatch.draw(six, targetSix.x, targetSix.y, targetSix.width, targetSix.height);
		
		//font to black
		font.setColor(0, 0, 0, 1);
		
		//Creature Healths
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(0).getActiveHealth()), targetOne.x + 140, targetOne.y + 100);
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(1).getActiveHealth()), targetTwo.x + 140, targetTwo.y + 100);
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(2).getActiveHealth()), targetThree.x + 140, targetThree.y + 100);
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(3).getActiveHealth()), targetFour.x + 140, targetFour.y + 100);
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(4).getActiveHealth()), targetFive.x + 140, targetFive.y + 100);
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(5).getActiveHealth()), targetSix.x + 140, targetSix.y + 100);


		//render the handler buttons
		spriteBatch.draw(handlerOne, handlerButtonOne.x, handlerButtonOne.y, handlerButtonOne.width, handlerButtonOne.height);
		spriteBatch.draw(handlerTwo, handlerButtonTwo.x, handlerButtonTwo.y, handlerButtonTwo.width, handlerButtonTwo.height);
		spriteBatch.draw(handlerThree, handlerButtonThree.x, handlerButtonThree.y, handlerButtonThree.width, handlerButtonThree.height);
		spriteBatch.draw(handlerFour, handlerButtonFour.x, handlerButtonFour.y, handlerButtonFour.width, handlerButtonFour.height);

		spriteBatch.end();
	}

	@Override
	public void update(float delta) {		
		
		Vector2 currentTouch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		flickStick.update(delta);
		
		//Deal with the first selection state
		if(battleState == BattleState.SELECTION) {
			if(Gdx.input.justTouched()) {
				//Update select button
				checkHandlerButtons(currentTouch);
				//Discover the choice
				int currentTarget = findSelection(currentTouch);
				//If we have an actual target
				if(currentTarget != -1) {
					firstTarget = currentTarget;
					//Reset move
					move = null;
					battleState = BattleState.MOVE;
				}
				
			}
		}
		//Deal with Move State
		if(battleState == BattleState.MOVE) {
			if(Gdx.input.justTouched()) {
				//Wait for an event or Selection button
				checkHandlerButtons(currentTouch);
				//Discover changes of choice
				int currentTarget = findSelection(currentTouch);
				//We got an event
				//If they switched thier primary choice
				if(currentTarget != -1) {
					firstTarget = currentTarget;
				}
				if(move != null) {
					battleState = BattleState.TARGET;
				}
				
			}
		}
				
		//Deal with Target State
		if(battleState == BattleState.TARGET) {
			//Update select Button
			checkHandlerButtons(currentTouch);
			int currentTarget = findTarget(currentTouch);
			//If we have an actual target
			if(currentTarget != -1) {
				secondTarget = currentTarget;
				battleState = BattleState.UPDATE;
			}
		}
		
		//Deal with Update State
		if(battleState == BattleState.UPDATE) {
			//Buzz buzz baby
			Gdx.input.vibrate(200);
			//Create actions based creatures and move
			Skill skill;
			//If select is on, and we didn't click
			if(move != MoveSelection.CLICK) {
				skill = currentCreatures.get(firstTarget -1).getSkillList().get(MainConfig.SKILL_DIRECTION_MAP.get(move));
			} 
			//If we clicked
			else {
				skill = new Defend();
			}
			Gdx.app.log("Skill Name", String.valueOf(skill.getName()));
			if(skill.isReady()) {
				Gdx.app.log("Skill Cast:", skill.getTimer().getStatus());
				skill.perform(currentCreatures.get(firstTarget -1), currentCreatures.get(secondTarget -1));
			} else {
				Gdx.app.log("Skill Not Ready:", skill.getTimer().getStatus());
			}
			
			//Clear state variables
			clearBattle();
			//Reset to selection state
			battleState = BattleState.SELECTION;
		}
		

		
	}

	@Override
	public void load() {

	}

	@Override
	public void cleanUp() {

	}
	
	void checkHandlerButtons(Vector2 currentTouch) {		
		if(handlerButtonOne.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Handler One", "Inside");
			playerOne.getHandlerSkills().get(0).perform(currentCreatures.subList(0, 3), currentCreatures.subList(3, 5));
		} 
		else if(handlerButtonTwo.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Handler Two", "Inside");
			playerOne.getHandlerSkills().get(1).perform(currentCreatures.subList(0, 3), currentCreatures.subList(3, 5));
		}
		else if(handlerButtonThree.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Handler Three", "Inside");
			playerOne.getHandlerSkills().get(2).perform(currentCreatures.subList(0, 3), currentCreatures.subList(3, 5));
		}
		else if(handlerButtonFour.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Handler Four", "Inside");
			playerOne.getHandlerSkills().get(3).perform(currentCreatures.subList(0, 3), currentCreatures.subList(3, 5));
		}
	}
	
	void clearBattle() {
		firstTarget = -1;
		move = null;
		secondTarget = -1;
	}
	
	int findSelection(Vector2 currentTouch) {
		int selection = -1;
		
		if(targetOne.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Selection One", "Inside");
			selection = 1;
		} 
		else if(targetTwo.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Selection Two", "Inside");
			selection = 2;
		}
		else if(targetThree.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Selection Three", "Inside");
			selection = 3;
		}
		
		return selection;
	}
	int findTarget(Vector2 currentTouch) {
		int selection = -1;
		
		if(targetOne.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Target One", "Inside");
			selection = 1;
		} 
		else if(targetTwo.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Target Two", "Inside");
			selection = 2;
		}
		else if(targetThree.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Target Three", "Inside");
			selection = 3;
		}
		else if(targetFour.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Target Four", "Inside");
			selection = 4;
		} 
		else if(targetFive.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Target Five", "Inside");
			selection = 5;
		}
		else if(targetSix.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Target Six", "Inside");
			selection = 6;
		}
		
		return selection;
	}

	//When a Release Happens
	@Override
	public void onFlickReleased(FlickReleaseEvent event) {
		Gdx.app.log("Release", String.valueOf(event));
		this.move = event.selection;
		//If we got a click
		if(this.move == MoveSelection.CLICK) {
			//If we have already gotten our first target
			if(this.firstTarget != -1) {
				this.secondTarget = this.firstTarget;
				System.out.println("Inside");
				battleState = BattleState.UPDATE;
			}
		}
	}

}
