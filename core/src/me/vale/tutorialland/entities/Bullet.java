package me.vale.tutorialland.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.screen.MainGameScreen;
import me.vale.tutorialland.tools.CollisionReact;

public class Bullet {
    public static final int SPEED = 500;
    public static final int WIDTH = 3; //larghezza ed altezza sono presi dalle dimensioni dell'immagine.
    public static final int HEIGHT = 12;
    private static Texture texture;

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
        y += SPEED * deltaTime;
        if (y > Gdx.graphics.getHeight()) {
            remove = true;
        }
        react.move(x,y); //aggiorniamo la posizione del bullet per verificare le collisioni
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


    public CollisionReact getCollisionReact (){
        return react;
    }
}