package com.br.einstein;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.br.einstein.characters.FallingCircle;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgE;
	Texture imgD;

	private FallingCircle fallingCircle = new FallingCircle(700, 100);

	public Texture getImage() {
		if (fallingCircle.getCircle().x < fallingCircle.getBefore()) {
			return imgE;
		} else if (fallingCircle.getCircle().x > fallingCircle.getBefore()) {
			return imgD;
		}
		return null;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		imgE = new Texture("policialE.png");
		imgD = new Texture("policialD.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(10, 10, 10, 10);

		fallingCircle.update();

		// Desenhar o retângulo
		batch.begin();
		batch.draw(getImage() , fallingCircle.getCircle().x , fallingCircle.getCircle().y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		getImage().dispose();
	}
}
