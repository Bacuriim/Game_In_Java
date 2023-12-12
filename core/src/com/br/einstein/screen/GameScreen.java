package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
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

    private String arena;
    private int round = 1;
    private float roundTime = 0;
    private int timer = 99;
    private float timeCount = 0;
    private Label timerLabel;
    private Label roundLabel;
    private float winTime = 0;
    private Label winLabel;
    private Stage stage;
    private boolean isGameRunning = true;
    private Sprite spritePause = new Sprite(new Texture("assets/backgrounds/pause.png"));
    private Button menuButton;
    private Button quitButton;
    private Button resumeButton;
    private Music fightMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/musics/Fight_Music.mp3"));
    private Texture imgB;
    private Texture redGradient;
    private Texture lightBrownGradient;
    private Texture darkBrownGradient;
    private Label p1_hp;
    private Label p2_hp;
    private boolean hit;
    Texture blackTransparent;
    static boolean pauseStatus=false;

    private int char1;
    private int char2;

    private Character character1;

    private Character character2;
    private Label p1;
    private Label p2;
    Sound Round1 = Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/Round 1.mp3"));
    Sound Round2 = Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/Round 2.mp3"));
    Sound FinalRound = Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/Final Round.mp3"));
    Sound Selection = Gdx.audio.newSound(Gdx.files.internal("assets/Sounds/Selection.mp3"));
    public GameScreen(ScreenManager game, String arena, int char1, int char2, int round) {
        this.game = game;
        this.char1 = char1;
        this.char2 = char2;
        this.arena = arena;
        this.round = round;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);

        character1 = new Character(-180, 26, Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.B, Input.Keys.N, Input.Keys.M, char1);
        character2 = new Character(1600, 26, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.NUM_1, Input.Keys.NUMPAD_2, Input.Keys.NUMPAD_3, char2);
        character1.setSkin();
        character2.setSkin();
        p1 = new Label("P1", fontParameters.getLabelStyle50R());
        p2 = new Label("P2", fontParameters.getLabelStyle50B());

        p1_hp = new Label("P1", fontParameters.getLabelStyle50R());
        p2_hp = new Label("P2", fontParameters.getLabelStyle50B());

        imgB = new Texture(arena);
        redGradient = new Texture("assets/hpBar/redgradient.jpg");
        lightBrownGradient = new Texture ("assets/hpBar/lightbrowngradient.png");
        darkBrownGradient = new Texture("assets/hpBar/darkbrowngradient.png");
        spritePause.setAlpha(0.4f);

        fightMusic.play();
        fightMusic.setLooping(true);
        fightMusic.setVolume(0.2f);

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
        switch(round){
            case 1:
                Round1.play(0.8f);
                break;
            case 2:
                Round2.play(0.8f);
                break;
            case 3:
                FinalRound.play(0.8f);
                break;
        }
        Gdx.input.setInputProcessor(stage);


        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Selection.play(2.0f);
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
                Selection.play(1.0f);
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

        String s = String.valueOf(timer);
        timerLabel = new Label(s, fontParameters.getLabelStyle50());
        timerLabel.setPosition(ScreenManager.V_WIDTH / 2f, 1040, Align.center);


        if (roundTime > 2 && isGameRunning) {
            character1.update();
            character2.update();
            stage.clear();
            stage.addActor(timerLabel);
            timeCount += delta;
        }

        if (timeCount >= 1.5f && !(timer == 0)) {
            timer--;
            timeCount = 0;
        }

        if (timer == 0) {
            if (character1.getHealth() > character2.getHealth()) {
                character1.setWins(character1.getWins() + 1);
                nextRound();
            } else if (character1.getHealth() < character2.getHealth()) {
                character2.setWins2(character2.getWins2() + 1);
                nextRound();
            } else {
                fightMusic.stop();
                game.setScreen(new GameScreen(this.game, this.arena, this.char1, this.char2, this.round));
            }
        }

        p1.setPosition(character1.getX() + 250, character1.getY() + 500, Align.center);
        p2.setPosition(character2.getX() + 250, character2.getY() + 500, Align.center);
        stage.addActor(p1);
        stage.addActor(p2);

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

        p1_hp.setPosition(((float) ScreenManager.V_WIDTH*0.1f) - 40, (((float) ScreenManager.V_HEIGTH*0.945f) + 30), Align.center);

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

        p2_hp.setPosition(((float) ScreenManager.V_WIDTH*0.9f) + 40, (((float) ScreenManager.V_HEIGTH*0.945f) + 30), Align.center);
        stage.addActor(p1_hp);
        stage.addActor(p2_hp);

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

        if (character1.getWins() == 2 || character2.getWins2() == 2) {
            fightMusic.stop();
            winTime += Gdx.graphics.getDeltaTime();
            winLabel = new Label("", fontParameters.getLabelStyle50());
            winLabel.setPosition(ScreenManager.V_WIDTH / 2f, ScreenManager.V_HEIGTH / 2f);
            winLabel.setAlignment(Align.center);

            if (winTime < 2) {
                if (character1.getWins() == 2) {
                    winLabel.setText("Jogador 1 Venceu");
                    stage.addActor(winLabel);
                } else if (character2.getWins2() == 2){
                    winLabel.setText("Jogador 2 Venceu");
                    stage.addActor(winLabel);
                }
                stage.draw();
            } else {
                hide();
                winTime = 0;
                character1.setWins(0);
                character2.setWins2(0);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                returnMenu();
            }
        } else {
            winTime = 0;
            if (character1.getHealth() == 0) {
                character2.setWins2(character2.getWins2() + 1);
                nextRound();
            } else if (character2.getHealth() == 0) {
                character1.setWins(character1.getWins() + 1);
                nextRound();
            }
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

    public void nextRound() {
        if (character1.getWins() == 1 && character2.getWins2() == 1) {
            hide();
            fightMusic.stop();
            game.setScreen(new GameScreen(this.game, this.arena, this.char1, this.char2, 3));
        } else if ((character1.getWins() == 1 || character2.getWins2() == 1) && round != 3) {
            hide();
            fightMusic.stop();
            game.setScreen(new GameScreen(this.game, this.arena, this.char1, this.char2, 2));
        }
    }
}
