package com.br.einstein.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public class FallingCircle {
    private Circle circle;
    private Vector2 velocity;
    private float before;

    private float deltaTime = Gdx.graphics.getDeltaTime();

    public static int jump = 1;

    public FallingCircle(float x, float y) {
        circle = new Circle(x, y, 40);
        velocity = new Vector2(0, -1); // Define a velocidade inicial como -1 na direção Y (gravidade para baixo).
    }

    public void update() {
        if (jump == 1) {
            if (!Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                velocity.y -= 9.8f * 35;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (velocity.y <= 0) {
                    velocity.y = 1;
                }
                velocity.y += 9.8f * 35;
                circle.y += velocity.y * deltaTime;
                jump++;
            }
        } else {
            if (!Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                velocity.y -= 9.8f;
            }
            if (circle.y <= 100) {
                jump = 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            before = circle.x;
            circle.x += 250 * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            before = circle.x;
            circle.x -= 250 * deltaTime;
        }
        circle.x += velocity.x * deltaTime;

        if (circle.y > 100) {
            circle.y += velocity.y * deltaTime;
        }

    }

    public Circle getCircle() {
        return circle;
    }
    public float getBefore() {
        return this.before;
    }
}
