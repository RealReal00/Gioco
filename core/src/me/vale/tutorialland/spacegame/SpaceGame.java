package me.vale.tutorialland.spacegame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.screen.MainMenuScreen;
import me.vale.tutorialland.tools.GameCamera;
import me.vale.tutorialland.tools.ScrollingBackground;

/*
	Estendiamo SpaceGame con Game, dato che LibGdx ha una classe Game che ha molti tools per gestire menu ed altro,
	che risultano molto utili. (ApplicationAdapter fa riferimento ad un app generica).
 */

/*
SpaceGame estende la classe Game che a sua volta implementa l'interfaccia Application Listener
che contiene il metodo create che viene chiamato quando l'applicazione viene creata.


 */
public class SpaceGame extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static boolean IS_MOBILE = false;

	/*
	Utilizziamo la variabile per modificare la velocità del personaggio, in questo modo la nostra velocità rimarrà
	fissa, ovvero viaggeremo per "40 x DeltaTime", in questo modo qualsiasi sia il refreshRate non avremmo differenza
	sulla velocità del personaggio. Viaggiamo a 40pixel per secondo.*/

	public SpriteBatch batch;
	public ScrollingBackground ScrollingBackground;
	public GameCamera cam;


	/*
	Quando avviamo il programma, il primo metodo che viene letto è "create".
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new GameCamera(WIDTH, HEIGHT);


		if(Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS){
			IS_MOBILE = true;
		}


		this.ScrollingBackground = new ScrollingBackground();
		this.setScreen(new MainMenuScreen(this));

	}

/*
	il metodo render viene eseguito n volte al secondo, in base alla quantità di frame per secondo (n = fps).
	Di default LibGDX è cappato a 60 fps.

	ScreenUtils.clear serve per pulire lo schermo e metterlo allo stesso colore.

	"batch.begin" serve per dire a LibGDX che può disegnare l'immagine sullo schermo,
	e "batch.end" invece dice che può fermarsi.
	"batch.draw" si utilizza per impostare l'immagine da disegnare nel nostro caso "img" che corrisponde ad un
	oggetto Texture. i valori x:0 e y:0 indicano le coordinate alle quali l'immagine deve essere disegnata,
	di default le coordinate 0,0 indicano l'angolo in alto a sinistra.

	Gdx.input.isKeyPressed: restituisce se il tasto è stato premuto.
	Gdx.input.isKeyJustPressed: restituisce se il tasto è stato appena premuto.
	La reale differenza sta che nel primo possiamo tenere premuto e continuare a muoverci, mentre nel secondo
	dobbiamo ripetutamente premere il pulsante.

	se tra ogni lettura di un comando mettiamo un "else" verra svolto solo 1 if per loop, quindi non saremmo
	in grado di muoverci diagonalmente, mentre se togliamo gli "else", ad ogni loop entriamo in più "if" e possiamo
	andare in più direzioni (consentito il movimento diagonale).


	Delta time = 1/fps (1/60) tempo tra un fps e l'altro. Più è alto, maggiore sarà la fluidità e la velocità.
	Chiamiamo "super.render" perchè sappiamo che andrà in loop
 */

	@Override
	public void render () {

		/* stiamo dicendo al batch che questa è la nostra camera e con .combined passiamo le matrici del nostro schermo
		 e questo è come vogliamo che venga gestito, ovvero stretciato in base alla dimensione dello schermo. manteniamo il rapporto. */
		batch.setProjectionMatrix(cam.combined());
		super.render(); //utilizziamo super. perchè ci stiamo riferendo ad un metodo della classe genitore
	}

	public void resize(int width, int height) {
			cam.update(width,height); //IN QUESTO MODO QUALSIASI SIA IL RAPPORTO DELLO SCHERMO LO ANDIAMO A RIDIMENSIONARE
			super.resize(width,height);
	}
}

