package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    private ScreenManager game;
    Texture texture;
    Texture img;
    private OrthographicCamera gamecam;
    private Viewport viewport;


    private Stage menuStage;
    private Table menuTable;
    private Button button;
    private Button exitButton;
    private TextButton.TextButtonStyle buttonStyle;
    private Label logo;

    public MainMenuScreen(ScreenManager game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gamecam);
        texture = new Texture("1_empxo5xvgaefru0-13999131.png");
        img = new Texture("icon.png");


        menuStage = new Stage(viewport);
        menuTable = new Table();
        menuTable.setFillParent(true);
        Gdx.input.setInputProcessor(menuStage);

        logo = new Label("Street Fortal", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        logo.setSize(100, 100);
        logo.setPosition(ScreenManager.V_WIDTH / 2f - 40, ScreenManager.V_HEIGTH - 100);
        menuStage.addActor(logo);

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = Color.WHITE;
        button = new TextButton("Jogar", buttonStyle);
        button.setSize(20, 20);
        button.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f + 20,Align.center);

        exitButton = new TextButton("Sair", buttonStyle);
        exitButton.setSize(20, 20);
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
    }
    public void loadSelection() {
        hide();
        game.setScreen(new SelectionScreen(game));
    }
}