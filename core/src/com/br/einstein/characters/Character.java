package com.br.einstein.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.br.einstein.screen.GameScreen;
import com.br.einstein.screen.ScreenManager;

public class Character {
    private Vector2 velocity;
    public float beforeX;
    public float beforeY;
    protected float x;
    protected float y;
    private float health;
    protected int left;
    protected int right;
    protected int space;
    protected int punch;
    protected int kick;
    protected int block;
    private final long startTime = System.currentTimeMillis();
    private float elapsedTime;
    private float lastTimeRight;
    private float lastTimeLeft;
    private float lastTimeDash;
    private int jump = 1;
    private final int characterId;
    private static int wins = 0;
    private static int wins2 = 0;
    private Texture punchImage;
    private Texture kickImage;
    private Texture walkImage;
    private Animation<TextureRegion> punchAnimation;
    private Animation<TextureRegion> kickAnimation;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;
    public boolean isPunching = false;
    public boolean isKicking = false;
    public boolean isBlocking = false;
    public boolean isHit = false;
    private TextureRegion currentPunchFrame;
    private TextureRegion currentWalkFrame;
    private TextureRegion currentKickFrame;
    private TextureRegion blocking;
    private TextureRegion idle;
    private TextureRegion jumping;
    private TextureRegion imunity;
    private TextureRegion dead;
    public Rectangle damageHitBox = new Rectangle();
    public Rectangle movementHitBox = new Rectangle();

    public Character(float x, float y, int left, int right, int space, int punch, int kick, int block, int characterId) {
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
        this.space = space;
        this.health = 100.0f;
        this.punch = punch;
        this.kick = kick;
        this.block = block;
        this.characterId = characterId;
        velocity = new Vector2(0, -1); // Define a velocidade inicial como -1 na direção Y (gravidade para baixo).

        if (characterId == 1) {
            punchImage = new Texture("assets/IracemaSprites/Iracema_soco_animation_D.png");
            TextureRegion[] [] tmp = TextureRegion.split(punchImage, 270, 270);
            TextureRegion[] punchFrames = new TextureRegion[9];
            int k = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    punchFrames[k++] = tmp[i][j];
                }
            }
            k = 0;
            punchAnimation = new Animation<TextureRegion>(0.025f, punchFrames);


            //walk aniamtion
            walkImage = new Texture("assets/IracemaSprites/Iracema_walking_animation.png");

            TextureRegion[] [] walkMat = TextureRegion.split(walkImage, 270, 270);
            TextureRegion[] walkFrames =  new TextureRegion[3];
            for (int i = 0; i < 3; i++) {
                walkFrames[k++] = walkMat[0] [i];
            }
            k = 0;
            walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);


            //kick animation
            kickImage = new Texture("assets/IracemaSprites/iracema_chutando.png");

            TextureRegion[] [] kickMat = TextureRegion.split(kickImage, 270, 270);
            TextureRegion[] kickFrames = new TextureRegion[4];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    kickFrames[k++] = kickMat[i] [j];
                }
            }
            k = 0;
            kickAnimation = new Animation<TextureRegion>(0.12f, kickFrames);

        } else if (characterId == 2) {
            punchImage = new Texture("assets/LoiraSprites/Loira_Socando_D.png");
            TextureRegion[] [] tmp = TextureRegion.split(punchImage, 270, 270);
            TextureRegion[] punchFrames = new TextureRegion[4];
            int k = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    punchFrames[k++] = tmp[i][j];
                }
            }
            k = 0;
            punchAnimation = new Animation<TextureRegion>(0.075f, punchFrames);


            //walk aniamtion
            walkImage = new Texture("assets/LoiraSprites/Loira_Andando_D.png");

            TextureRegion[] [] walkMat = TextureRegion.split(walkImage, 270, 270);
            TextureRegion[] walkFrames =  new TextureRegion[6];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    walkFrames[k++] = walkMat[i] [j];
                }
            }
            k = 0;
            walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);


            //kick animation
            kickImage = new Texture("assets/LoiraSprites/Loira_Chutando_D.png");

            TextureRegion[] [] kickMat = TextureRegion.split(kickImage, 270, 270);
            TextureRegion[] kickFrames = new TextureRegion[6];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    kickFrames[k++] = kickMat[i] [j];
                }
            }
            k = 0;
            kickAnimation = new Animation<TextureRegion>(0.12f, kickFrames);

        } else if (characterId == 3) {
            punchImage = new Texture("assets/SartoSprites/Sarto_Socando.png");
            TextureRegion[] [] tmp = TextureRegion.split(punchImage, 270, 270);
            TextureRegion[] punchFrames = new TextureRegion[4];
            int k = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    punchFrames[k++] = tmp[i][j];
                }
            }
            k = 0;
            punchAnimation = new Animation<TextureRegion>(0.075f, punchFrames);


            //walk aniamtion
            walkImage = new Texture("assets/SartoSprites/Sarto_Andando.png");

            TextureRegion[] [] walkMat = TextureRegion.split(walkImage, 270, 270);
            TextureRegion[] walkFrames =  new TextureRegion[6];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    walkFrames[k++] = walkMat[i] [j];
                }
            }
            k = 0;
            walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);


            //kick animation
            kickImage = new Texture("assets/SartoSprites/Sarto_Chutando.png");

            TextureRegion[] [] kickMat = TextureRegion.split(kickImage, 270, 270);
            TextureRegion[] kickFrames = new TextureRegion[4];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    kickFrames[k++] = kickMat[i] [j];
                }
            }
            k = 0;
            kickAnimation = new Animation<TextureRegion>(0.12f, kickFrames);

        }
    }

    public void update() {
        elapsedTime = ((float)(System.currentTimeMillis() - startTime))/1000;

        if (jump == 1 && !isBlocking) {
            if (!Gdx.input.isKeyJustPressed(space)) {
                velocity.y -= 9.8f * 70;
            } else if (Gdx.input.isKeyJustPressed(space)) {
                if (velocity.y <= 0) {
                    velocity.y = 1;
                }
                velocity.y += 9.8f * 80;
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
        if (Gdx.input.isKeyPressed(right) && (!isPunching && !isKicking && !isBlocking)) {
            if (x < Gdx.graphics.getWidth() - 350 && ScreenManager.isFullScreenStatus()) {
                beforeX = x;
                x += 250 * Gdx.graphics.getDeltaTime();
            } else if(x < Gdx.graphics.getWidth() + 350 && !ScreenManager.isFullScreenStatus()) {
                beforeX = x;
                x+= 250 * Gdx.graphics.getDeltaTime();
            }
        }
        // dashRight
        if (Gdx.input.isKeyJustPressed(right) && (!isPunching && !isKicking && !isBlocking)){
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
        if (Gdx.input.isKeyPressed(left) && (!isPunching && !isKicking && !isBlocking)) {
            if (x > -135 && ScreenManager.isFullScreenStatus()) {
                beforeX = x;
                x -= 250 * Gdx.graphics.getDeltaTime();
            } else if (x > -150 && !ScreenManager.isFullScreenStatus()) {
                beforeX = x;
                x -= 250 * Gdx.graphics.getDeltaTime();
            }
        }
        // dashLeft
        if (Gdx.input.isKeyJustPressed(left) && (!isPunching && !isKicking && !isBlocking)){
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
            beforeY = y;
            y += velocity.y * Gdx.graphics.getDeltaTime();
        }
    }

    public TextureRegion characterAction() {
        currentPunchFrame = punchAnimation.getKeyFrame(stateTime, true);
        currentWalkFrame = walkAnimation.getKeyFrame(stateTime, true);
        currentKickFrame = kickAnimation.getKeyFrame(stateTime,false);
        stateTime += Gdx.graphics.getDeltaTime();

        if (health == 0) {
            return dead;
        }

        if (characterId == 1){
            movementHitBox.set(getX(), getY(), 170, 360);
            damageHitBox.set(getX(), getY(), 220, 360);
        } else if (characterId == 2){
            movementHitBox.set(getX(), getY(), 150, 390);
            damageHitBox.set(getX(), getY(), 200, 390);
        } else if (characterId == 3){
            movementHitBox.set(getX(), getY(), 170, 440);
            damageHitBox.set(getX(), getY(), 220, 440);
        }

        if (isPunching) {
            if (stateTime >= punchAnimation.getAnimationDuration()) {
                isPunching = false;
            } else {
                return currentPunchFrame;
            }
        } else if (isKicking) {
            if (stateTime >= kickAnimation.getAnimationDuration()) {
                isKicking = false;
            } else {
                return currentKickFrame;
            }
        } else if (isBlocking && (y < 27)) {
            if(stateTime >= walkAnimation.getAnimationDuration()) {
                isBlocking = false;
            } else {
                return blocking;
            }
        }

        if (Gdx.input.isKeyJustPressed(punch)) {
            isPunching = true;
        } else if (Gdx.input.isKeyJustPressed(kick)) {
            isKicking = true;
        } else if (Gdx.input.isKeyPressed(block) && y < 27) {
            isBlocking = true;
        }else if ((Gdx.input.isKeyPressed(left) || Gdx.input.isKeyPressed(right)) && y < 27) {
            return currentWalkFrame;
        }

        if(isHit) {
            idle = imunity;
            isHit = false;
        } else if (y >= 27){
            idle = jumping;
        } else {
            setSkin();
        }


        stateTime = 0;
        return idle;
    }



    public void setSkin() {
        switch (characterId) {
            case 1:
                idle = new TextureRegion(new Texture("assets/IracemaSprites/Iracema_parada_D.png"));
                jumping = new TextureRegion(new Texture("assets/IracemaSprites/Iracema_pulo.png"));
                blocking = new TextureRegion(new Texture("assets/IracemaSprites/Iracema_Block.png"));
                imunity = new TextureRegion(new Texture("assets/IracemaSprites/Iracema_Dano.png"));
                dead = new TextureRegion(new Texture("assets/IracemaSprites/Iracema_Morta.png"));
                break;
            case 2:
                idle = new TextureRegion(new Texture("assets/LoiraSprites/Loira_parada_D.png"));
                jumping = new TextureRegion(new Texture("assets/LoiraSprites/Loira_pulo.png"));
                blocking = new TextureRegion(new Texture("assets/LoiraSprites/Loira_Block.png"));
                imunity = new TextureRegion(new Texture("assets/LoiraSprites/Loira_Dano.png"));
                dead = new TextureRegion(new Texture("assets/LoiraSprites/Loira_Morta.png"));
                break;
            case 3:
                idle = new TextureRegion(new Texture("assets/SartoSprites/Sarto_Parado.png"));
                jumping = new TextureRegion(new Texture("assets/SartoSprites/Sarto_Pulando_D.png"));
                blocking = new TextureRegion(new Texture("assets/SartoSprites/Sarto_Block.png"));
                imunity = new TextureRegion(new Texture("assets/SartoSprites/Sarto_Dano.png"));
                dead = new TextureRegion(new Texture("assets/SartoSprites/Sarto_Morto.png"));
                break;
            default:
                System.out.println("Não setou a skin!!!");
                break;
        }
    }

    public float getHealth() {
        if(this.health <= 0) {
            return 0;
        }
        return this.health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public TextureRegion getIdle() {
        return this.idle;
    }

    public float getBeforeX() {
        return beforeX;
    }

    public void setBeforeX(float beforeX) {
        this.beforeX = beforeX;
    }

    public float getBeforeY() {
        return beforeY;
    }

    public void setBeforeY(float beforeY) {
        this.beforeY = beforeY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWins() {
        return this.wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins2() {
        return this.wins2;
    }
    public void setWins2(int wins2) {
        this.wins2 = wins2;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
