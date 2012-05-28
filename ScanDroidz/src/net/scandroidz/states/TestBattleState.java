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

public class TestBattleState implements State, FlickReleaseEventListener {
	
	//Rendering 
	private SpriteBatch spriteBatch;
	
	//Flick Stick
	private FlickStick flickStick;
	
	//Cast Button
	private Rectangle castButton;
	
	//Current State Variables
	BattleState battleState = null;
	MoveSelection move = null;
	
	//Players
	Player playerOne;
	
	//Creature List and Skill Lists
	List<Creature> currentCreatures = Arrays.asList(new Creature[2]);

	//Textures
	//TODO: Dynamicly load the creatures
	Texture one = new Texture(Gdx.files.internal("data/one.png"));
	Texture two = new Texture(Gdx.files.internal("data/two.png"));

	
	BitmapFont font = new BitmapFont(Gdx.files.internal("data/font16.fnt"),
	         Gdx.files.internal("data/font16.png"), false);

	Texture castTexture = new Texture(Gdx.files.internal("data/handlerOne.png"));


	//YOU BETTER FUCKING CALL SETUP FAGGOT
	public TestBattleState() {
		startUp();
	}
	
	//This should be called directly after the constructor
	public void setup(Creature targetOne, Creature targetTwo, Player playerOne) {
		//Link creatures to their location
		currentCreatures.set(0, targetOne);
		currentCreatures.set(1, targetTwo);
		
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
		castButton = new Rectangle(0, Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/4) * 4), Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		
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
		spriteBatch.draw(one, 0, Gdx.graphics.getHeight() -  Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		spriteBatch.draw(two, Gdx.graphics.getWidth()/2 + Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight() -  Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
		
		//font to black
		font.setColor(0.5f, 0.5f, 1.0f, 1.0f);
		
		//Creature Healths
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(0).getActiveHealth()), 140, Gdx.graphics.getHeight() -  Gdx.graphics.getHeight()/4 + 100);
		font.draw(spriteBatch, String.valueOf(currentCreatures.get(1).getActiveHealth()), Gdx.graphics.getWidth()/2 + Gdx.graphics.getWidth()/4 + 140, Gdx.graphics.getHeight() -  Gdx.graphics.getHeight()/4 + 100);
		
		//render cast button
		spriteBatch.draw(castTexture, castButton.x, castButton.y, castButton.getWidth(), castButton.getHeight());
		
		//render Spell Choice
		if(move != null) {
			font.draw(spriteBatch, String.valueOf(currentCreatures.get(0).getSkillList().get(MainConfig.SKILL_DIRECTION_MAP.get(move)).getName()), 280, Gdx.graphics.getHeight() -  ((Gdx.graphics.getHeight()/4 )*3));
		}
		spriteBatch.end();
	}

	@Override
	public void update(float delta) {		
		
		Vector2 currentTouch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		flickStick.update(delta);
		
		//Deal with Selection State
		if(battleState == BattleState.SELECTION) {
			if(Gdx.input.justTouched()) {
				//Make sure we have selected a move
				if(move != null){
					//Check if they pressed Cast
					checkCastButton(currentTouch);
				}
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
				skill = currentCreatures.get(0).getSkillList().get(MainConfig.SKILL_DIRECTION_MAP.get(move));
			} 
			//If we clicked
			else {
				skill = new Defend();
			}
			Gdx.app.log("Skill Name", String.valueOf(skill.getName()));
			if(skill.isReady()) {
				Gdx.app.log("Skill Cast:", skill.getTimer().getStatus());
				skill.perform(currentCreatures.get(0), currentCreatures.get(1));
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
	
	private void checkCastButton(Vector2 currentTouch) {
		if(castButton.contains(currentTouch.x, currentTouch.y)) {
			Gdx.app.log("Cast Pressed:", move.toString());
			battleState = BattleState.UPDATE;
		}
	}
	
	void clearBattle() {
		move = null;
	}
	
	//When a Release Happens
	@Override
	public void onFlickReleased(FlickReleaseEvent event) {
		Gdx.app.log("Release", String.valueOf(event));
		this.move = event.selection;
	}

}
