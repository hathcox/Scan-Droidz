package com.scandroids.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.scandroids.core.State;
import com.scandroids.physics.LineSegment;

/**
 * This is used to create and play out user vs AI battles
 * @author haddaway
 *
 */
public class SinglePlayerBattle implements State{
	
	//Rendering 
	private SpriteBatch spriteBatch;
		
	private Sprite jumperSprite;
	
	//Buttons
	private Texture evadeButtonTexture = new Texture(Gdx.files.internal("data/one.png"));
	private Rectangle evadeButton;
	private Texture attackButtonTexture = new Texture(Gdx.files.internal("data/two.png"));
	private Rectangle attackButton;
	
	//Physics
	private World world;
	private Body jumper;
	public static float PIXELS_PER_METER = 60f;
	
	public static final double SCREEN_CONSTANT = 0.0000078125f;
	
	public SinglePlayerBattle() {
		startUp();
	}
	
	@Override
	public void startUp() {
		spriteBatch = new SpriteBatch();
		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
		System.out.println("Height:"+height + " Width:"+width);

		world = new World(new Vector2(0.0f, -10.0f), true);
				
		jumperSprite = new Sprite(new Texture(Gdx.files.internal("data/three.png")), 0, 0, (int)(width*0.05f), (int)(height*0.1f));
		
		BodyDef jumperBodyDef = new BodyDef();
		jumperBodyDef.type = BodyDef.BodyType.DynamicBody;
		jumperBodyDef.position.set((Gdx.graphics.getWidth()/PIXELS_PER_METER)/4, (Gdx.graphics.getHeight()/PIXELS_PER_METER)/2);

		jumper = world.createBody(jumperBodyDef);

		PolygonShape jumperShape = new PolygonShape();
		jumperShape.setAsBox(jumperSprite.getWidth() / (2 * PIXELS_PER_METER),
				jumperSprite.getHeight() / (2 * PIXELS_PER_METER));

		jumper.setFixedRotation(true);
		

		FixtureDef jumperFixtureDef = new FixtureDef();
		jumperFixtureDef.shape = jumperShape;
		//jumperFixtureDef.density = 1.0f;
		//jumperFixtureDef.friction = 5.0f;

		jumper.createFixture(jumperFixtureDef);
		jumperShape.dispose();
		
		evadeButton = new Rectangle(0,0,width/4,height/4);
		
		attackButton = new Rectangle((float)(width*.75),0, width/4, height/4);
		
		//This will setup the colision boundries
		createColisions();
	}
	
	private void createColisions() {
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.type = BodyDef.BodyType.StaticBody;
		Body groundBody = world.createBody(groundBodyDef);

		EdgeShape mapBounds = new EdgeShape();
		/* Old bottom floor
		mapBounds.set(new Vector2(0.0f, 0.0f), new Vector2(Gdx.graphics.getWidth()
				/ PIXELS_PER_METER, 0.0f));
		groundBody.createFixture(mapBounds, 0);
		*/
		
		mapBounds.set(new Vector2(0.0f, (Gdx.graphics.getHeight()/PIXELS_PER_METER)/4), new Vector2(Gdx.graphics.getWidth()
				/ PIXELS_PER_METER, (Gdx.graphics.getHeight()/PIXELS_PER_METER)/4));
		groundBody.createFixture(mapBounds, 0);
		
		mapBounds.set(new Vector2(0.0f, Gdx.graphics.getHeight() / PIXELS_PER_METER),
				new Vector2(Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight()
						/ PIXELS_PER_METER));
		groundBody.createFixture(mapBounds, 0);

		mapBounds.set(new Vector2(0.0f, 0.0f), new Vector2(0.0f,
				Gdx.graphics.getHeight() / PIXELS_PER_METER));
		groundBody.createFixture(mapBounds, 0);

		mapBounds.set(new Vector2(Gdx.graphics.getWidth() / PIXELS_PER_METER, 0.0f),
				new Vector2(Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight()
						/ PIXELS_PER_METER));
		groundBody.createFixture(mapBounds, 0);

		mapBounds.dispose();
	}

	//This should be called directly after the constructor
	public void setup() {
		
	}

	@Override
	public void render() {
		//Clear Screen
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		world.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);
		
		spriteBatch.begin();
		
		jumperSprite.setPosition(
				PIXELS_PER_METER * jumper.getPosition().x
						- jumperSprite.getWidth() / 2,
				PIXELS_PER_METER * jumper.getPosition().y
						- jumperSprite.getHeight() / 2);
		jumperSprite.draw(spriteBatch);
		
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

			if (Math.abs(jumper.getLinearVelocity().y) < 1e-9) {
				jumper.applyLinearImpulse(new Vector2(0.0f, 
						//Gdx.graphics.getHeight()/(Gdx.graphics.getHeight()/10)
						8f
						),
						jumper.getWorldCenter());
			}
		}
		if(attackButton.contains(currentTouch.x, currentTouch.y)) {
			System.out.println("Attack Clicked");
			float height = Gdx.graphics.getHeight();
			float width = Gdx.graphics.getWidth();
			System.out.println("Height:"+height + " Width:"+width);
			System.out.println("Player h:"+jumperSprite.getHeight() + " w:"+jumperSprite.getWidth() +" m:"+jumper.getMass()+" g:"+jumper.getGravityScale());
			System.out.println(height*width);
			System.out.println(jumper.getMassData().mass);
			System.out.println(jumper.getMassData().I);
			System.out.println(jumper.getMassData().center);
		}
	}

	@Override
	public void load() {
		
	}

	@Override
	public void cleanUp() {
		
	}

}
