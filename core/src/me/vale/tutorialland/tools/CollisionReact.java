package me.vale.tutorialland.tools;

public class CollisionReact {

    float x,y;
    int width, height;

    public CollisionReact(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move (float x, float y){
        this.x = x;
        this.y = y;
    }

    /*
    Stiamo controllando se l'oggetto che collide risulta esterno alla nostra navicella,
    verificando che l'asteroide non rientri nel perimetro della navicella, questo significa che non c'è stata la
    collisione
     */
    public boolean collidesWith (CollisionReact react) {
        return x < react.x + react.width && y < react.y + react.height && x + width > react.x && y + height > react.y;
    }


}
