package com.br.einstein;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.br.einstein.characters.Character;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture imgE;
	Texture imgD;
	Texture imgB;

	private boolean inGame = false;

	protected static boolean fullScreenStatus = true;

	private Character character1 = new Character(700, 25 , Input.Keys.A , Input.Keys.D , Input.Keys.SPACE);

	private Character character2 = new Character(700 , 25 , Input.Keys.LEFT , Input.Keys.RIGHT , Input.Keys.UP);

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

	public static int getWidthScreen(boolean fullScreenStatus) {
		if (fullScreenStatus) {
			return Gdx.graphics.getWidth();
		}
		return 2000;
	}

	public static int getHeightScreen(boolean fullScreenStatus) {
		if (fullScreenStatus) {
			return Gdx.graphics.getHeight();
		}
		return 1200;
	}

	public static boolean isFullScreenStatus() {
		return fullScreenStatus;
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
		if(Gdx.input.isKeyJustPressed(Input.Keys.F11) && fullScreenStatus) {
			Gdx.graphics.setWindowedMode(1200,800);
			fullScreenStatus = false;
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.F11) && !fullScreenStatus) {
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			fullScreenStatus = true;
		}
		batch.begin();
		ScreenUtils.clear(189/255f, 195/255f, 199/255f, 1);
		batch.draw(imgB, 0, 0, getWidthScreen(fullScreenStatus), getHeightScreen(fullScreenStatus));
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
	}

	@Override
	public void dispose () {
		batch.dispose();
		getImage1().dispose();
		getImage2().dispose();
	}

}
