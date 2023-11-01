package com.br.einstein.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.br.einstein.MyGdxGame;

public class Character {
    private Vector2 velocity;
    private float before;
    protected float x;
    protected float y;

    protected int left;
    protected int right;
    protected int space;
    private final long startTime = System.currentTimeMillis();
    private float elapsedTime;
    private float lastTimeRight;
    private float lastTimeLeft;
    private float lastTimeDash;
    private int jump = 1;

    public Character(float x, float y, int left, int right, int space) {
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
        this.space = space;
        velocity = new Vector2(0, -1); // Define a velocidade inicial como -1 na direção Y (gravidade para baixo).
    }

    public void update() {
        elapsedTime = ((float)(System.currentTimeMillis() - startTime))/1000;
        if (jump == 1) {
            if (!Gdx.input.isKeyJustPressed(space)) {
                velocity.y -= 9.8f * 35;
            } else if (Gdx.input.isKeyJustPressed(space)) {
                if (velocity.y <= 0) {
                    velocity.y = 1;
                }
                velocity.y += 9.8f * 35;
                y += velocity.y * Gdx.graphics.getDeltaTime();
                jump++;
            }
        } else {
            if (!Gdx.input.isKeyJustPressed(space)) {
                velocity.y -= 9.8f;
            }
            if (y < 25) {
                jump = 1;
            }
        }
        //walkRight
        if (Gdx.input.isKeyPressed(right)) {
            if (x < Gdx.graphics.getWidth() - 350 && MyGdxGame.isFullScreenStatus()) {
                before = x;
                x += 250 * Gdx.graphics.getDeltaTime();
            } else if(x < Gdx.graphics.getWidth() + 350 && !MyGdxGame.isFullScreenStatus()) {
                before = x;
                x+= 250 * Gdx.graphics.getDeltaTime();
            }
        }
        // dashRight
        if (Gdx.input.isKeyJustPressed(right)){
            if((elapsedTime-lastTimeRight)<0.25f && elapsedTime-lastTimeDash>0.75f){
                x += 10000 * Gdx.graphics.getDeltaTime();
                lastTimeDash=elapsedTime;
                if (x >= Gdx.graphics.getWidth() - 350 && MyGdxGame.isFullScreenStatus()) {
                    x = Gdx.graphics.getWidth() - 350;
                } else if (x >= Gdx.graphics.getWidth() && !MyGdxGame.isFullScreenStatus()) {
                    x = Gdx.graphics.getWidth() + 350;
                }
            }
            lastTimeRight=elapsedTime;
        }
        // walkLeft
        if (Gdx.input.isKeyPressed(left)) {
            if (x > -135 && MyGdxGame.isFullScreenStatus()) {
                before = x;
                x -= 250 * Gdx.graphics.getDeltaTime();
            } else if (x > -150 && !MyGdxGame.isFullScreenStatus()) {
                before = x;
                x -= 250 * Gdx.graphics.getDeltaTime();
            }
        }
        // dashLeft
        if (Gdx.input.isKeyJustPressed(left)){
            if((elapsedTime-lastTimeLeft)<0.25f && elapsedTime-lastTimeDash>0.75f){
                x -= 10000 * Gdx.graphics.getDeltaTime();
                lastTimeDash=elapsedTime;
                if (x <= -150 && !MyGdxGame.isFullScreenStatus()) {
                    x = -150;
                } else if (x <= -135 && MyGdxGame.isFullScreenStatus()) {
                    x = -135;
                }
            }
            lastTimeLeft=elapsedTime;
        }
        if (y >= 25) {
            y += velocity.y * Gdx.graphics.getDeltaTime();
        }
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getBefore() {
        return this.before;
    }

}
