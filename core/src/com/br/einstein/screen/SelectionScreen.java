package com.br.einstein.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.br.einstein.mechanics.ManagerGeral;
import org.lwjgl.system.CallbackI;

import java.nio.file.Path;

public class SelectionScreen implements Screen {
    private ScreenManager game;
    private OrthographicCamera gameCam;
    private Viewport viewport;
    public Texture texture;

    private ManagerGeral managerGeral = new ManagerGeral();

    private FontParameters fontParameters = new FontParameters();

    private Stage stage;
    private Button returnButton;
    private ImageButton castelaoButton;
    private ImageButton estacionamentoButton;
    private ImageButton iracemaChar;
    private ImageButton loiraChar;
    private ImageButton sartoChar;
    private Button lutar;
    private Music selectionMusic;

    private int char1;
    private int char2;
    private int selected = 0;
    private String arena;
    private String t = "icon.png";



    public SelectionScreen(ScreenManager game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);
        stage = new Stage(viewport);

        managerGeral.loadSons();
        managerGeral.loadIracema();
        managerGeral.loadLoira();
        managerGeral.loadSarto();
        managerGeral.loadFundos();
        managerGeral.manager.finishLoading();

        selectionMusic = managerGeral.manager.get("assets/musics/Selection_Screen.mp3", Music.class);

        selectionMusic.play();
        selectionMusic.setLooping(true);
        selectionMusic.setVolume(0.5f);

        returnButton = new TextButton("Voltar", fontParameters.getButtonStyle());
        returnButton.setPosition(30, 30);


        TextureRegionDrawable texIraB = new TextureRegionDrawable(managerGeral.manager.get("assets/IracemaSprites/Iracema_head.png", Texture.class));
        iracemaChar = new ImageButton(texIraB);
        iracemaChar.setPosition(ScreenManager.V_WIDTH / 5f, ScreenManager.V_HEIGTH / 3f, Align.center);

        TextureRegionDrawable texLoiB = new TextureRegionDrawable(managerGeral.manager.get("assets/LoiraSprites/Loira_head.png", Texture.class));
        loiraChar = new ImageButton(texLoiB);
        loiraChar.setPosition(ScreenManager.V_WIDTH / 5f + 60, ScreenManager.V_HEIGTH / 3f, Align.center);

        TextureRegionDrawable texSarB = new TextureRegionDrawable(managerGeral.manager.get("assets/SartoSprites/Sarto_head.png", Texture.class));
        sartoChar = new ImageButton(texSarB);
        sartoChar.setPosition(ScreenManager.V_WIDTH / 5f + 130, ScreenManager.V_HEIGTH / 3f, Align.center);


        TextureRegionDrawable texCast = new TextureRegionDrawable(managerGeral.manager.get("assets/backgrounds/gameBackgrounds/castelao.png", Texture.class));
        castelaoButton = new ImageButton(texCast);
        castelaoButton.setPosition(ScreenManager.V_WIDTH / 2f - 100, ScreenManager.V_HEIGTH / 2f);
        castelaoButton.setSize(100, 100);

        TextureRegionDrawable texEst = new TextureRegionDrawable(managerGeral.manager.get("assets/backgrounds/gameBackgrounds/estacionamento.png", Texture.class));
        estacionamentoButton = new ImageButton(texEst);
        estacionamentoButton.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH /2f);
        estacionamentoButton.setSize(100, 100);



        lutar = new TextButton("Lutar", fontParameters.getButtonStyle());
        lutar.setPosition(ScreenManager.V_WIDTH / 2f, 100, Align.center);


        stage.addActor(iracemaChar);
        stage.addActor(loiraChar);
        stage.addActor(sartoChar);
        stage.addActor(returnButton);


        Gdx.input.setInputProcessor(stage);

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                returnScreen();
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

        loiraChar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("loira selected");
                if (selected == 0) {
                    char1 = 2;
                } else if (selected == 1) {
                    char2 = 2;
                }
                selected++;
            }
        });

        sartoChar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("sarto selected");
                if (selected == 0) {
                    char1 = 3;
                } else if (selected == 1) {
                    char2 = 3;
                }
                selected++;
            }
        });

        lutar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selected = 0;
                loadGame();
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
        texture = new Texture(t);

        game.batch.begin();
        game.batch.draw(texture, 0, 0, ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH);
        game.batch.end();

        stage.act();
        stage.draw();

        if (selected >= 1) {
            if (char1 == 1) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(managerGeral.manager.get("assets/IracemaSprites/Iracema_parada_D.png", Texture.class)),
                        ScreenManager.V_WIDTH / 20f - 350, ScreenManager.V_HEIGTH / 10f + 100, 750, 800);
                game.batch.end();
            } if (char2 == 1) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(managerGeral.manager.get("assets/IracemaSprites/Iracema_parada_D.png", Texture.class)),
                        ScreenManager.V_WIDTH + 250, ScreenManager.V_HEIGTH / 10f + 100 , -750, 800);
                game.batch.end();
            }
        }

        if (selected >= 1) {
            if (char1 == 2) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(managerGeral.manager.get("assets/LoiraSprites/Loira_parada_D.png", Texture.class)),
                        ScreenManager.V_WIDTH / 20f - 350, ScreenManager.V_HEIGTH / 10f + 100, 750, 800);
                game.batch.end();
            } if (char2 == 2) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(managerGeral.manager.get("assets/LoiraSprites/Loira_parada_D.png", Texture.class)),
                        ScreenManager.V_WIDTH + 250, ScreenManager.V_HEIGTH / 10f + 100 , -750, 800);
                game.batch.end();
            }
        }

        if (selected >= 1) {
            if (char1 == 3) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(managerGeral.manager.get("assets/SartoSprites/Sarto_Parado.png", Texture.class)),
                        ScreenManager.V_WIDTH / 20f - 350, ScreenManager.V_HEIGTH / 10f + 100, 750, 800);
                game.batch.end();
            } if (char2 == 3) {
                game.batch.begin();
                game.batch.draw(new TextureRegion(managerGeral.manager.get("assets/SartoSprites/Sarto_Parado.png", Texture.class)),
                        ScreenManager.V_WIDTH + 250, ScreenManager.V_HEIGTH / 10f + 100 , -750, 800);
                game.batch.end();
            }
        }

        if (selected == 2) {
            stage.clear();
            stage.addActor(returnButton);
            stage.addActor(castelaoButton);
            stage.addActor(estacionamentoButton);
            returnButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    returnScreen();
                }
            });

            if (castelaoButton.isOver()) {
                t = "assets/backgrounds/gameBackgrounds/castelao.png";
            } else if (estacionamentoButton.isOver()) {
                t = "assets/backgrounds/gameBackgrounds/estacionamento.png";
            } else {
                t = "icon.png";
            }

            if (castelaoButton.isPressed()) {
                arena = "assets/backgrounds/gameBackgrounds/castelao.png";
                t = "assets/backgrounds/gameBackgrounds/castelao.png";
                selected++;
            }

            if (estacionamentoButton.isPressed()) {
                arena = "assets/backgrounds/gameBackgrounds/estacionamento.png";
                t = "assets/backgrounds/gameBackgrounds/estacionamento.png";
                selected++;
            }
        }

        if (selected == 3) {
            stage.addActor(lutar);
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
        managerGeral.manager.dispose();
    }

    public void returnScreen() {
        hide();
        selectionMusic.stop();
        game.setScreen(new MainMenuScreen(game));
    }

    public void loadGame() {
        hide();
        selectionMusic.stop();
        dispose();
        game.setScreen(new GameScreen(game, arena, char1, char2, 1));
    }
}
