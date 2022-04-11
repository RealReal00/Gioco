package me.vale.tutorialland.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.vale.tutorialland.spacegame.SpaceGame;

/*Launcher LibGdx per l'applicazione. Questa classe viene generata alla creazione del progetto.
in questa classe settiamo le impostazioni dell'applicazione:
- cap FPS,
- larghezza immagine
- altezza immagine
- ridimensionamento dell'immagine
- tecnologia vSync per evitare il tearing sullo schermo
*/

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = SpaceGame.WIDTH;
		config.height = SpaceGame.HEIGHT;
		config.resizable = true;
		config.vSyncEnabled = true;

		new LwjglApplication(new SpaceGame(), config);
	}
}
