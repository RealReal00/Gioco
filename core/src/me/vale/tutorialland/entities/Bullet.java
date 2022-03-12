package me.vale.tutorialland.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.screen.MainGameScreen;

public class Bullet {
    public static final int SPEED = 500;
    private static Texture texture;

    float x, y;

    //l'oggetto è pubblico perchè possiamo rimuoverlo dalla lista che esternamente.
    public boolean remove = false;

    public Bullet(float x, float y) {
        this.x = x;
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
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);

    }

}