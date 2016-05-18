package shape;

import ui.Grid;

/**
 * Created by yanni on 03.05.2016
 */

public class Step extends Shape {

    public Step(int startX, int startY) {
        super(startX, startY);
    }

    /**
     * rotates the Object
     */
    @Override
    public void rotate() {
        if (getRotation() == 0) {
            getRectangle(0).setPosition(getRectangle(0).getX(), getRectangle(0).getY() + GRIDSIZE);
            getRectangle(1).setPosition(getRectangle(1).getX() - GRIDSIZE, getRectangle(1).getY());
            getRectangle(2).setPosition(getRectangle(2).getX() - 2 * GRIDSIZE, getRectangle(2).getY() + GRIDSIZE);
            getRectangle(3).setPosition(getRectangle(3).getX() - 3 * GRIDSIZE, getRectangle(3).getY());
        } else {
            getRectangle(0).setPosition(getRectangle(0).getX(), getRectangle(0).getY() - GRIDSIZE);
            getRectangle(1).setPosition(getRectangle(1).getX() + GRIDSIZE, getRectangle(1).getY());
            getRectangle(2).setPosition(getRectangle(2).getX() + 2 * GRIDSIZE, getRectangle(2).getY() - GRIDSIZE);
            getRectangle(3).setPosition(getRectangle(3).getX() + 3 * GRIDSIZE, getRectangle(3).getY());
        }
        setRotation(getRotation() == 1 ? 0 : 1);
    }

    /**
     * initializes the 4 needed Rectangles
     * @param startX x coordinate of spawning point
     * @param startY y coordinate of spawning point
     */
    @Override
    protected void initialize(int startX, int startY) {
        setRectangle(0, new Rectangle(startX, startY, GRIDSIZE, Rectangle.YELLOW));
        setRectangle(1, new Rectangle(startX + GRIDSIZE, startY, GRIDSIZE, Rectangle.YELLOW));
        setRectangle(2, new Rectangle(startX + GRIDSIZE, startY - GRIDSIZE, GRIDSIZE, Rectangle.YELLOW));
        setRectangle(3, new Rectangle(startX + 2 * GRIDSIZE, startY - GRIDSIZE, GRIDSIZE, Rectangle.YELLOW));
    }

    @Override
    protected boolean canMoveDown(Grid grid){
        return true;
    }
}


