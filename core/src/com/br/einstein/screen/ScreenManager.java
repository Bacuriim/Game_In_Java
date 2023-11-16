package com.br.einstein.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.br.einstein.characters.Character;

public class ScreenManager extends Game {
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGTH = 1080;

    public SpriteBatch batch;

    protected static boolean fullScreenStatus = true;

    public static boolean isFullScreenStatus() {
        return fullScreenStatus;
    }



    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
    }


    @Override
    public void render() {
        super.render();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F11) && fullScreenStatus) {
            Gdx.graphics.setWindowedMode(1366,768);
            fullScreenStatus = false;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.F11) && !fullScreenStatus) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            fullScreenStatus = true;
        }

    }
    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}


