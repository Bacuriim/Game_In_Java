package com.br.einstein.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGTH = 208;
    public SpriteBatch batch;
    private boolean fullScreenStatus;



    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F11) && fullScreenStatus) {
            Gdx.graphics.setWindowedMode(1200,800);
            fullScreenStatus = false;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.F11) && !fullScreenStatus) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            fullScreenStatus = true;
        }
    }
}
