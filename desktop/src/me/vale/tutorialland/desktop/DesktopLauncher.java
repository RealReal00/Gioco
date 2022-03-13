package me.vale.tutorialland.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.vale.tutorialland.spacegame.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = SpaceGame.WIDTH;
		config.height = SpaceGame.HEIGHT;
		config.resizable = false;
		//config.fullscreen = true;
		new LwjglApplication(new SpaceGame(), config);
	}
}
