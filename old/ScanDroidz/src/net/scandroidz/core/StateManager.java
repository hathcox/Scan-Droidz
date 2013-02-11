package net.scandroidz.core;

import java.util.HashMap;
import java.util.Map;

import net.scandroidz.person.Player;

public class StateManager {

	private static StateManager stateManagerRef;
	
	private State currentState;
	private Map<String,State> states;
	private Player player;
	public boolean renderPaused = false;

	public static StateManager getInstance()
	{
		if( stateManagerRef != null)
		{
			return stateManagerRef;
		}
		
		stateManagerRef = new StateManager();
		return stateManagerRef;
	}
	
	private StateManager()
	{
		states = new HashMap<String,State>();
	}
	
	public void registerState( String stateName, State state)
	{
		if( state != null )
		{
			states.put(stateName, state);
		}
	}
	
	public void unRegisterState( String stateName )
	{
		if( states.containsKey(stateName) )
		{
			states.remove(stateName);
		}
	}
	
	public void clear()
	{
		states.clear();
	}
	
	public void update( float delta)
	{
		if( currentState != null )
		{
			currentState.update(delta);
		}
	}
	
	public void render()
	{
		if( currentState != null  && !renderPaused)
		{
			currentState.render();
		}
	}
	
	public State getState( String stateName )
	{
		if( states.get(stateName) != null )
		{
			return states.get(stateName);
		}
		
		return null;
	}
	
	public void changeState( String stateName )
	{
		if( states.get(stateName) != null )
		{
			if( currentState != null )
			{
				currentState.cleanUp();
			}
			
			currentState = states.get(stateName);
			currentState.startUp();
			currentState.load();
		}
	}
	
	public State getCurrentState()
	{
		return currentState;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
