package ui;

/**
 * Created by marcoghilardelli on 02.05.16
 * this class is a Singleton and should only be accessed with the method 'getInstance()'
 */

import config.Config;
import processing.core.PApplet;
import shape.Shape;


public class UI extends PApplet {

    public static void main(String args[]) {
        PApplet.main(new String[]{"--present", UI.class.getName()});
    }

    private static PApplet app;

    int framerate = 3;
    Shape shape;
    Grid grid;

    //@Override
    public void settings() {
        size(Config.ROWS * Config.GRIDSIZE + 2 * Config.BORDER_X, Config.COLlUMNS * Config.GRIDSIZE + 2 * Config.BORDER_Y);
    }

    @Override
    public void setup() {
        app = this;
        grid = new Grid();
        shape = Shape.getNewShape((int) random(0, 3), 5, 4);
        //frameRate(framerate);
    }

    @Override
    public void draw() {
        if (!keyPressed) {
            frameRate(framerate);
        }
        grid.drawGrid();
        if (shape.moveVertical(grid)) {
            shape = Shape.getNewShape((int) random(0, 3), 5, 4);
        }
        //draw circle which hides the incoming objects
        {
            fill(0);
            noStroke();
            rect(0, 0, width, Config.BORDER_Y);
        }
    }


    @Override
    public void keyPressed() {
        if (key == CODED) {
            switch (keyCode) {
                case RIGHT:
                    shape.moveHorizontal(false, grid);
                    break;
                case LEFT:
                    shape.moveHorizontal(true, grid);
                    break;
                case SHIFT:
                    shape.rotate();
                    break;
                case DOWN:
                    frameRate(3 * framerate);
                    break;
                default:
                    frameRate(framerate);
            }
        }
    }

    /**
     * draws the board on the UI
     */
    private void drawUI() {

    }

    /**
     * use this method to access any of processing commands
     *
     * @return PApplet instance
     */
    public static PApplet getInstance() {
        return app;
    }

}
