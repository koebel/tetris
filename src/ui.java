/**
 * Created by marcoghilardelli on 02.05.16.
 */
import processing.core.*;

public class ui extends PApplet {
    int sizeX= 10;
    int sizeY = 20;
    public int sizeRect = 40;
    int sizeBorderX = 200;
    int sizeBorderY = 50;


    public void settings() {
        size(sizeX*sizeRect+2*sizeBorderX,sizeY*sizeRect+2*sizeBorderY);
    }
    public void setup(){

    }
    public void draw() {
        fill(255,255,255);
        textSize(20);
        text("Tetris 2",0,0);
        background(0,0,0);
        fill(0,0,0);
        stroke(255,255,255);
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {
                rect(x*sizeRect+sizeBorderX, y*sizeRect+sizeBorderY, sizeRect, sizeRect);
            }
        }

    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "ui" } );
    }
}
