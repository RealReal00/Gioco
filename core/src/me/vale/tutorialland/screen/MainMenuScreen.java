package me.vale.tutorialland.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import me.vale.tutorialland.SpaceGame;

public class MainMenuScreen implements Screen {

    private static final int EXIT_BUTTON_WIDTH = 100;
    private static final int EXIT_BUTTON_HEIGHT = 50;
    private static final int PLAY_BUTTON_WIDTH = 100;
    private static final int PLAY_BUTTON_HEIGHT = 50;


    SpaceGame game;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;

    public MainMenuScreen (SpaceGame game){
        this.game = game;
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(exitButtonActive,0,675,EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

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
