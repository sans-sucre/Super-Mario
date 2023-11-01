package com.sanssucre.mario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sanssucre.mario.Scenes.Hud;
import com.sanssucre.mario.SuperMario;

public class PlayScreen implements Screen {
    private SuperMario game;
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    public PlayScreen(SuperMario game) {
        this.game = game;
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(SuperMario.V_WIDTH, SuperMario.V_HEIGHT, gameCam);// viewport decides
        this.hud = new Hud(game.batch);
        // how the game looks like in different devices
        //StretchViewport and ScreenViewport are also available
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("My_super_mario.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);

        this.world = new World(new Vector2(0,0), true);
        this.b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        //create ground bodies/fixtures
        for (RectangleMapObject objects : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = objects.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //create coin bodies/fixtures
        for (RectangleMapObject objects : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = objects.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        for (RectangleMapObject objects : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = objects.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


    }
    @Override
    public void show() {

    }


    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            gameCam.position.x += 100*dt;
        }
    }

    public void update(float dt){
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //render our Box2DDebugLines
        b2dr.render(world, gameCam.combined);

        this.game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        this.hud.stage.draw();
       // this.game.batch.begin();
       // this.game.batch.draw(texture, 0, 0);
       // this.game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
