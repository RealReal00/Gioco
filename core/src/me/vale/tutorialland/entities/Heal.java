package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;

import java.util.Random;

// Questa classe rappresenta l'entità heal che ripristina 20% degli HP.

public class Heal {

    Random random = new Random();
    public static final int max = 500;
    public static final int min = 300;
    public final int SPEED = random.nextInt(max - min) + min;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    private Texture texture;

    float x, y;
    CollisionReact react;
    public boolean remove = false;

    public Heal(float x) {
        this.x = x;
        this.y = SpaceGame.HEIGHT;
        this.react = new CollisionReact(x, y, WIDTH, HEIGHT);

        if (texture == null) {
            texture = new Texture("heal.png");
        }

    }

    public void update(float deltaTime) {
        y -= SPEED * deltaTime;
        if (y < -HEIGHT) {
            remove = true;
        }
        react.move(x, y);

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public CollisionReact getCollisionReact() {
        return react;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}