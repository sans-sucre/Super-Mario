package com.sanssucre.mario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
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
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);


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
