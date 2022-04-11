package me.vale.tutorialland.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;

import java.util.Random;

/* La classe Asteroid rappresenta la mia entità asteroide, dove sono presenti gli attributi che ne definiscono la velocità
(differente per ogni volta che creaiamo un nuovo asteroide), la dimensione del nostro oggetto (che si riferisce alla grandezza
in pixel dell'immagine 'png'). Le coordinate ci servono per indicare il punto in cui l'asteroide verrà creato,
mentre l'oggetto react ci serve per verificare le collisioni.

- Abbiamo il costruttore "public Asteroid" che utilizziamo per generare un oggetto Asteroid
Questo metodo richiede una coordinata x di tipo float, la coordinata y a cui viene assegnata
l'altezza della schermata di gioco, tramite l'attributo "Height" della classe SpaceGame, ed assegnamo all'oggetto react la
dimensione dell'oggetto e le sue coordinate.
Assegnamo una texture (grafica dell'oggetto), nel caso in cui non sia ancora stata assegnata una Texture.


- Il metodo update viene utilizzato per aggiornare la posizione dell'oggetto.
Questo metodo richiede che venga passato il parametro deltaTime che corrisponde al periodo di tempo trascorso tra
il rendering di un frame e l'altro (60 frame per secondo 'FPS' equivalgono a dire 1/60 = 0.016666s tra un fotogramma e
l'altro).
L'oggetto per ogni frame che passa scorre sull'asse Y ad una velocità pari all'attributo SPEED, che viene stabilità alla
creazione. Per fare in modo che l'oggetto percorra lo stesso spazio con diverso Frame-Rate dobbiamo moltiplicare la velocità
per il deltaTime, così facendo l'oggetto percorrerà un quantitativo di spazio più grande per ogni frame reinderizzato,
se il Frame-Rate risulta minore dei 60 che LibGdx ha come "cap", oppure uno spazio minore se il Frame-Rate viene impostato
dai settaggi superiore a 60.
Se l'oggetto esce dalla Y dello schermo impostiamo il suo attributo remove a "true" così sappiamo che quell'oggetto può
essere eliminato.

- Il metodo render ci serve per eseguire il rendering dell'oggetto durante la fase di gioco.
Viene passato un parametro SpriteBatch Batch che viene utilizzato per disegnare rettangoli 2D che fanno riferimento a una
texture (region). La classe eseguirà il batch dei comandi di disegno e li ottimizzerà per l'elaborazione da parte della GPU.
Tutti i comandi di disegno del Batch operano in coordinate dello schermo. Il sistema di coordinate dello schermo
ha un asse x che punta a destra, un asse y che punta verso l'alto e l'origine si trova nell'angolo inferiore sinistro
dello schermo.

- Il metodo getCollisionReact ci ritorna react


*/


public class Asteroid {

    Random random = new Random();

    public static final int max = 400;
    public static final int min = 250;
    public final int SPEED = random.nextInt(max-min)+min;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    private Texture texture;

    float x, y;
    CollisionReact react;

    //l'oggetto è pubblico perché possiamo rimuoverlo dalla lista che esternamente.
    public boolean remove = false;

    public Asteroid(float x) {
        this.x = x;
        this.y = SpaceGame.HEIGHT;
        this.react = new CollisionReact(x, y, WIDTH, HEIGHT);

        if (texture == null) {
            texture = new Texture("asteroid.png");
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

}