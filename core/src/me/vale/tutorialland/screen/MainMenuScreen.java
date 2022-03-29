package me.vale.tutorialland.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.ScrollingBackground;

public class MainMenuScreen implements Screen {

    private static final int EXIT_BUTTON_WIDTH = 100;
    private static final int EXIT_BUTTON_HEIGHT = 50;
    private static final int PLAY_BUTTON_WIDTH = 200;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_Y = 50;
    private static final int PLAY_BUTTON_Y = 100;

    private final Sound buttonFx;
    private boolean exitButtonSound = false;
    private boolean playButtonSound = false;

    final SpaceGame game;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;

    public MainMenuScreen (final SpaceGame game){
        this.game = game;
        buttonFx = Gdx.audio.newSound(Gdx.files.internal("Sound Effects Button.mp3"));
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");

        game.ScrollingBackground.setSpeedFixed(true);
        game.ScrollingBackground.setSpeed(ScrollingBackground.DEFAULT_SPEED);

        final MainMenuScreen mainMenuScreen = this;
        //reinizializziamo un oggetto e gli diamo un metodo
        Gdx.input.setInputProcessor(new InputAdapter() {

            /*
            Usiamo touchDown per rilevare dove viene rilasciata la pressione sullo schermo (non per quando avviene)
             */
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //Exit button
                int x = SpaceGame.WIDTH / 2 - EXIT_BUTTON_WIDTH /2;
                if (game.cam.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y > EXIT_BUTTON_Y){
                    mainMenuScreen.dispose();
                    Gdx.app.exit();
                }

                //Play button
                x = SpaceGame.WIDTH / 2 - PLAY_BUTTON_WIDTH /2;
                if (game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y > PLAY_BUTTON_Y) {
                    mainMenuScreen.dispose(); //per non
                    game.setScreen(new MainGameScreen(game));
                }
                    return super.touchUp(screenX, screenY, pointer, button);
            }
        });

    }



    @Override
    public void show() {

    }

    /*
    Utilizziamo game.batch.begin() per settare gli stati del render ed una volta finito il setup degli stati
    tramite game.batch.end() verrà eseguito il "draw" su schermo (ovvero viene eseguito il rendering 2D).
     */
    @Override
    public void render(float delta) {


        ScreenUtils.clear(0.15f, 0.15f, 0.3f, 1);
        game.batch.begin();

        game.ScrollingBackground.updateAndRender(delta,game.batch);
        ScrollingBackground.image = new Texture("spaceBackgrondMoon.jpg");

        int x = SpaceGame.WIDTH / 2 - EXIT_BUTTON_WIDTH /2;
        if (game.cam.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, x ,EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if(!exitButtonSound){
                buttonFx.play();
                exitButtonSound = true;
                playButtonSound = false;
            }

        }else{
            game.batch.draw(exitButtonInactive, x ,EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            exitButtonSound = false;
        }

        /*
        Se il cursore del mouse si trova all'interno del tasto, viene caricata l'immagine gialla del tasto,
        per evidenziare il fatto che stiamo selezionando quella scelta.
        game.cam.getInputInGameWorld().x <- posizione asse x del mouse
        game.cam.getInputInGameWorld().y <- posizione asse y del mouse

        if(game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x)   <- se il mouse si trova
        all'interno della scritta e ripetiamo la stessa cosa per l'asse y.
        */
        x = SpaceGame.WIDTH /2 - PLAY_BUTTON_WIDTH /2;
        if (game.cam.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputInGameWorld().x > x && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y > PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive, x ,PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if(!playButtonSound){
                buttonFx.play();
                exitButtonSound = false;
                playButtonSound = true;
            }
        }else{
            game.batch.draw(playButtonInactive, x ,PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            playButtonSound = false;
        }


        //game.batch.draw(playButtonActive,375,675,PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
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

    //ci disfiamo degli input processor quando abbiamo fatto con ogni schermata, in modo che non possa più prendere ulteriori input
    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        buttonFx.dispose();
    }
}
