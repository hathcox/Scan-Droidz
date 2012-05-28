package net.scandroidz.widgets.flickstick;

import java.util.ArrayList;
import java.util.EventListener;

import net.scandroidz.enums.BattleEnums.BattleState;
import net.scandroidz.enums.BattleEnums.MoveSelection;
import net.scandroidz.shapes.Triangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class FlickStick {
	// Create the listener list
    protected ArrayList<EventListener> releaseListenerList = new ArrayList<EventListener>();
    protected ArrayList<EventListener> clickListenerList = new ArrayList<EventListener>();

    //So we can draw shit
    private SpriteBatch spriteBatch = new SpriteBatch();
    
    //Inner stick shit
    private Texture innerStickTexture;
    private Circle innerStick;
    private Vector2 innerStickPosition = new Vector2();
    
    //Outer stick shit
    private Texture outerStickTexture;
    private Circle outerBounds;
    
    //general shit
    private Vector2 position;
    private int size;
    private float scale;
    private boolean isTouched;
    private MoveSelection currentQuadrant = MoveSelection.NONE;
    
    //Quadrants
    private Triangle north;
    private Triangle east;
    private Triangle south;
    private Triangle west;
    
    //Font for debugging
    BitmapFont font = new BitmapFont(Gdx.files.internal("data/font16.fnt"), Gdx.files.internal("data/font16.png"), false);

    /**
     * Flick Stick should take a given Position, Size, and Scale to be created
     * 
     */
    public FlickStick(Vector2 position, int size, float scale) {
    	this.position = position;
    	this.size = size;
    	this.scale = scale;
    	//Load Default Sprites
    	innerStickTexture = new Texture(Gdx.files.internal("data/innerStick.png"));
    	outerStickTexture = new Texture(Gdx.files.internal("data/outerBounds.png"));
    	//Set innerStick to default location
    	innerStickPosition.set(position.x + ((size * scale) / 4), position.y + ((size * scale) / 4));
    	
    	//Setup inner and bounds circles
    	innerStick =  new Circle(position.x + (size * scale) / 2, position.y + (size * scale) / 2, (size * scale) / 2 - (size * scale) / 4);
    	outerBounds = new Circle(position.x + (size * scale) / 2, position.y + (size * scale) / 2, (size * scale) / 2);
    	
    	//Setup Quadrants
//    	north = new Triangle(position.x, position.y + (size * scale), position.x + (size * scale),  position.y + (size * scale), position.x + ((size * scale) / 2),  position.y + ((size * scale) /2));
//    	east = new Triangle(position.x + (size * scale),  position.y + (size * scale), position.x + ((size * scale) / 2),  position.y + ((size * scale) /2), position.x + (size * scale), position.y);
//    	south = new Triangle(position.x, position.y,  position.x + ((size * scale) / 2),  position.y + ((size * scale) /2), position.x + (size * scale), position.y);
//    	west = new Triangle(position.x + ((size * scale) / 2),  position.y + ((size * scale) /2), position.x, position.y, position.x, position.y + (size * scale));
    
    	north = new Triangle(position.x, position.y + (size * scale), position.x + (size * scale),  position.y + (size * scale), position.x + ((size * scale) / 2),  position.y + ((size * scale) /2));
    	east = new Triangle(position.x + (size * scale),  position.y + (size * scale), position.x + ((size * scale) / 2),  position.y + ((size * scale) /2), position.x + (size * scale), position.y);
    	south = new Triangle(position.x, position.y,  position.x + ((size * scale) / 2),  position.y + ((size * scale) /2), position.x + (size * scale), position.y);
    	west = new Triangle(position.x + ((size * scale) / 2),  position.y + ((size * scale) /2), position.x, position.y, position.x, position.y + (size * scale));
    
    
    }
    
    private void resetStick() {
    	innerStickPosition.set(position.x + ((size * scale) / 4), position.y + ((size * scale) / 4));
    	currentQuadrant = MoveSelection.NONE;
    }
    // This methods allows classes to register for Release Events
    public void addReleaseListener(FlickReleaseEventListener listener) {
    	releaseListenerList.add(listener);
    }
    
 // This methods allows classes to register for Click Events
    public void addClickListener(FlickClickEventListener listener) {
    	clickListenerList.add(listener);
    }

    // This methods allows classes to unregister for Release Events
    public void removeReleaseListener(FlickReleaseEventListener listener) {
    	releaseListenerList.remove(listener);
    }
    
 // This methods allows classes to unregister for Click Events
    public void removeReleaseListener(FlickClickEventListener listener) {
    	clickListenerList.remove(listener);
    }

    // This private class is used to fire Release Events
    private void fireReleaseEvent(FlickReleaseEvent event) {
       for (EventListener listener : releaseListenerList) {
		((FlickReleaseEventListener) listener).onFlickReleased(event);
       }
    }
    
    // This private method is used to fire Click Events
    private void fireClickEvent(FlickClickEvent event) {
	       for (EventListener listener : clickListenerList) {
			((FlickClickEventListener) listener).onFlickClicked(event);
	       }
    }

    /**
     * This method should be called every frame to deal with the graphical display
     * 
     */
    public void render() {
    	GLCommon gl = Gdx.gl;
    	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    	//Start Drawing Shit
    	spriteBatch.begin();
    	
    	//Draw outer Stick
    	spriteBatch.draw(outerStickTexture, position.x, position.y, (size * scale), (size * scale));
    	
    	//Draw inner Stick
    	spriteBatch.draw(innerStickTexture, innerStickPosition.x, innerStickPosition.y, (size * scale) / 2, (size * scale) / 2);
    	
    	//Finish Drawing Shit
    	spriteBatch.end();
    	
    }
    
    /**
     * This is should be called before render to decide what to do with everything that the user inputs
     * @return 
     */

    public void update(float delta) {
    	//If the user touched the screen
    	if(Gdx.input.isTouched()) {
    		Vector2 currentTouch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY() );
    		//If they touched the inner joy stick
    		if(outerBounds.contains(currentTouch)) {
    			//Follow their movement as long as its within the outerBounds
    			innerStickPosition.x = (Gdx.input.getX() - ((size * scale) /4)); //* delta;
    			innerStickPosition.y = ((Gdx.graphics.getHeight() - Gdx.input.getY()) - ((size * scale) /4));// * delta;
    			//Get their quadrant
    			findQuadrant(currentTouch);
    			isTouched = true;
    		}
    	}
    	else {
    		if(isTouched) {
    			fireReleaseEvent(new FlickReleaseEvent(currentQuadrant));
    			isTouched = false;
    		}
    		resetStick();
    	}
    }

	private void findQuadrant(Vector2 currentTouch) {
		if(north.isInside(currentTouch.x, currentTouch.y) && !innerStick.contains(currentTouch)) {
			currentQuadrant = MoveSelection.NORTH;
		}
		else if (east.isInside(currentTouch.x, currentTouch.y) && !innerStick.contains(currentTouch)){
			currentQuadrant = MoveSelection.EAST;
		}
		else if (south.isInside(currentTouch.x, currentTouch.y) && !innerStick.contains(currentTouch)){
			currentQuadrant = MoveSelection.SOUTH;
		}
		else if (west.isInside(currentTouch.x, currentTouch.y) && !innerStick.contains(currentTouch)){
			currentQuadrant = MoveSelection.WEST;
		}
		else {
			currentQuadrant = MoveSelection.CLICK;
		}
	}
}
