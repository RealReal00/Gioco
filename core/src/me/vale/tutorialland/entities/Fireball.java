package me.vale.tutorialland.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.screen.MainGameScreen;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;

public class Fireball {
    public static final int SPEED = 500;
    public static int WIDTH = 20; //larghezza ed altezza sono presi dalle dimensioni dell'immagine.
    public static int HEIGHT = 40;
    private Texture texture;
    public static AssetManager manager;
    //public final Sound fireball_sound = Gdx.audio.newSound(Gdx.files.internal("Fireball_sound.mp3"));

    float x, y;
    CollisionReact react;

    //l'oggetto è pubblico perchè possiamo rimuoverlo dalla lista che esternamente.
    public boolean remove = false;

    public Fireball(float x, float y) {
        this.x = x;
        this.react = new CollisionReact(x,y,WIDTH,HEIGHT);
        this.y = y;

        if (texture == null) {
            texture = new Texture("fireball.png");
        }

    }

    public void update(float deltaTime) {
        y -= SPEED * deltaTime; // velocità proiettile
        //fireball_sound.play();
        if (y < -10) {
            remove = true;
        }
        react.move(x,y); //aggiorniamo la posizione del asteroide per verificare le collisioni, se lo togli il
        // proiettile non colpisce l'asteroide
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y,WIDTH,HEIGHT);
    }


    public CollisionReact getCollisionReact (){
        return react;
    }


}
