package com.br.einstein;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.br.einstein.characters.FallingCircle;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgE;
	Texture imgD;
	Texture imgB;

	private boolean fullScreenStatus = true;
	Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

	private FallingCircle fallingCircle = new FallingCircle(700, 100);

    public Texture getImage() {
		if (fallingCircle.getCircle().x < fallingCircle.getBefore()) {
			return imgE;
		} else if (fallingCircle.getCircle().x > fallingCircle.getBefore()) {
			return imgD;
		}
		return null;
	}

	public int getWidthScreen(boolean fullScreenStatus) {
		if (fullScreenStatus) {
			return Gdx.graphics.getWidth();
		}
		return 2000;
	}

	public int getHeightScreen(boolean fullScreenStatus) {
		if (fullScreenStatus) {
			return Gdx.graphics.getHeight();
		}
		return 1200;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		imgE = new Texture("policialE.png");
		imgD = new Texture("policialD.png");
		imgB = new Texture("1_empxo5xvgaefru0-13999131.png");
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && fullScreenStatus) {
			Gdx.graphics.setWindowedMode(1200,800);
			fullScreenStatus = false;
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !fullScreenStatus) {
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			fullScreenStatus = true;
		}
		batch.begin();
		ScreenUtils.clear(189/255f, 195/255f, 199/255f, 1);
		batch.draw(imgB, 0, 0, getWidthScreen(fullScreenStatus), getHeightScreen(fullScreenStatus));
		batch.end();

		fallingCircle.update();


		// Desenhar o retângulo
		batch.begin();
		batch.draw(getImage() , fallingCircle.getCircle().x , fallingCircle.getCircle().y , 500 , 450);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		getImage().dispose();
	}

}
