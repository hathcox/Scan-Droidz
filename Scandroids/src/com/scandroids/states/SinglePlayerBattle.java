package com.scandroids.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.scandroids.core.State;

/**
 * This is used to create and play out user vs AI battles
 * @author haddaway
 *
 */
public class SinglePlayerBattle implements State{

	//Rendering 
	private SpriteBatch spriteBatch;
		
	//Buttons
	private Texture evadeButtonTexture = new Texture(Gdx.files.internal("data/one.png"));
	private Rectangle evadeButton;
	private Texture attackButtonTexture = new Texture(Gdx.files.internal("data/two.png"));
	private Rectangle attackButton;
	
	public SinglePlayerBattle() {
		startUp();
	}
	
	@Override
	public void startUp() {
		spriteBatch = new SpriteBatch();
		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
		
		System.out.println("Height:"+height + " Width:"+width);
		
		evadeButton = new Rectangle(0,0,width/4,height/4);
		
		attackButton = new Rectangle((float)(width*.75),0, width/4, height/4);
	}
	
	//This should be called directly after the constructor
	public void setup() {
		
	}

	@Override
	public void render() {
		//Clear Screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		
		//Render Buttons
		spriteBatch.draw(evadeButtonTexture, evadeButton.x, evadeButton.y, evadeButton.width, evadeButton.height);
		spriteBatch.draw(attackButtonTexture, attackButton.x, attackButton.y, attackButton.width, attackButton.height);
		
		spriteBatch.end();
		
	}

	@Override
	public void update(float delta) {
		Vector2 currentTouch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

		if(Gdx.input.justTouched()) {
			//Check Buttons
			checkButtons(currentTouch);
		}
		
	}

	private void checkButtons(Vector2 currentTouch) {
		if(evadeButton.contains(currentTouch.x, currentTouch.y)) {
			System.out.println("Evade Clicked");
		}
		if(attackButton.contains(currentTouch.x, currentTouch.y)) {
			System.out.println("Attack Clicked");
		}
	}

	@Override
	public void load() {
		
	}

	@Override
	public void cleanUp() {
		
	}

}
