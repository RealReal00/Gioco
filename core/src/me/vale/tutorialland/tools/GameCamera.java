package me.vale.tutorialland.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameCamera {

    private final OrthographicCamera cam; // controlla
    private final StretchViewport viewport; // indifferentemente dal tipo di smartphone e rapporto dello schermo, viene streachata l'immagine in modo da coprire interamente lo schermo

    public GameCamera (int width, int height){

        cam = new OrthographicCamera(); //Costruisce una nuova OrthographicCamera, usando la larghezza e l'altezza del viewport date.
        viewport = new StretchViewport(width, height, cam); // Crea una nuova finestra utilizzando OrthographicCamera.
        viewport.apply();
        cam.position.set((float)	width/2,(float)	height/2,0); //imposto la camera al centro dello schermo
        cam.update();
    }

    public Matrix4 combined(){ //la matrice combinata di proiezione e vista
        return cam.combined;
    }

    public void update (int width, int height){
        viewport.update(width, height);
    } //aggiorno la cam

    public Vector2 getInputInGameWorld () {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight()- Gdx.input.getY(), 0); //acquisisce l'input sullo schermo
        Vector3 unprojected = cam.unproject(inputScreen); //Funzione per tradurre un punto dato in coordinate dello schermo nello spazio di gioco.
        return new Vector2(unprojected.x, unprojected.y);


    }
}
