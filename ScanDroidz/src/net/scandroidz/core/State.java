package net.scandroidz.core;

public interface State {
	
	void startUp();
	
	void render();
	
	void update(float delta);
	
	void load();
	
	void cleanUp();
	
}
