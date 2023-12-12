package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.br.einstein.characters.Character;
import com.br.einstein.mechanics.FontParameters;

public class GameScreen implements Screen {
    private ScreenManager game;
    private OrthographicCamera gameCam;
    private Viewport viewport;
    private FontParameters fontParameters = new FontParameters();

    private boolean shouldFlip = false;

    private int round = 1;
    private float roundTime = 0;
    private Label roundLabel;
    private Stage stage;
    private boolean isGameRunning = true;
    private Sprite spritePause = new Sprite(new Texture("assets/backgrounds/pause.png"));
    private Button menuButton;
    private Button quitButton;
    private Button resumeButton;
    Texture imgB;
    Texture redGradient;
    Texture lightBrownGradient;
    Texture darkBrownGradient;
    private boolean hit;

    private int char1;
    private int char2;

    private Character character1;

    private Character character2;

    public GameScreen(ScreenManager game, String arena, int char1, int char2) {
        this.game = game;
        this.char1 = char1;
        this.char2 = char2;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);

        character1 = new Character(-180, 26, Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.B, Input.Keys.N, Input.Keys.M, char1);
        character2 = new Character(1600, 26, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.NUM_1, Input.Keys.NUMPAD_2, Input.Keys.NUMPAD_3, char2);
        character1.setSkin();
        character2.setSkin();
        imgB = new Texture(arena);
        redGradient = new Texture("assets/hpBar/redgradient.jpg");
        lightBrownGradient = new Texture ("assets/hpBar/lightbrowngradient.png");
        darkBrownGradient = new Texture("assets/hpBar/darkbrowngradient.png");
        spritePause.setAlpha(0.4f);

        menuButton = new TextButton("Menu", fontParameters.getButtonStyle());
        menuButton.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f, Align.center);

        quitButton = new TextButton("Sair", fontParameters.getButtonStyle());
        quitButton.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f - 100, Align.center);

        resumeButton = new TextButton("Retornar", fontParameters.getButtonStyle());
        resumeButton.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f + 100, Align.center);

        roundLabel = new Label("Round " + round, fontParameters.getLabelStyle50());
        roundLabel.setPosition(ScreenManager.V_WIDTH / 2f - 100, ScreenManager.V_HEIGTH / 2f);
        roundLabel.setAlignment(Align.center);
        stage = new Stage(viewport);
        stage.addActor(roundLabel);
        Gdx.input.setInputProcessor(stage);


        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hide();
                returnMenu();
            }
        });

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isGameRunning = !isGameRunning;
            }
        });

    }

    public Texture getRedGradient(){
        return redGradient;
    }
    public Texture getLightBrownGradient(){
        return lightBrownGradient;
    }
    public Texture getDarkBrownGradient(){
        return darkBrownGradient;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);
        roundTime += Gdx.graphics.getDeltaTime();

        game.batch.begin();
        ScreenUtils.clear(189/255f, 195/255f, 199/255f, 1);
        game.batch.end();

        if (character1.movementHitBox.overlaps(character2.movementHitBox)) {
            if(shouldFlip) {
                character1.setX(character1.getBeforeX() + 20);
                character2.setX(character2.getBeforeX() - 20);
            } else {
                character1.setX(character1.getBeforeX() - 20);
                character2.setX(character2.getBeforeX() + 20);
            }
        }

        if(character1.damageHitBox.overlaps(character2.damageHitBox) && (character1.isKicking || character1.isPunching) && !character2.isBlocking) {
            character2.setHealth(character2.getHealth() - 00.25f);
            character2.isHit = true;
        }

        if(character2.damageHitBox.overlaps(character1.damageHitBox) && (character2.isKicking || character2.isPunching) && !character1.isBlocking) {
            character1.setHealth(character1.getHealth() - 00.25f);
            character1.isHit = true;
        }


        if (roundTime > 2 && isGameRunning) {
            character1.update();
            character2.update();
            stage.clear();
        }

        game.batch.begin();
        game.batch.draw(imgB, 0, 0, ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH);
        game.batch.end();


        // Desenhar o retângulo
        game.batch.begin();
        game.batch.draw((roundTime > 2 && isGameRunning ? character1.characterAction() : character1.getIdle()) ,
                (shouldFlip ? 500 + character1.getX() : character1.getX()) , character1.getY() ,
                (shouldFlip ? -500: 500) , 450);
        game.batch.end();

        game.batch.begin();
        game.batch.draw((roundTime > 2 && isGameRunning? character2.characterAction() : character2.getIdle()) ,
                (shouldFlip ? character2.getX() : 500 + character2.getX()) , character2.getY(),
                (shouldFlip ? 500: -500), 450);
        game.batch.end();

        // Barra de Vida 1
        game.batch.begin();
        game.batch.draw(getLightBrownGradient(), (((float) ScreenManager.V_WIDTH*0.1f)-5), (((float) ScreenManager.V_HEIGTH*0.945f)-5), 710, 60);
        game.batch.end();

        game.batch.begin();
        game.batch.draw(getDarkBrownGradient(), ((float) ScreenManager.V_WIDTH*0.1f), (((float) ScreenManager.V_HEIGTH*0.945f)), 700, 50);
        game.batch.end();

        game.batch.begin();
        game.batch.draw(getRedGradient(), ((float) ScreenManager.V_WIDTH*0.1f), (((float) ScreenManager.V_HEIGTH*0.945f)), 700 * character1.getHealth()/100, 50);
        game.batch.end();

        // Barra de Vida 2
        game.batch.begin();
        game.batch.draw(getLightBrownGradient(), (((float) ScreenManager.V_WIDTH*0.9f)+5), (((float) ScreenManager.V_HEIGTH*0.945f)-5), -710, 60);
        game.batch.end();

        game.batch.begin();
        game.batch.draw(getDarkBrownGradient(), ((float) ScreenManager.V_WIDTH*0.9f), (((float) ScreenManager.V_HEIGTH*0.945f)), -700, 50);
        game.batch.end();

        game.batch.begin();
        game.batch.draw(getRedGradient(), ((float) ScreenManager.V_WIDTH*0.9f), (((float) ScreenManager.V_HEIGTH*0.945f)), -700 * character2.getHealth()/100, 50);
        game.batch.end();

        stage.draw();
        stage.act();

        if (character1.getX() > character2.getX()) {
            shouldFlip = true;
        } else {
            shouldFlip = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && roundTime > 2) {
            isGameRunning = !isGameRunning;
        }

        if (!isGameRunning) {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            game.batch.begin();
            spritePause.draw(game.batch);
            game.batch.end();

            stage.addActor(resumeButton);
            stage.addActor(menuButton);
            stage.addActor(quitButton);
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
        game.batch.dispose();
    }

    public void returnMenu() {
        game.setScreen(new MainMenuScreen(game));
    }

    public void quit() {
        Gdx.app.exit();
    }
}
