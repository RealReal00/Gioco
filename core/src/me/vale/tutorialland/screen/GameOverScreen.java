package me.vale.tutorialland.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.ScrollingBackground;

public class GameOverScreen implements Screen {

    private static final int BANNER_WITDH = 350;
    private static final int BANNER_HEIGHT = 100;

    private final Sound gameOverFx = Gdx.audio.newSound(Gdx.files.internal("Game Over sound effect.mp3"));

    SpaceGame game;
    int score, highscore;

    Texture gameOverBanner;
    BitmapFont scoreFont;

    public GameOverScreen (SpaceGame game, int score){
        this.game = game;
        this.score = score;

        //SoundFX
        gameOverFx.play();

        /*
        Ogni volta il gioco va a vedere nel file il tag di nome highscore e cerca il suo valore, se non è presente il tag highscore, usa il suo valore
        di default a 0, impostando highscore a 0.
        Se (score > highscore) allora aggiorniamo highscore nel file con il comando "prefs.putInteger("highscore", score)" e salviamo il file con il comando
        "prefs.flush()". Se non eseguiamo il flush, il file non viene salvato e di conseguenza si perde highscore.
         */
        //get highscore from save file
        Preferences prefs = Gdx.app.getPreferences("spacegame");
        this.highscore = prefs.getInteger("highscore", 0);

        //check if score beats highscore
        if(score > highscore){
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        //LOAT TEXTURE AND FONTS
        gameOverBanner = new Texture("game_over.png");
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.ScrollingBackground.updateAndRender(delta,game.batch);

        //disegnamo il banner nella parte alta dello schermo con 15 pixel di differenza (-15),
        game.batch.draw(gameOverBanner, (float) SpaceGame.WIDTH /2 - (float) BANNER_WITDH /2, SpaceGame.HEIGHT - BANNER_HEIGHT - 15, BANNER_WITDH, BANNER_HEIGHT);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score :\n" + score, Color.WHITE, 0, Align.left, false );
        GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore :\n" + highscore, Color.WHITE, 0, Align.left, false );
        scoreFont.draw(game.batch, scoreLayout, (float) SpaceGame.WIDTH/2-scoreLayout.width /2, SpaceGame.HEIGHT - BANNER_HEIGHT - 15 * 2);
        scoreFont.draw(game.batch, highscoreLayout, (float) SpaceGame.WIDTH/2-highscoreLayout.width /2, SpaceGame.HEIGHT - BANNER_HEIGHT - scoreLayout.height- 15 * 3);

        game.ScrollingBackground.setSpeedFixed(true);
        game.ScrollingBackground.setSpeed(ScrollingBackground.DEFAULT_SPEED);

        GlyphLayout tryAgainLayout = new GlyphLayout(scoreFont, "Try Again");
        GlyphLayout mainMenuLayout = new GlyphLayout(scoreFont,"Main Menu");

        float tryAgainX = (float) SpaceGame.WIDTH / 2 - tryAgainLayout.width /2;
        float tryAgainY = (float) SpaceGame.HEIGHT / 2 - tryAgainLayout.height /2;
        float mainMenuX = (float) SpaceGame.WIDTH / 2 - tryAgainLayout.width /2;
        float mainMenuY = (float) SpaceGame.HEIGHT / 2 - tryAgainLayout.height /2 - tryAgainLayout.height - 15;

        float touchX = game.cam.getInputInGameWorld().x, touchY = SpaceGame.HEIGHT - game.cam.getInputInGameWorld().y;

        //IF "TRY AGAIN" AND "MAIN MENU" IS BEING PRESS

        if(Gdx.input.isTouched()){
            //try again
            if(touchX > tryAgainX && touchX < tryAgainX + tryAgainLayout.width && touchY > tryAgainY - tryAgainLayout.height && touchY < tryAgainY){
                this.dispose();
                game.batch.end();
                game.setScreen(new MainGameScreen(game));
                return;
            }

            if(touchX > mainMenuX && touchX < mainMenuX + tryAgainLayout.width && touchY > mainMenuY - tryAgainLayout.height && touchY < mainMenuY){
                this.dispose();
                game.batch.end();
                game.setScreen(new MainMenuScreen(game));

                return;
            }
        }

        //Draw buttons
        scoreFont.draw(game.batch, tryAgainLayout,tryAgainX, tryAgainY);
        scoreFont.draw(game.batch, mainMenuLayout,mainMenuX, mainMenuY);
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
        gameOverFx.dispose();
    }
}
