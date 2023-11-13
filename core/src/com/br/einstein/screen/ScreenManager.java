package com.br.einstein.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.br.einstein.characters.Character;

public class ScreenManager extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGTH = 208;

    SpriteBatch batch;

    private boolean inGame = false;

    protected static boolean fullScreenStatus = true;

    public boolean getInGame() {
        return this.inGame;
    }

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
            Gdx.graphics.setWindowedMode(1200,800);
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
