package com.br.einstein.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.br.einstein.characters.Character;

public class GameScreen implements Screen {
    private ScreenManager game;
    private OrthographicCamera gameCam;
    private Viewport viewport;


    public SpriteBatch batch;
    public Texture imgE;
    public Texture imgD;
    public Texture imgB;

    private Character character1 = new Character(700, 25, Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.B, Input.Keys.N,1);

    private Character character2 = new Character(700, 25, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.INSERT, Input.Keys.HOME, 2);

    public GameScreen(ScreenManager game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, gameCam);
        batch = new SpriteBatch();
        character1.setSkin();
        character2.setSkin();
        imgB = new Texture("1_empxo5xvgaefru0-13999131.png");
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
        batch.draw(character1.getImage() , character1.getX() , character1.getY() , 500 , 450);
        batch.end();

        batch.begin();
        batch.draw(character2.getImage() , character2.getX() , character2.getY() , 500 , 450);
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
        batch.dispose();
        character1.getImage().dispose();
        character2.getImage().dispose();
    }
}
