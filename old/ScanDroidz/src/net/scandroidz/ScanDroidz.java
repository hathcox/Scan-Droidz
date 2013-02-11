package net.scandroidz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import net.scandroidz.core.StateManager;
import net.scandroidz.handlerskill.HandlerSkill;
import net.scandroidz.handlerskill.HealAll;
import net.scandroidz.person.Player;
import net.scandroidz.states.MainMenu;
import net.scandroidz.states.MainMenuOld;
import net.scandroidz.states.SinglePlayerBattleState;
import net.scandroidz.states.TestBattleState;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;

public class ScanDroidz implements ApplicationListener{
		
	private StateManager stateManager;
	
	private Player player;

	private long syncTime;
	private long lastUpdate;
	
	@Override
	public void create() {

		stateManager = StateManager.getInstance(); 
		
		//Initiliaze timers
		syncTime = System.nanoTime();
		lastUpdate = System.nanoTime();
		
		stateManager.registerState("MainMenu", new MainMenu());
		
		//Setup Players
		try {
			player = new Player("John");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//Stub Handler Skills
		List<HandlerSkill> playerSkills = new ArrayList<HandlerSkill>();
		
		playerSkills.add(new HealAll());
		playerSkills.add(new HealAll());
		playerSkills.add(new HealAll());
		playerSkills.add(new HealAll());
		
		player.setHandlerSkills(playerSkills);
		stateManager.registerState("SinglePlayerBattleState", new SinglePlayerBattleState());
//		stateManager.registerState("TestBattleState", new TestBattleState());
		
		stateManager.changeState("MainMenu");
		
//		//Check if they have played the game before;
//		FileHandle history = Gdx.files.external("history.dat");
//		//If we have the history file
//		if(history.exists()) {
//			//Decrypt it
//			//Confirm its still valid
//			//Unserilize player and load it into our player object
//			try {
//				ObjectInputStream ois = new ObjectInputStream(history.read());
//				player = (Player)ois.readObject();
//				Gdx.app.log("History:", "Loaded player [" + player.getName() + "]");
//				stateManager.setPlayer(player);
//				System.out.println(player.getHandlerSkills());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		//Create a new Player and write that shit out	
//		}else {
//			OutputStream historyStream = history.write(false);
//			try {
//				player = new Player("John");
//				
//				//Stub Handler Skills
//				List<HandlerSkill> playerSkills = new ArrayList<HandlerSkill>();
//				
//				playerSkills.add(new HealAll());
//				playerSkills.add(new HealAll());
//				playerSkills.add(new HealAll());
//				playerSkills.add(new HealAll());
//				
//				player.setHandlerSkills(playerSkills);
//				
//				ObjectOutputStream oos = new ObjectOutputStream(historyStream);
//				oos.writeObject(player);
//				Gdx.app.log("History:", "Created new History");
//				stateManager.setPlayer(player);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					historyStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stateManager.clear();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

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
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		this.create();
		
	}
	
	public Player getPlayer() {
		return player;
	}

}
