package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.tools.CollisionReact;


/* Questa classe fa riferimento allo scudo che viene generato sopra la navicella
 */

public class PlayerShield {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private Texture texture;

    float x, y;
    CollisionReact react;

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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
