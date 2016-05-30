package shape;

import config.Config;
import ui.Grid;

/**
 * Created by yanni on 02.05.2016
 * model for all shapes
 */

public abstract class Shape {

    protected static final int GRIDSIZE = Config.GRIDSIZE;
    protected static Grid grid = Grid.getInstance();

    private Rectangle[] rectangles = new Rectangle[4];
    private int rotation;

    protected Shape(int startX, int startY) {
        this.rotation = 0;
        initialize(startX, startY);
        for (Rectangle rectangle : rectangles) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
    }

    //***** abstract methods

    /**
     * rotates the shape
     */
    public abstract void rotate();

    /**
     * initializes all the rectangles and arranges them for the initial spawn
     * @param row spawning row point of the lowest left rectangle
     * @param collumn spawning collumn point of the lowest left rectangle
     */
    protected abstract void initialize(int row, int collumn);

    /**
     * checks if the Line can move down
     * @return true if the shape can move down, false if not
     */
    protected abstract boolean canMoveDown();

    /**
     * checks if the Line can move right
     * @return true if the shape can move right, false if not
     */
    public abstract boolean canMoveRight();

    /**
     * checks if the Line can move down
     * @return true if the shape can move left, false if not
     */
    public abstract boolean canMoveLeft();

    //***** API

    /**
     * this method creates a new shape with the given parameter
     * @param random random number to create shape
     * @return Shape object
     */
    public static Shape getNewShape(int random, int startX, int startY) {
        switch (random) {
            case 0:
                return new Line(startX, startY);
            case 1:
                return new Step(startX, startY);
            case 2:
                return new Cube(startX, startY);
            default:
                return null;
        }
    }

    /**
     * moves the Shape object down
     * @return true if object is at the bottom of the grid
     */
    public boolean moveVertical(Grid grid) {
        if (!canMoveDown()) {
            return true;
        }
        for (Rectangle rectangle : rectangles) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
        }
        move(0, 1);
        for (Rectangle rectangle : rectangles) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
        return false;
    }

    /**
     * moves the object left or right, depending on the boolean param
     * @param left move the to left = true, move to right = false
     */
    public void moveHorizontal(boolean left) {
        if (left) {
            if(!canMoveLeft()) {
                return;
            }
            move(-1, 0);
        } else {
            if(!canMoveRight()) {
                return;
            }
            move(1, 0);
        }
    }

    //***** getter & setter
    // getter have public access, setter can just be accessed inside the package

    public Rectangle getRectangle(int index) {
        return rectangles[index];
    }

    public Rectangle[] getAllRectangles() {
        return rectangles;
    }

    protected void setRectangle(int index, Rectangle rectangle) {
        rectangles[index] = rectangle;
    }

    public int getRotation() {
        return this.rotation;
    }

    protected void setRotation(int rotation) {
        this.rotation = rotation;
    }


    /**
     * moves the shape depending on the x and y coordinates in param
     * @param x value for the movement of the x coordinate
     * @param y value for the movement of the y coordinate
     */
    private void move(int x, int y) {
        for (Rectangle rectangle : rectangles) {
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
            rectangle.setRow(rectangle.getRow() + x);
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
        for (Rectangle rectangle : rectangles) {
            //if (rectangle.getCollumn() + y <= 23) {
                Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
                rectangle.setCollumn(rectangle.getCollumn() + y);
                Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
            //}
        }
    }
}
