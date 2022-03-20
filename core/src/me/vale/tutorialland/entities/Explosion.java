package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
questa classe tiene conto di ogni singola esplosione sulla mappa, e quando l'animazione dell'esplosione è finita
viene rimossa.
 */
public class Explosion {
    public static final float FRAME_LENGHT = 0.2f;  //tempo tra ogni frame dell'animazione
    public static int OFFSET = 8; //dobbiamo sottratte 8 alla posizone dell'asteroide per fare in modo che l'esplosione sia centrata
    public static int SIZE = 32; //dimensione dell'esplosione

    private static Animation anim = null;
    float x, y;
    float statetime;

    public boolean remove = false;

    public Explosion(float x, float y) {
        this.x = x - OFFSET;
        this.y = y - OFFSET;
        statetime = 0;

        if (anim == null) {
            anim = new Animation<>(FRAME_LENGHT, TextureRegion.split(new Texture("explosion.png"), SIZE, SIZE)[0]);
        }
    }

    /*
    quando il tempo inizia (statetime = 0), il codice per l'animazione progedisce in base al progedire del tempo.

     */

    public void update(float deltatime) {
        statetime += deltatime;
        if(anim.isAnimationFinished(statetime)){
            remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw((TextureRegion) anim.getKeyFrame(statetime),x,y);
    }
}
