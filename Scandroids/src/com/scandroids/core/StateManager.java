package com.scandroids.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic Manager to deal with switching screens and states
 * @author haddaway
 *
 */
public class StateManager {
	private static StateManager stateManagerRef;

	private State currentState;
	private Map<String, State> states;
	private boolean renderPaused = false;

	public boolean isRenderPaused() {
		return renderPaused;
	}

	public void setRenderPaused(boolean renderPaused) {
		this.renderPaused = renderPaused;
	}

	public static StateManager getInstance() {
		if (stateManagerRef != null) {
			return stateManagerRef;
		}

		stateManagerRef = new StateManager();
		return stateManagerRef;
	}

	private StateManager() {
		states = new HashMap<String, State>();
	}

	public void registerState(String stateName, State state) {
		if (state != null) {
			states.put(stateName, state);
		}
	}

	public void unRegisterState(String stateName) {
		if (states.containsKey(stateName)) {
			states.remove(stateName);
		}
	}

	public void clear() {
		states.clear();
	}

	public void update(float delta) {
		if (currentState != null) {
			currentState.update(delta);
		}
	}

	public void render() {
		if (currentState != null && !renderPaused) {
			currentState.render();
		}
	}

	public State getState(String stateName) {
		if (states.get(stateName) != null) {
			return states.get(stateName);
		}

		return null;
	}

	public void changeState(String stateName) {
		if (states.get(stateName) != null) {
			if (currentState != null) {
				currentState.cleanUp();
			}

			currentState = states.get(stateName);
			currentState.startUp();
			currentState.load();
		}
	}

	public State getCurrentState() {
		return currentState;
	}

}
