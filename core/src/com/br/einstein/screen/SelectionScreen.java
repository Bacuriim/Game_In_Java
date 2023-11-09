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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.BinaryHeap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SelectionScreen implements Screen {
    private ScreenManager game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    Texture texture;

    private Stage stage;
    private Button returnButton;
    private TextButton.TextButtonStyle buttonStyle1;



    public SelectionScreen(ScreenManager game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gamecam);
        stage = new Stage(viewport);
        texture = new Texture("icon.png");

        buttonStyle1 = new TextButton.TextButtonStyle();
        buttonStyle1.font = new BitmapFont();
        buttonStyle1.fontColor = Color.WHITE;

        returnButton = new TextButton("Voltar", buttonStyle1);
        returnButton.setPosition(10, ScreenManager.V_HEIGTH - 20);


        stage.addActor(returnButton);
        Gdx.input.setInputProcessor(stage);

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                returnScreen();
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
        game.batch.draw(texture, 0, 0, ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH);
        game.batch.end();

        stage.act();
        stage.draw();
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
        stage.dispose();
    }

    public void returnScreen() {
        hide();
        game.setScreen(new MainMenuScreen(game));
    }
}
