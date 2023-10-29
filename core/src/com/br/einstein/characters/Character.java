package com.br.einstein.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public class Character {
    private Vector2 velocity;
    private float before;
    protected float x;
    protected float y;
    private final long startTime = System.currentTimeMillis();
    private float elapsedTime;
    private float lastTimeRight;
    private float lastTimeLeft;
    private float lastTimeDash;
    private static int jump = 1;

    public Character(float x, float y) {
        this.x = x;
        this.y = y;
        velocity = new Vector2(0, -1); // Define a velocidade inicial como -1 na direção Y (gravidade para baixo).
    }

    public void update() {
        elapsedTime = ((float)(System.currentTimeMillis() - startTime))/1000;
        if (jump == 1) {
            if (!Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                velocity.y -= 9.8f * 35;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (velocity.y <= 0) {
                    velocity.y = 1;
                }
                velocity.y += 9.8f * 35;
                y += velocity.y * Gdx.graphics.getDeltaTime();
                jump++;
            }
        } else {
            if (!Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                velocity.y -= 9.8f;
            }
            if (y <= 100) {
                jump = 1;
            }
        }
        //walkRight
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            before = x;
            x += 250 * Gdx.graphics.getDeltaTime();
        }
        // dashRight
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D)){
            if((elapsedTime-lastTimeRight)<0.25f && elapsedTime-lastTimeDash>0.75f){
                x += 5000 * Gdx.graphics.getDeltaTime();
                lastTimeDash=elapsedTime;
            }
            lastTimeRight=elapsedTime;
        }
        // walkLeft
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            before = x;
            x -= 250 * Gdx.graphics.getDeltaTime();
        }
        // dashLeft
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A)){
            if((elapsedTime-lastTimeLeft)<0.25f && elapsedTime-lastTimeDash>0.75f){
                x -= 5000 * Gdx.graphics.getDeltaTime();
                lastTimeDash=elapsedTime;
            }
            lastTimeLeft=elapsedTime;
        }
        x += velocity.x * Gdx.graphics.getDeltaTime();

        if (y > 100) {
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
