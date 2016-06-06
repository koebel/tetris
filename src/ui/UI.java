package ui;

/**
 * Created by marcoghilardelli on 02.05.16
 * this class is a Singleton and should only be accessed with the method 'getInstance()'
 */

import config.Config;
import processing.core.PApplet;
import shape.Shape;
import shape.Step;

public class UI extends PApplet {

    public static void main(String args[]) {
        PApplet.main(new String[]{"--present", UI.class.getName()});
    }

    private static PApplet app;
    private Shape shape;
    private Grid grid;
    private int score = 0;
    private int lastShape;

    //@Override
    public void settings() {
        size(Config.ROWS * Config.GRIDSIZE + 2 * Config.BORDER_X, Config.COLlUMNS * Config.GRIDSIZE + 2 * Config.BORDER_Y);
    }

    @Override
    public void setup() {
        app = this;
        grid = new Grid();
        shape = Shape.getNewShape((int) random(0, 3));
        frameRate(Config.getFRAMERATE());
    }

    @Override
    public void draw() {
        int tempscore;
        if (!keyPressed) {
            frameRate(Config.getFRAMERATE());
        }
        grid.drawGrid();
        if (shape.moveVertical()) {
            // check if there are full Rows in the grid
            tempscore = grid.checkGrid();
            score += (tempscore * tempscore * 10);
            int random = (int)random(0,3);
            if(random == lastShape){
                switch (random){
                    case 2:
                        --random;
                        break;
                    case 1:
                        ++random;
                        break;
                    case 0:
                        ++random;
                        break;
                }
            }
            shape = Shape.getNewShape(random);
            lastShape = random;
        }
        //draw circle which hides the incoming objects
        {
            fill(0);
            noStroke();
            rect(0, 0, width, Config.BORDER_Y + (2 * Config.GRIDSIZE));
        }

        //score
        fill(255);
        textSize(20);
        text("score: " + score, 50, 60);
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
                    if(shape.getClass() == Step.class) {
                        if(shape.canMoveRight())
                            shape.rotate();
                    }
                    else {
                        shape.rotate();
                    }
                    break;
                case DOWN:
                    frameRate(Config.getFRAMERATE() * 3);
                    break;
                case UP:
                    grid.clear();
                    shape = Shape.getNewShape((int)random(0,3));
                    loop();
                    break;
                default:
                    frameRate(Config.getFRAMERATE());
            }
        }
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
