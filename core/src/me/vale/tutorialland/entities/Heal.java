package me.vale.tutorialland.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;

import java.util.Random;

import static com.badlogic.gdx.Gdx.audio;

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
    public long id;

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

    Sound sound = new Sound() {

        @Override
        public long play() {
            return 0;
        }

        @Override
        public long play(float volume) {
            sound = audio.newSound(Gdx.files.internal("Heal Sound Effect.mp3"));
            id = sound.play();
            return 0;
        }

        @Override
        public long play(float volume, float pitch, float pan) {
            return 0;
        }

        @Override
        public long loop() {
            return 0;
        }

        @Override
        public long loop(float volume) {
            return 0;
        }

        @Override
        public long loop(float volume, float pitch, float pan) {
            return 0;
        }

        @Override
        public void stop() {

        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {
            sound.dispose();
        }

        @Override
        public void stop(long soundId) {
            sound.stop(id);
        }

        @Override
        public void pause(long soundId) {
            sound.pause(id);
        }

        @Override
        public void resume(long soundId) {
            sound.resume(id);
        }

        @Override
        public void setLooping(long soundId, boolean looping) {

        }

        @Override
        public void setPitch(long soundId, float pitch) {

        }

        @Override
        public void setVolume(long soundId, float volume) {

        }

        @Override
        public void setPan(long soundId, float pan, float volume) {

        }
    };
}
