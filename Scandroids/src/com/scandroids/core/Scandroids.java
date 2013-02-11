package com.scandroids.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scandroids.states.SinglePlayerBattle;

/*
 * The magic maker
 */
public class Scandroids implements ApplicationListener {
	
	private StateManager stateManager;
	
	private long syncTime;
	private long lastUpdate;
	
	@Override
	public void create() {		
		//Setup the state manager
		stateManager = StateManager.getInstance(); 
		
		//Initiliaze timers
		syncTime = System.nanoTime();
		lastUpdate = System.nanoTime();
		
		//Register Game States
		registerStates();
		
		//Switch States
		stateManager.changeState("SinglePlayerBattle");
	}

	/**
	 * This will register all of the states that exist in the game
	 */
	private void registerStates() {
		stateManager.registerState("SinglePlayerBattle", new SinglePlayerBattle());
	}

	@Override
	public void dispose() {
		stateManager.clear();
	}

	/**
	 * This is the 
	 */
	@Override
	public void render() {		
		//Get the start frame time
		syncTime = System.nanoTime();
		
		//Clear Screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//Update State
		stateManager.update(syncTime - lastUpdate);
		
		//Render State
		stateManager.getCurrentState().render();
				
		//Set end frame time
		lastUpdate = syncTime;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		this.create();
	}
}
