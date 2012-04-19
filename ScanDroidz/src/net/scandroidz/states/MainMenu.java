package net.scandroidz.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import net.scandroidz.ScanDroidz;
import net.scandroidz.core.State;
import net.scandroidz.core.StateManager;
import net.scandroidz.creature.DickMonster;
import net.scandroidz.enums.CreatureEnums.Element;
import net.scandroidz.enums.CreatureEnums.Stat;

public class MainMenu implements State {
	private Rectangle play;
	private Rectangle options;
	private Rectangle quit;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	private Texture playButton;
	private Texture optionsButton;
	private Texture	exitButton;

	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		play = new Rectangle(Gdx.graphics.getWidth()/4, 
				Gdx.graphics.getHeight() -  Gdx.graphics.getHeight()/2, 
				Gdx.graphics.getWidth()/4, 
				Gdx.graphics.getHeight()/4);
		
		options = new Rectangle(play.x,
				play.y -  Gdx.graphics.getHeight()/4, 
				play.width, 
				play.height);
		
		quit = new Rectangle(options.x,
				options.y -  Gdx.graphics.getHeight()/4, 
				options.width, 
				options.height);
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/font16.fnt"),
		         Gdx.files.internal("data/font16.png"), false);
		playButton = new Texture(Gdx.files.internal("data/playButton.png"));
		optionsButton = new Texture(Gdx.files.internal("data/optionsButton.png"));
		exitButton = new Texture(Gdx.files.internal("data/exitButton.png"));

	}

	@Override
	public void render() {
		font.setColor(0.0f, 0.0f, 1.0f, 1.0f); // tint font blue
		spriteBatch.begin();
		
		font.draw(spriteBatch, "MainMenu", 0, Gdx.graphics.getHeight());
		
		//Draw buttons
		spriteBatch.draw(playButton, play.x, play.y, play.width, play.height);
		spriteBatch.draw(optionsButton, options.x, options.y, options.width, options.height);
		spriteBatch.draw(exitButton, quit.x, quit.y, quit.width, quit.height);
		
		spriteBatch.end();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		Vector2 currentTouch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		if(Gdx.input.justTouched()) {
			if(play.contains(currentTouch.x, currentTouch.y)) {
				Gdx.app.log("PlayButton", "Pressed");
				
				//Setup with the monsters that matter, for testing I made some bullshit
				((SinglePlayerBattleState)StateManager.getInstance().getState("SinglePlayerBattleState")).setup(
						new DickMonster("DickBalls Dragon", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO),
						new DickMonster("Cat", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO),
						new DickMonster("FBGM", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO),
						new DickMonster("Tites", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO),
						new DickMonster("BEER", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO),
						new DickMonster("lololLOLOllOl", 1, 1, 1, 1, 100, 1, Element.DICK, Stat.ONE, Stat.TWO),
						StateManager.getInstance().getPlayer());
				StateManager.getInstance().changeState("SinglePlayerBattleState");
			} 
			if(options.contains(currentTouch.x, currentTouch.y)) {
				Gdx.app.log("OptionsButton", "Pressed");
			} 
			if(quit.contains(currentTouch.x, currentTouch.y)) {
				Gdx.app.log("QuitButton", "Pressed");
				Gdx.app.exit();
			} 
		}
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}
