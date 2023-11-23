package com.br.einstein.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.br.einstein.mechanics.FontParameters;

public class SelectionScreen implements Screen {
    private ScreenManager game;
    private OrthographicCamera gameCam;
    private Viewport viewport;
    public Texture texture;

    private FontParameters fontParameters = new FontParameters();

    private Stage stage;
    private Button returnButton;
    private Button testButton;
    private ImageButton iracemaChar;

    private int char1;
    private int char2;
    private int selected = 0;



    public SelectionScreen(ScreenManager game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);
        stage = new Stage(viewport);
        texture = new Texture("icon.png");



        returnButton = new TextButton("Voltar", fontParameters.getButtonStyle());
        returnButton.setPosition(30, 30);

        testButton = new TextButton("Teste", fontParameters.getButtonStyle());
        testButton.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f);

        TextureRegionDrawable texRegDraIra = new TextureRegionDrawable(new Texture("assets/IracemaSprites/Iracema_head.png"));
        iracemaChar = new ImageButton(texRegDraIra);
        iracemaChar.setPosition(ScreenManager.V_WIDTH / 5f, ScreenManager.V_HEIGTH / 3f, Align.center);


        stage.addActor(iracemaChar);
        stage.addActor(returnButton);
        stage.addActor(testButton);


        Gdx.input.setInputProcessor(stage);

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                returnScreen();
            }
        });

        testButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                loadGame();
            }
        });

        iracemaChar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("iracema selected");
                if (selected == 0) {
                    char1 = 1;
                } else if (selected == 1) {
                    char2 = 1;
                }
                selected++;
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

        if (selected >= 1) {
            if (char1 == 1) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(new Texture("assets/IracemaSprites/Iracema_parada_D.png")),
                        ScreenManager.V_WIDTH / 20f - 350, ScreenManager.V_HEIGTH / 10f + 100, 750, 800);
                game.batch.end();
            } if (char2 == 1) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(new Texture("assets/IracemaSprites/Iracema_parada_E.png")),
                        ScreenManager.V_WIDTH - 500, ScreenManager.V_HEIGTH / 10f + 100 , 750, 800);
                game.batch.end();
            }
        }

        if (selected == 2) {
            Button lutar = new TextButton("Lutar", fontParameters.getButtonStyle());
            lutar.setPosition(ScreenManager.V_WIDTH / 2f, 100, Align.center);
            stage.addActor(lutar);

            lutar.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    selected = 0;
                    loadGame();
                }
            });
        }
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
        fontParameters.dispose();
    }

    public void returnScreen() {
        hide();
        game.setScreen(new MainMenuScreen(game));
    }

    public void loadGame() {
        hide();
        game.setScreen(new GameScreen(game));
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);
    }
}
