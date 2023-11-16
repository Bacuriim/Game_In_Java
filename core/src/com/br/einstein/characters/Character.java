package com.br.einstein.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.br.einstein.screen.ScreenManager;

public class Character {
    private Vector2 velocity;
    private float before;
    protected float x;
    protected float y;

    protected int left;
    protected int right;
    protected int space;
    protected int punch;
    protected int kick;
    private final long startTime = System.currentTimeMillis();
    private float elapsedTime;
    private float lastTimeRight;
    private float lastTimeLeft;
    private float lastTimeDash;
    private int jump = 1;
    private final int characterId;
    private Texture imagePunchE;
    private Texture imagePunchD;
    private Texture imageKickE;
    private Texture imageKickD;
    private Texture imageJumpingE;
    private Texture imageJumpingD;
    private Texture imageBaseE;
    private Texture imageBaseD;

    public Character(float x, float y, int left, int right, int space, int punch, int kick, int characterId) {
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
        this.space = space;
        this.punch = punch;
        this.kick = kick;
        this.characterId = characterId;
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
            if (y <= 25) {
                jump = 1;
            }
        }
        //walkRight
        if (Gdx.input.isKeyPressed(right)) {
            if (x < Gdx.graphics.getWidth() - 350 && ScreenManager.isFullScreenStatus()) {
                before = x;
                x += 250 * Gdx.graphics.getDeltaTime();
            } else if(x < Gdx.graphics.getWidth() + 350 && !ScreenManager.isFullScreenStatus()) {
                before = x;
                x+= 250 * Gdx.graphics.getDeltaTime();
            }
        }
        // dashRight
        if (Gdx.input.isKeyJustPressed(right)){
            if((elapsedTime-lastTimeRight) < 0.25f && elapsedTime-lastTimeDash > 0.75f){
                x += 10000 * Gdx.graphics.getDeltaTime();
                lastTimeDash=elapsedTime;
                if (x >= Gdx.graphics.getWidth() - 350 && ScreenManager.isFullScreenStatus()) {
                    x = Gdx.graphics.getWidth() - 350;
                } else if (x >= Gdx.graphics.getWidth() && !ScreenManager.isFullScreenStatus()) {
                    x = Gdx.graphics.getWidth() + 350;
                }
            }
            lastTimeRight=elapsedTime;
        }
        // walkLeft
        if (Gdx.input.isKeyPressed(left)) {
            if (x > -135 && ScreenManager.isFullScreenStatus()) {
                before = x;
                x -= 250 * Gdx.graphics.getDeltaTime();
            } else if (x > -150 && !ScreenManager.isFullScreenStatus()) {
                before = x;
                x -= 250 * Gdx.graphics.getDeltaTime();
            }
        }
        // dashLeft
        if (Gdx.input.isKeyJustPressed(left)){
            if((elapsedTime-lastTimeLeft) < 0.25f && elapsedTime-lastTimeDash > 0.75f){
                x -= 10000 * Gdx.graphics.getDeltaTime();
                lastTimeDash=elapsedTime;
                if (x <= -150 && !ScreenManager.isFullScreenStatus()) {
                    x = -150;
                } else if (x <= -135 && ScreenManager.isFullScreenStatus()) {
                    x = -135;
                }
            }
            lastTimeLeft=elapsedTime;
        }
        if (y >= 25) {
            y += velocity.y * Gdx.graphics.getDeltaTime();
        }
    }

    public Texture getImage() {
        if (x < before) {
            return characterAction("E");
        } else if (x > before) {
            return characterAction("D");
        }
        return null;
    }

    private Texture characterAction(String lado) {
       if (Gdx.input.isKeyJustPressed(punch)) {
            if (lado == "E") {
                return imagePunchE;
            } else if(lado == "D") {
                return imagePunchD;
            }
       }
       if (Gdx.input.isKeyJustPressed(kick)) {
           if (lado == "E") {
               return imageKickE;
           } else if (lado == "D") {
               return imageKickD;
           }
       }
       if (Gdx.input.isKeyJustPressed(space)) {
           if (lado == "E") {
               return imageJumpingE;
           } else if (lado == "D") {
               return imageJumpingD;
           }
       }
       if (!Gdx.input.isKeyJustPressed(punch) && !Gdx.input.isKeyJustPressed(kick) && !Gdx.input.isKeyJustPressed(space)) {
           if (lado == "E") {
               return imageBaseE;
           } else if (lado == "D") {
               return imageBaseD;
           }
       }
       return null;
    }

    public void setSkin() {
        switch (characterId) {
            case 1:
                imageBaseD = new Texture("Iracema_parada_D.png");
                imageBaseE = new Texture("Iracema_parada_E.png");
                imageJumpingD = new Texture("Iracema_pulo_D.png");
                imageJumpingE = new Texture("Iracema_pulo_E.png");
                imagePunchE = new Texture("Iracema_block_E.png");
                imagePunchD = new Texture("Iracema_block_D.png");
                imageKickD = new Texture("Iracema_chute_D.png");
                imageKickE = new Texture("Iracema_chute_E.png");
                break;
            case 2:
                imageBaseD = new Texture("Iracema_parada_D.png");
                imageBaseE = new Texture("Iracema_parada_E.png");
                imageJumpingD = new Texture("Iracema_pulo_D.png");
                imageJumpingE = new Texture("Iracema_pulo_E.png");
                imagePunchE = new Texture("Iracema_block_E.png");
                imagePunchD = new Texture("Iracema_block_D.png");
                imageKickD = new Texture("Iracema_chute_D.png");
                imageKickE = new Texture("Iracema_chute_E.png");
                break;
            default:
                System.out.println("Não setou a skin!!!");
                break;
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
