package com.scandroids.core;

/**
 * Generic interface for state management
 * @author haddaway
 *
 */
public interface State {

	void startUp();
	
	void render();
	
	void update(float delta);
	
	void load();
	
	void cleanUp();
}
