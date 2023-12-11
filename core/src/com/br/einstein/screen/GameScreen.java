package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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


    Texture imgE;
    Texture imgD;
    Texture imgB;
    Texture redGradient;
    Texture lightBrownGradient;
    Texture darkBrownGradient;
    Texture blackTransparent;
    static boolean pauseStatus=false;

    private Character character1 = new Character(1600, 26, Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.B, Input.Keys.N,1);

    private Character character2 = new Character(-180, 26, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.NUMPAD_2, Input.Keys.NUMPAD_3, 2);

    public GameScreen(ScreenManager game, String arena) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);
        character1.setSkin();
        character2.setSkin();
        imgB = new Texture(arena);
        redGradient = new Texture("assets/hpBar/redgradient.jpg");
        lightBrownGradient = new Texture ("assets/hpBar/lightbrowngradient.png");
        darkBrownGradient = new Texture("assets/hpBar/darkbrowngradient.png");

        roundLabel = new Label("Round " + round, fontParameters.getLabelStyle50());
        roundLabel.setPosition(ScreenManager.V_WIDTH / 2f - 100, ScreenManager.V_HEIGTH / 2f);
        roundLabel.setAlignment(Align.center);
        stage = new Stage(viewport);
        stage.addActor(roundLabel);


    }

    public Texture getImage1() {
        if (character1.getX() < character1.getBefore()) {
            return imgE;
        } else if (character1.getX() > character1.getBefore()) {
            return imgD;
        }
        return null;
    }

    public Texture getImage2() {
        if (character2.getX() < character2.getBefore()) {
            return imgE;
        } else if (character2.getX() > character2.getBefore()) {
            return imgD;
        }
        return null;
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
        roundTime += Gdx.graphics.getDeltaTime();

        game.batch.begin();
        ScreenUtils.clear(189/255f, 195/255f, 199/255f, 1);
        game.batch.end();


        if (roundTime > 3) {
            character1.update();
            character2.update();
            stage.clear();
        }

        game.batch.begin();
        game.batch.draw(imgB, 0, 0, ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH);
        game.batch.end();


        // Desenhar o retângulo
        game.batch.begin();
        game.batch.draw((roundTime > 3 ? character1.characterAction() : character1.idle) ,
                (shouldFlip ? 500 + character1.getX() : character1.getX()) , character1.getY() ,
                (shouldFlip ? -500: 500) , 450);
        game.batch.end();

        game.batch.begin();
        game.batch.draw((roundTime > 3 ? character2.characterAction() : character2.idle) ,
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
}
