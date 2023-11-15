package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.br.einstein.MyGdxGame;
import com.br.einstein.characters.Character;

public class GameScreen implements Screen {
    private ScreenManager game;
    private OrthographicCamera gameCam;
    private Viewport viewport;


    SpriteBatch batch;
    Texture imgE;
    Texture imgD;
    Texture imgB;
    Texture redGradient;
    Texture lightBrownGradient;
    Texture darkBrownGradient;

    private Character character1 = new Character(700, 25 , Input.Keys.A , Input.Keys.D , Input.Keys.SPACE);

    private Character character2 = new Character(700 , 25 , Input.Keys.LEFT , Input.Keys.RIGHT , Input.Keys.UP);

    public GameScreen(ScreenManager game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);
        batch = new SpriteBatch();
        imgE = new Texture("policialE.png");
        imgD = new Texture("policialD.png");
        imgB = new Texture("1_empxo5xvgaefru0-13999131.png");
        redGradient = new Texture("redgradient.jpg");
        lightBrownGradient = new Texture ("lightbrowngradient.png");
        darkBrownGradient = new Texture("darkbrowngradient.png");

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
        batch.begin();
        ScreenUtils.clear(189/255f, 195/255f, 199/255f, 1);
        batch.end();

        character1.update();
        character2.update();


        // Desenhar o retângulo
        batch.begin();
        batch.draw(getImage1() , character1.getX() , character1.getY() , 500 , 450);
        batch.end();

        batch.begin();
        batch.draw(getImage2() , character2.getX() , character2.getY() , 500 , 450);
        batch.end();

        // Barra de Vida 1
        batch.begin();
        batch.draw(getLightBrownGradient(), (((float) ScreenManager.V_WIDTH*0.1f)-5), (((float) ScreenManager.V_HEIGTH*0.945f)-5), 710, 60);
        batch.end();

        batch.begin();
        batch.draw(getDarkBrownGradient(), ((float) ScreenManager.V_WIDTH*0.1f), (((float) ScreenManager.V_HEIGTH*0.945f)), 700, 50);
        batch.end();

        batch.begin();
        batch.draw(getRedGradient(), ((float) ScreenManager.V_WIDTH*0.1f), (((float) ScreenManager.V_HEIGTH*0.945f)), 700 * character1.getHealth()/100, 50);
        batch.end();

        // Barra de Vida 2
        batch.begin();
        batch.draw(getLightBrownGradient(), (((float) ScreenManager.V_WIDTH*0.9f)+5), (((float) ScreenManager.V_HEIGTH*0.945f)-5), -710, 60);
        batch.end();

        batch.begin();
        batch.draw(getDarkBrownGradient(), ((float) ScreenManager.V_WIDTH*0.9f), (((float) ScreenManager.V_HEIGTH*0.945f)), -700, 50);
        batch.end();

        batch.begin();
        batch.draw(getRedGradient(), ((float) ScreenManager.V_WIDTH*0.9f), (((float) ScreenManager.V_HEIGTH*0.945f)), -700 * character2.getHealth()/100, 50);
        batch.end();
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
    }
}
