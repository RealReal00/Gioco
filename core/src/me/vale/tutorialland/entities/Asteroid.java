package me.vale.tutorialland.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.tools.CollisionReact;

public class Asteroid {

    public static final int SPEED = 250;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    private static Texture texture;

    float x, y;
    CollisionReact react;

    //l'oggetto è pubblico perchè possiamo rimuoverlo dalla lista che esternamente.
    public boolean remove = false;

    public Asteroid(float x) {
        this.x = x;
        this.y = Gdx.graphics.getHeight();
        this.react = new CollisionReact(x,y,WIDTH,HEIGHT);

        if (texture == null) {
            texture = new Texture("asteroid.png");
        }
    }

    public void update(float deltaTime) {
        y -= SPEED * deltaTime;
        if (y < -HEIGHT) {
            remove = true;
        }
        react.move(x,y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);

    }

    public CollisionReact getCollisionReact() {
        return react;
    }
}
