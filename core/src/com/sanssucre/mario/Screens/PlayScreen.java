package com.sanssucre.mario.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sanssucre.mario.SuperMario;

public class PlayScreen implements Screen {
    private SuperMario game;
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    public PlayScreen(SuperMario game) {
        this.game = game;
        this.texture = new Texture("badlogic.jpg");
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(800, 480, gameCam);// viewport decides
        // how the game looks like in different devices
        //StretchViewport

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.batch.setProjectionMatrix(gameCam.combined);
        this.game.batch.begin();
        this.game.batch.draw(texture, 0, 0);
        this.game.batch.end();

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
