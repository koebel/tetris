package shape;

import config.Config;
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
        if (getRotation() == 0 & (getRectangle(0).getRow() <= 0 | getRectangle(0).getRow() >= Config.ROWS - 2)) {
            return;
        } else if (getRectangle(0).getRow() <= 0 | getRectangle(3).getRow() >= Config.ROWS - 2) {
            return;
        }
        for (Rectangle rectangle : getAllRectangles()) {
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
        }
        if (getRotation() == 0) {
            int xMovement = -1;
            int i = 0;
            for (Rectangle rectangle : getAllRectangles()) {
                setRectangle(i++, new Rectangle(rectangle.getRow() + xMovement, rectangle.getCollumn() + xMovement, 40, Rectangle.BLUE));
                ++xMovement;
            }
        } else {
            int yMovement = 1;
            int i = 0;
            for (Rectangle rectangle : getAllRectangles()) {
                setRectangle(i++, new Rectangle(rectangle.getRow() + yMovement, rectangle.getCollumn() + yMovement, 40, Rectangle.BLUE));
                rectangle.setPosition(rectangle.getRow() + yMovement, rectangle.getCollumn() + yMovement);
                --yMovement;
            }
        }
        for (Rectangle rectangle : getAllRectangles()) {
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
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
        setRectangle(1, new Rectangle(startX + 1, startY, GRIDSIZE, Rectangle.YELLOW));
        setRectangle(2, new Rectangle(startX + 1, startY - 1, GRIDSIZE, Rectangle.YELLOW));
        setRectangle(3, new Rectangle(startX + 2, startY - 1, GRIDSIZE, Rectangle.YELLOW));
    }

    @Override
    protected boolean canMoveDown() {
        if (getRotation() == 0) {
            if (getRectangle(0).getCollumn() >= 23 || grid.isOccupied(getRectangle(0).getRow(), getRectangle(0).getCollumn() + 1)) {
                return false;
            }
        }
        else if(getRotation() == 1) {

        }
        else if(getRotation() == 2) {

        }
        else {
            for (Rectangle rectangle : getAllRectangles()) {
                if (rectangle.getCollumn() >= 23 || grid.isOccupied(rectangle.getRow(), rectangle.getCollumn() + 1)) {
                    return false;
                }
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


