package ui;

/**
 * Created by marcoghilardelli on 02.05.16
 * this class is a Singleton and can only be accessed with the method 'getInstance()'
 */
import processing.core.*;
import shape.*;

public class UI extends PApplet {

    public static void main(String args[]) {
        PApplet.main(new String[] {"--present", UI.class.getName()});
    }

    private static PApplet app;
    private static int sizeRect = 40;

    int sizeX= 10;
    int sizeY = 20;
    int sizeBorderX = 200;
    int sizeBorderY = 50;
    Shape shape;
    Grid grid;

    @Override
    public void settings() {
        size(sizeX*sizeRect+2*sizeBorderX,sizeY*sizeRect+2*sizeBorderY);
    }

    @Override
    public void setup(){
        app = this;
        shape = Shape.getNewShape((int)random(0, 3), width/2, sizeBorderY);
        grid = new Grid();
        frameRate(3);
    }

    @Override
    public void draw() {
        drawBoard();
        grid.drawGrid();
        shape.drawShape();
        shape.moveVertical();
        fill(0);
        noStroke();
        rect(0, 0, width, sizeBorderY);
    }

    @Override
    public void keyPressed() {
        if (key == CODED) {
            switch (keyCode) {
                case RIGHT:
                    shape.moveHorizontal(false);
                    break;
                case LEFT:
                    shape.moveHorizontal(true);
                    break;
                case SHIFT:
                    shape.rotate();
                    break;
            }
        }
    }

    public static int getSizeRect() {
        return sizeRect;
    }

    /**
     * draws the board on the UI
     */
    private void drawBoard(){
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

    /**
     * use this method to access any of processing commands
     * @return PApplet instance
     */
    public static PApplet getInstance(){
        return app;
    }

}
