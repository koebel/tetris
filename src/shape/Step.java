package shape;

import config.Config;
import ui.Grid;

/**
 * Created by yanni on 03.05.2016
 */

public class Step extends Shape {

    private static final int ID = 2;

    public Step(int startX, int startY) {
        super(startX, startY);
    }

    /**
     * rotates the Object
     */
    @Override
    public void rotate() {
        for (Rectangle rectangle : getAllRectangles()) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
        }
        switch(getRotation()) {
            case 0:
                setRectangle(0, new Rectangle(getRectangle(0).getRow(), getRectangle(0).getCollumn() - 2, GRIDSIZE, ID));
                setRectangle(1, new Rectangle(getRectangle(1).getRow(),getRectangle(1).getCollumn() ,GRIDSIZE, ID));
                setRectangle(2, new Rectangle(getRectangle(2).getRow(), getRectangle(2).getCollumn(), GRIDSIZE, ID));
                setRectangle(3, new Rectangle(getRectangle(3).getRow() - 2, getRectangle(3).getCollumn(), GRIDSIZE, ID));
                for (Rectangle rectangle : getAllRectangles()) {
                    grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), ID);
                }
                setRotation(1);
            }
        }

    /**
     * initializes the 4 needed Rectangles
     * @param startX x coordinate of spawning point
     * @param startY y coordinate of spawning point
     */
    @Override
    protected void initialize(int startX, int startY) {
        setRectangle(0, new Rectangle(startX, startY, GRIDSIZE, ID));
        setRectangle(1, new Rectangle(startX + 1, startY, GRIDSIZE, ID));
        setRectangle(2, new Rectangle(startX + 1, startY - 1, GRIDSIZE, ID));
        setRectangle(3, new Rectangle(startX + 2, startY - 1, GRIDSIZE, ID));
    }

    @Override
    protected boolean canMoveDown() {
        if (getRotation() == 0) {
            if (getRectangle(0).getCollumn() >= 23 || grid.isOccupied(getRectangle(0).getRow(), getRectangle(0).getCollumn() + 1)
                    || grid.isOccupied(getRectangle(1).getRow(), getRectangle(1).getCollumn() + 1)
                    || grid.isOccupied(getRectangle(3).getRow(), getRectangle(3).getCollumn() + 1)) {
                return false;
            }
        }
        else {
            if (getRectangle(1).getCollumn() >= 23 || grid.isOccupied(getRectangle(1).getRow(), getRectangle(1).getCollumn() + 1)
                    || grid.isOccupied(getRectangle(3).getRow(), getRectangle(3).getCollumn() + 1)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canMoveLeft() {
        return true;
    }

    @Override
    public boolean canMoveRight() {
        return true;
    }
}


