package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ExplosionUfo {
    public static final float FRAME_LENGHT = 0.2f;  //tempo tra ogni frame dell'animazione
    public static int OFFSET = 8; //dobbiamo sottratte 8 alla posizione dell'asteroide per fare in modo che l'esplosione sia centrata
    public static int SIZE_UFO = 32; //dimensione dell'esplosione

    private static Animation<TextureRegion> anim_ufo;
    float x, y;
    float statetime;

    public boolean remove = false;

    public ExplosionUfo(float x, float y) {
        this.x = x ;
        this.y = y ;
        statetime = 0;

        if (anim_ufo == null) {
            anim_ufo = new Animation<>(FRAME_LENGHT, TextureRegion.split(new Texture("explosion.png"), SIZE_UFO,
                    SIZE_UFO)[0]);
        }
    }

    /*
    quando il tempo inizia (statetime = 0), il codice per l'animazione progredisce in base al progredire del tempo.

     */

    public void update(float deltatime) {
        statetime += deltatime;
        if(anim_ufo.isAnimationFinished(statetime)){
            remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw( anim_ufo.getKeyFrame(statetime),x,y);
    }
}
