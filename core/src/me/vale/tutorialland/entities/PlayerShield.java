package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.tools.CollisionReact;

import java.util.Random;

public class PlayerShield {
    Random random = new Random();
    public static final int max = 300;
    public static final int min = 250;
    public final int SPEED = random.nextInt(max - min) + min;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private Texture texture;

    float x, y;
    CollisionReact react;
    public boolean remove = false;

    public PlayerShield(float x, float y){
        this.x = x;
        this.y = y;
        this.react = new CollisionReact(x, y, WIDTH, HEIGHT);

        if (texture == null) {
            texture = new Texture("playerShield.png");
        }

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
