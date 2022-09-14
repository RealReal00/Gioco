package me.vale.tutorialland.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.screen.MainGameScreen;
import me.vale.tutorialland.spacegame.SpaceGame;

/* La classe ScrollingBackground ci serve per dare l'illusione che lo sfondo sia continuo, quando in realtà prendiamo la stessa
immagine due volte, e facciamo scorrere la prima e prima che finisca facciamo scorrere la seconda, in modo da dare senso di continuità
*/

public class ScrollingBackground {
    public static final int DEFAULT_SPEED = 150;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELLERATION = 200;
    public static Texture image;
    public static Texture image_levelup;
    float y1, y2;
    float k1, k2;
    int speed; //velocità in pixel / s
    int goalSpeed; //velocità massima di movimento da raggiungere
    float imageScale;
    float imageScale1;
    boolean speedFixed;
    boolean disattiva = false;

    public ScrollingBackground() {
        image = new Texture("spaceBackgrondMoon.jpg");
        image_levelup = new Texture("spaceimage_new.jpg");

        y1 = 0;
        y2 = image.getHeight() - 150;
        k1 = 0;
        k2 = image_levelup.getHeight() - 150;
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        imageScale = (float) SpaceGame.WIDTH / image.getWidth(); //serve per mantenere in proporzione la schermata di gioco
        imageScale1 = (float) SpaceGame.WIDTH / image_levelup.getWidth();
        speedFixed = true;
    }

    public void updateAndRender(float deltaTime, SpriteBatch batch) {
        //Speed adjustemnt to reach goal
      if (MainGameScreen.score > 1000) {   //era 1000
            image.dispose();
            image = new Texture("drawSpace.jpg");
        }
       /*if(MainGameScreen.score > 200){
            image.dispose();
            image = new Texture("spaceimage_new.jpg");
        }*/
        if (MainGameScreen.score > 3500) {
            if(!disattiva) {
                disattiva=true;
                image.dispose();
            }
            k1 -= 0 * deltaTime;
            k2 -= 0 * deltaTime;
            if (k1 + image_levelup.getHeight() * imageScale1 <= 0) {
                k1 = k2 + image_levelup.getHeight() * imageScale1;
            }
            if (k2 + image_levelup.getHeight() * imageScale1 <= 0) {
                k2 = k1 + image_levelup.getHeight() * imageScale1;
            }
            if (speed < goalSpeed) {
                speed += GOAL_REACH_ACCELLERATION * deltaTime;
                if (speed > goalSpeed) {
                    speed = goalSpeed;
                }
            } else if (speed > goalSpeed) {
                speed -= GOAL_REACH_ACCELLERATION * deltaTime;
                if (speed < goalSpeed) {
                    speed = goalSpeed;
                }
            }
            if (!speedFixed) {
                speed += ACCELERATION * deltaTime;
            }
            batch.draw(image_levelup, 0, k1, SpaceGame.WIDTH, image_levelup.getHeight() * imageScale1);
            batch.draw(image_levelup, 0, k2, SpaceGame.WIDTH, image_levelup.getHeight() * imageScale1);
        }
        else {
            if (speed < goalSpeed) {
                speed += GOAL_REACH_ACCELLERATION * deltaTime;
                if (speed > goalSpeed) {
                    speed = goalSpeed;
                }
            } else if (speed > goalSpeed) {
                speed -= GOAL_REACH_ACCELLERATION * deltaTime;
                if (speed < goalSpeed) {
                    speed = goalSpeed;
                }
            }
            if (!speedFixed) {
                speed += ACCELERATION * deltaTime;
            }
            y1 -= speed * deltaTime;
            y2 -= speed * deltaTime;


               /*
               Se l'immagine è completamente fuori dallo schermo dobbiamo controllare che la "Y + image.getHeight" sia minore uguale a 0
               se l'immagine raggiunge la fine dello schermo e non è visibile, mettila in alto */
            if (y1 + image.getHeight() * imageScale <= 0) {
                y1 = y2 + image.getHeight() * imageScale;
            }
            if (y2 + image.getHeight() * imageScale <= 0) {
                y2 = y1 + image.getHeight() * imageScale;
            }
            //Render
            batch.draw(image, 0, y1, SpaceGame.WIDTH, image.getHeight() * imageScale);
            batch.draw(image, 0, y2, SpaceGame.WIDTH, image.getHeight() * imageScale);
        }
    }


        public void setSpeed ( int goalSpeed){
            this.goalSpeed = goalSpeed;
        }
        public void setSpeedFixed ( boolean speedFixed){
            this.speedFixed = speedFixed;
        }

}



