package shape;

import config.Config;
import ui.Grid;

/**
 * Created by yanni on 03.05.2016
 * this class creates a line piece with 4 Rectangles in a horizontal row
 */

public class Line extends Shape {

    //***** consturctor
    public Line(int startX, int startY) {
        super(startX, startY);
    }

    /**
     * rotates the Object
     */
    @Override
    public void rotate() {
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
     *
     * @param startX x coordinate of spawning point
     * @param startY y coordinate of spawning point
     */
    @Override
    protected void initialize(int startX, int startY) {
        for (int i = 0; i != 4; ++i) {
            setRectangle(i, new Rectangle(startX, startY -= 1, GRIDSIZE, Rectangle.BLUE));
        }
    }

    @Override
    protected boolean canMoveDown(Grid grid) {
        if (getRotation() == 0) {
            if (getRectangle(0).getCollumn() >= 23 || grid.isOccupied(getRectangle(0).getRow(), getRectangle(0).getCollumn() + 1)) {
                return false;
            }
        } else {
            for (Rectangle rectangle : getAllRectangles()) {
                if (rectangle.getCollumn() >= 23 || grid.isOccupied(rectangle.getRow(), rectangle.getCollumn() + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canMoveRight(Grid grid) {
        if (getRotation() == 0) {
            for (Rectangle rectangle : getAllRectangles()) {
                if (rectangle.getRow() + 1 >= Config.ROWS) {
                    return false;
                }
                if (grid.isOccupied(rectangle.getRow() + 1, rectangle.getCollumn())) {
                    return false;
                }
            }
            return true;
        } else {
            Rectangle[] subRectangles = {getRectangle(0), getRectangle(3)};
            for (Rectangle rectangle : subRectangles) {
                if (rectangle.getRow() + 1 < Config.ROWS) {
                    return false;
                }
                if (grid.isOccupied(rectangle.getRow() + 1, rectangle.getCollumn()) | rectangle.getRow() <= 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public boolean canMoveLeft(Grid grid) {
        if (getRotation() == 0) {
            for (Rectangle rectangle : getAllRectangles()) {
                if (rectangle.getRow() - 1 < 0) {
                    return false;
                }
                if (grid.isOccupied(rectangle.getRow() - 1, rectangle.getCollumn()) | rectangle.getRow() <= 0) {
                    return false;
                }
            }
            return true;
        } else {
            Rectangle[] subRectangles = {getRectangle(0), getRectangle(3)};
            for (Rectangle rectangle : subRectangles) {
                if (rectangle.getRow() - 1 > 0) {
                    return false;
                }
                if (grid.isOccupied(rectangle.getRow() - 1, rectangle.getCollumn()) | rectangle.getRow() <= 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
