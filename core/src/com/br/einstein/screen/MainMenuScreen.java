package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.br.einstein.mechanics.FontParameters;

public class MainMenuScreen implements Screen {

    private FontParameters fontParameters = new FontParameters();

    private ScreenManager game;
    public Texture texture;
    private OrthographicCamera gameCam;
    private Viewport viewport;


    private Stage menuStage;
    private Button button;
    private Button exitButton;
    private Label logo;
    private Label madeBy;

    public MainMenuScreen(ScreenManager game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);
        texture = new Texture("1_empxo5xvgaefru0-13999131.png");


        //Setting stage
        menuStage = new Stage(viewport);
        Gdx.input.setInputProcessor(menuStage);

        //Creating labels
        logo = new Label("Street Fortal", fontParameters.getLabelStyle100());
        logo.setSize(100, 100);
        logo.setPosition(ScreenManager.V_WIDTH / 2f - 125, ScreenManager.V_HEIGTH / 2f + 300, Align.center);
        menuStage.addActor(logo);

        madeBy = new Label("Made by\n     CCR", fontParameters.getLabelStyle50());
        madeBy.setPosition(ScreenManager.V_WIDTH - 120, 100, Align.center);
        menuStage.addActor(madeBy);

        //Creating buttons
        button = new TextButton("Jogar", fontParameters.getButtonStyle());
        button.setSize(20, 20);
        button.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f + 100, Align.center);

        exitButton = new TextButton("Sair", fontParameters.getButtonStyle());
        exitButton.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f - 20, Align.center);
        menuStage.addActor(button);
        menuStage.addActor(exitButton);


        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                loadSelection();
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(texture,0,0,ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH);
        game.batch.end();

        game.batch.setProjectionMatrix(menuStage.getCamera().combined);

        menuStage.act();
        menuStage.draw();



    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        menuStage.dispose();
        fontParameters.dispose();
    }
    public void loadSelection() {
        hide();
        game.setScreen(new SelectionScreen(game));
    }
}