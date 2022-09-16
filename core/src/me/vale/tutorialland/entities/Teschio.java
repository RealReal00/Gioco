package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.screen.MainGameScreen;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;
import javax.swing.Timer;

import java.util.Random;

public class Teschio extends Thread{
    Random random = new Random();
    public float secondi = 0;
    public Timer t;
    public static int max = 400;
    public static int min = 250;
    public final int SPEED = random.nextInt(max-min)+min;
    public static int WIDTH = 30;
    public static int HEIGHT = 30;
    public static boolean skulls_accelerated=false;
    private Texture texture;
    private long contatore;



    /** Istante temporale dell'ultimo avvio del cronometro. */
    private long avviato_a;

    /** Variabile di stato che indica se il cronometro sta avanzando oppure no. */
    private boolean avanzando;


    float x, y;
    CollisionReact react;

    //l'oggetto è pubblico perché possiamo rimuoverlo dalla lista che esternamente.
    public boolean remove = false;

    public Teschio(float x) {
        this.x = x;
        this.y = SpaceGame.HEIGHT;
        this.react = new CollisionReact(x, y, WIDTH, HEIGHT);

        if (texture == null) {
            texture = new Texture("teschio_game.png");
        }
    }

    public void update(float deltaTime) {
        if(MainGameScreen.score>3500 && !skulls_accelerated){
            max=900;
            min=750;
            WIDTH=28;
            HEIGHT=28;
            skulls_accelerated=true;
        }

        y -= SPEED * deltaTime;
        x-= 100 * deltaTime;

        if (y < -HEIGHT) {
            remove = true;
        }
        react.move(x, y);
    }
    public void update1(float deltaTime){

        y -= SPEED * deltaTime;
        x += 100 * deltaTime;
        if (y < -HEIGHT) {
            remove = true;
        }
        react.move(x, y);

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y,WIDTH,HEIGHT);

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

    /** Metodo per (fermare ed) azzerare del cronometro. */
    public void azzera() {
        synchronized (this) {
            contatore = 0;
            avanzando = false;
        }
    }


    public void avanza() {
        synchronized (this) {
            avviato_a = System.currentTimeMillis();
            avanzando = true;
        }
    }


    public void ferma() {
        synchronized (this) {
            contatore += System.currentTimeMillis() - avviato_a;
            avanzando = false;
        }
    }

    /** Azzera il cronometro e ne fa partire il conteggio. */
    public void avanzaDaCapo() {
        azzera();
        avanza();
    }


    public long leggi() {
        synchronized (this) {
            return avanzando ? contatore + System.currentTimeMillis() - avviato_a : contatore;
        }
    }

    public String toString() {
        return "" + leggi();
    }
}
