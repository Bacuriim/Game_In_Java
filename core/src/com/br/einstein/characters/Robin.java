package com.br.einstein.characters;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Robin extends ApplicationAdapter {
        private Rectangle rectangle;
        private Vector2 velocity;

        public Robin() {}
        public Robin(float x, float y, float width, float height) {
            rectangle = new Rectangle(x, y, width, height);
            velocity = new Vector2(0, -1); // Define a velocidade inicial como -1 na direção Y (gravidade para baixo).
        }

        public void update(float deltaTime) {
            velocity.y -= 9.8f * deltaTime; // Ajuste o valor 9.8f para a aceleração da gravidade desejada.

            rectangle.x += velocity.x * deltaTime;
            rectangle.y += velocity.y * deltaTime;
        }

        public Rectangle getRectangle() {
            return rectangle;
        }
}
