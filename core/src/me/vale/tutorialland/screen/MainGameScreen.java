package me.vale.tutorialland.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import me.vale.tutorialland.SpaceGame;

public class  MainGameScreen implements Screen {

    public static final float SPEED = 300;
    public static final float SHIP_ANIMATION_SPEED = 0.5f;
    public static final int SHIP_WIDTH_PIXEL = 17;
    public static final int SHIP_HEIGHT_PIXEL = 32;

    //sono le misure dell'immagine che andremo a disegnare
    public static final int SHIP_WIDTH = SHIP_WIDTH_PIXEL * 3;
    public static final int SHIP_HEIGHT = SHIP_HEIGHT_PIXEL * 3;

    public static final float ROLL_TIMER_SWITCH_TIME = 0.25f; //tempo che si impiega tra un roll e l'altro dell'animazione


    Animation[] rolls;

    float x;
    float y;
    int roll;
    float rollTimer; //traccia il tempo del roll
    float stateTime; //usiamo come stato generale per l'animazione.
                    // La classe animazione

    SpaceGame game;

    /* Quando creiamo il costruttore di MainGameScreen, passiamo "SpaceGame Game" cosi dentro questa classe siamo in
    grado di accedere alla classe principale "SpaceGame". In questo modo accediamo al batch (dobbiamo definirlo public),
    E poi impostiamo il game di questa classe (this.game), con il game che passiamo dalla classe main.

    Dobbiamo impostare il batch come game.batch, perchè il batch appartiene al game passato dalla classe main.
    */

    public MainGameScreen (SpaceGame game){

        this.game = game;
        y = 15;
        x = SpaceGame.WIDTH / 2 - SHIP_WIDTH / 2;

        roll = 2;
        rollTimer = 0;
        rolls = new Animation[5];

        /*
        utilizziamo un array di regioni per gestire le animazioni, altrimenti non sarebbero animazioni
        se avessimo una sola texture.
         */
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(new Texture("ship.png"), SHIP_WIDTH_PIXEL, SHIP_HEIGHT_PIXEL);

        /* Costruttori: CTRL + SPACE per vedere i diversi costruttori. Quando creiamo un nuovo oggetto le varie cose
        che passiamo servono per creare l'oggetto.
         */

        rolls[0] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[2]);//ANIMAZIONE VERSO SINISTRA
        rolls[1] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[1]);
        rolls[2] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[0]);//ANIMAZIONE CENTRALE
        rolls[3] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[3]);
        rolls[4] = new Animation(SHIP_ANIMATION_SPEED, rollSpriteSheet[4]);//ANIMAZIONE VERSO DESTRA



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += SPEED * Gdx.graphics.getDeltaTime();

            //bordo superiore
            if (y + SHIP_HEIGHT + 1 > Gdx.graphics.getHeight()) {
                y = Gdx.graphics.getHeight() - SHIP_HEIGHT - 1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= SPEED * Gdx.graphics.getDeltaTime();

            if (y < 0) {
                y = 0;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= SPEED * Gdx.graphics.getDeltaTime();

            //bordo sinistro
            if (x < 0) {
                x = 0;
            }

            //aggiorniamo la virata a sinistra se siamo ancora in virata verso destra (movimento più fluido e naturale)
            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && roll > 0){
                rollTimer = 0;
                roll--;
            }

            //Update roll
                /*
                controlliamo che rollTimer sia maggiore del Roll_timer_switch_time,
                ovvero se il tempo in cui il giocatore decide di mantenere premuto il
                tasto destro o sinistro è maggiore nel tempo che abbiamo impostato per lo switch
                avverà l'animazione del verso in cui ci stiamo spostando.
                 */
            rollTimer -= Gdx.graphics.getDeltaTime();
            if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
                rollTimer = 0;
                roll--;

                if (roll < 0) {
                    roll = 0;
                }

            }
        }else {
                if (roll < 2) {
                    rollTimer += Gdx.graphics.getDeltaTime();
                    if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
                        rollTimer = 0;
                        roll++;

                        if (roll > 4) {
                            roll = 4;
                        }
                    }
                }
            }


// movimento verso destra
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += SPEED * Gdx.graphics.getDeltaTime();


            //bordo destro
            //Gdx.graphics.getWidth() <- screen width
            if (x + SHIP_WIDTH > Gdx.graphics.getWidth()) {
                x = Gdx.graphics.getWidth() - SHIP_WIDTH;
            }

            //update roll
            rollTimer += Gdx.graphics.getDeltaTime();
            if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll < 4) {
                rollTimer = 0;
                roll++;

                if (roll > 4) {
                    roll = 4;
                }
            }
            /*
            se roll è < 2 stiamo ancora virando verso sinistra.
             */
        } else {
            if (roll > 2) {
                rollTimer -= Gdx.graphics.getDeltaTime();
                if (Math.abs(rollTimer) > ROLL_TIMER_SWITCH_TIME && roll > 0) {
                    rollTimer = 0;
                    roll--;

                    if (roll < 0) {
                        roll = 0;
                    }
                }
            }
        }

        stateTime += delta;

        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();

        game.batch.draw((TextureRegion) rolls[roll].getKeyFrame(stateTime, true), x, y, SHIP_WIDTH, SHIP_HEIGHT);

        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
