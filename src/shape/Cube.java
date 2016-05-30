package shape;

import ui.Grid;

/**
 * Created by yanni on 03.05.2016
 * this class creates a cube with the size of 2x2 with 4 Rectangles
 */

public class Cube extends Shape {

    //***** constructor
    public Cube(int startX, int startY) {
        super(startX, startY);
    }

    /**
     * no code because a cube cannot rotate
     */
    @Override
    public void rotate() {
        return;
    }

    /**
     * initializes the 4 needed Rectangles
     * @param row x coordinate of spawning point
     * @param collumn y coordinate of spawning point
     */
    @Override
    protected void initialize(int row, int collumn) {
        setRectangle(0, new Rectangle(row, collumn, GRIDSIZE, Rectangle.RED));
        setRectangle(1, new Rectangle(row, collumn - GRIDSIZE, GRIDSIZE, Rectangle.RED));
        setRectangle(2, new Rectangle(row + GRIDSIZE, collumn, GRIDSIZE, Rectangle.RED));
        setRectangle(3, new Rectangle(row + GRIDSIZE, collumn - GRIDSIZE, GRIDSIZE, Rectangle.RED));
    }

    @Override
    public boolean canMoveRight() {
        return false;
    }

    @Override
    public boolean canMoveLeft() {
        return false;
    }

    @Override
    protected boolean canMoveDown(){
        return true;
    }
}
