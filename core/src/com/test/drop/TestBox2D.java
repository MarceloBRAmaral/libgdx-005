package com.test.drop;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
//import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class TestBox2D extends ApplicationAdapter {
	//SpriteBatch batch;
	//Texture img;
	private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    static private final float TIMESTEP = 1/60f;
    static private final int VELOCITYITERATIONS = 8;
    static private final int POSITIONITERATIONS = 3;

    @Override
    public void create() {
        world = new World(new Vector2(0,-9.81f),true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/400, Gdx.graphics.getHeight()/400);

        //common for bodies and ground
        BodyDef bodyDef = new BodyDef();

        //body definition
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, .5f);

        //body shape definition
        CircleShape bodyShape = new CircleShape();
        bodyShape.setRadius(.05f);

        //fixture definition
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .7f;

        //takes all definitions and create a body with that
        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyShape.dispose();

        ////////////////////second ball
        //body definition
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 1);

        //body shape definition
        CircleShape bodyShape2 = new CircleShape();
        bodyShape2.setRadius(.05f);

        //fixture definition
        //FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape2;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .7f;

        //takes all definitions and create a body with that
        world.createBody(bodyDef).createFixture(fixtureDef);

        bodyShape2.dispose();




        //ground definition
        //body definition
        //BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        //body shape definition
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-.5f,1), new Vector2(-.5f,-1f),
            new Vector2(.51f,-1.01f), new Vector2(.51f,1)});

        //fixture definition
        //FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        //fixtureDef.density = 2.5f;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        //takes all definitions and create a body with that
        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape.dispose();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(world, camera.combined);

        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();


    }

//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//	}

//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}

//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
