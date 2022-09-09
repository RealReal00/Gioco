package me.vale.tutorialland.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.vale.tutorialland.spacegame.SpaceGame;
import me.vale.tutorialland.tools.CollisionReact;

import java.util.Random;




public class Megafungus {
    public static final int SPEED= 700;
    public static final int WIDTH= 80;
    public static final int HEIGHT= 80;

    private static Texture texture;
    float x,y;
    public boolean remove= false;
    CollisionReact react;

    public Megafungus(float x){
        this.x=x;
        this.y= Gdx.graphics.getHeight();
        this.react = new CollisionReact(x, y, WIDTH, HEIGHT);

        if(texture==null){
            texture=new Texture("megafungo.png");
        }


    }
    public void update (float deltaTime){
        y-= SPEED  * deltaTime;
        if(y<-HEIGHT){
            remove=true;
        }
        react.move(x,y);
    }
    public void render (SpriteBatch batch){
        batch.draw(texture,x,y,WIDTH,HEIGHT);
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
