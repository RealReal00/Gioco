package me.vale.tutorialland.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;

public class Bullet {
    public static final int SPEED = 500;
    public static final int WIDTH = 3; //larghezza ed altezza sono presi dalle dimensioni dell'immagine.
    public static final int HEIGHT = 12;
    private Texture texture;
    public static AssetManager manager;

    float x, y;
    CollisionReact react;

    //l'oggetto è pubblico perchè possiamo rimuoverlo dalla lista che esternamente.
    public boolean remove = false;

    public Bullet(float x, float y) {
        this.x = x;
        this.react = new CollisionReact(x,y,WIDTH,HEIGHT);
        this.y = y;

        if (texture == null) {
            texture = new Texture("bullet.png");
        }
    }

    public void update(float deltaTime) {
        y += SPEED * deltaTime; //spostamento asteroide
        if (y > SpaceGame.HEIGHT) {
            remove = true;
        }
        react.move(x,y); //aggiorniamo la posizione del asteroide per verificare le collisioni
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


    public CollisionReact getCollisionReact (){
        return react;
    }


}