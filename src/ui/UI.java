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

    Shape shape;
    Grid grid;
    int score = 0;

    //@Override
    public void settings() {
        size(Config.ROWS * Config.GRIDSIZE + 2 * Config.BORDER_X, Config.COLlUMNS * Config.GRIDSIZE + 2 * Config.BORDER_Y);
    }

    @Override
    public void setup() {
        app = this;
        grid = new Grid();
        shape = Shape.getNewShape((int) random(0, 3), 4, 4);
        //frameRate(Config.getFRAMERATE());
    }

    @Override
    public void draw() {
        int tempscore = 0;
        if (!keyPressed) {
            frameRate(Config.getFRAMERATE());
        }
        grid.drawGrid();
        if (shape.moveVertical(grid)) {
            // check if there are full Rows in the grid
            tempscore = grid.checkGrid();
            score += (tempscore * tempscore * 10);
            shape = Shape.getNewShape((int) random(0, 3), 4, 4);
        }
        //draw circle which hides the incoming objects
        {
            fill(0);
            noStroke();
            rect(0, 0, width, Config.BORDER_Y);
        }
        
        //score
        fill(255);
        textSize(16);
        text("score: " +score, 50, 60);
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
                case DOWN:
                    frameRate(Config.getFRAMERATE() * 3);
                    break;
                default:
                    frameRate(Config.getFRAMERATE());
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
