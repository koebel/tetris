package shape;

import ui.UI;

/**
 * Created by yanni on 02.05.2016
 * model for all shapes
 */

public abstract class Shape {

    protected static final int GRIDSIZE = UI.getSizeRect();

    private Rectangle[] rectangles = new Rectangle[4];
    private int rotation;

    protected Shape(int startX, int startY) {
        this.rotation = 0;
        initialize(startX, startY);
    }

    //***** abstract methods

    public abstract void rotate();

    protected abstract void initialize(int startX, int startY);

    //***** API

    /**
     * this method creates a new shape with the given parameter
     * @param random random number
     * @return Shape object
     */
    public static Shape getNewShape(int random, int startX, int startY){
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
     * draws the shape
     */
    public void drawShape() {
        for (Rectangle rectangle : rectangles) {
            rectangle.drawRect();
        }
    }

    /**
     * moves the Shape object down
     */
    public void moveVertical() {
        move(0, GRIDSIZE);
    }

    /**
     * moves the object left or right, depending on the boolean param
     * @param left move the to left = true, move to right = false
     */
    public void moveHorizontal(boolean left) {
        if (left) {
            move(-GRIDSIZE, 0);
        } else {
            move(GRIDSIZE, 0);
        }
    }

    //***** getter & setter

    protected Rectangle getRectangle(int index) {
        return rectangles[index];
    }

    protected Rectangle[] getAllRectangles() {
        return rectangles;
    }

    protected void setRectangle(int index, Rectangle rectangle) {
        rectangles[index] = rectangle;
    }

    protected int getRotation() {
        return this.rotation;
    }

    protected void setRotation(int rotation){
        this.rotation = rotation;
    }


    /**
     * helps with simplifying the movment of the shape
     * @param x value for the movement of the x coordinate
     * @param y value for the movement of the y coordinate
     */
    private void move(int x, int y) {
        for (Rectangle rectangle : rectangles) {
            rectangle.setY(rectangle.getY() + y);
        }
        for (Rectangle rectangle : rectangles) {
            rectangle.setX(rectangle.getX() + x);
        }
    }
}
