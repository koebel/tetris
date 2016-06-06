package shape;

import config.Config;

/**
 * Created by yanni on 03.05.2016
 * this class creates a line piece with 4 Rectangles in a horizontal row
 */

public class Line extends Shape {

    private static final int ID = 1;

    //***** consturctor
    public Line(int startX, int startY) {
        super(startX, startY);
    }

    /**
     * rotates the Object
     */
    @Override
    public void rotate() {
        if(getRotation() == 1){
            if(getRectangle(0).getRow() < 0 | getRectangle(3).getRow() > Config.ROWS - 1){
                return;
            }
        } else {
            if (getRectangle(0).getRow() <= 0 | getRectangle(0).getRow() >= Config.ROWS - 2) {
                return;
            }
            if(!canMoveLeft() | !canMoveRight() ){
                return;
            }
        }
        for (Rectangle rectangle : getAllRectangles()) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
        }
        if (getRotation() == 0) {
            int xMovement = -1;
            int i = 0;
            for (Rectangle rectangle : getAllRectangles()) {
                setRectangle(i++, new Rectangle(rectangle.getRow() + xMovement, rectangle.getCollumn() + xMovement, 40, ID));
                ++xMovement;
            }
        } else {
            int yMovement = 0;
            int i = 0;
            for (Rectangle rectangle : getAllRectangles()) {
                setRectangle(i++, new Rectangle(rectangle.getRow() + yMovement, rectangle.getCollumn() + yMovement, 40, ID));
                rectangle.setPosition(rectangle.getRow() + yMovement, rectangle.getCollumn() + yMovement);
                --yMovement;
            }
        }
        for (Rectangle rectangle : getAllRectangles()) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
        setRotation(getRotation() == 1 ? 0 : 1);
    }

    /**
     * initializes the 4 needed Rectangles
     *
     * @param row     x coordinate of spawning point
     * @param collumn y coordinate of spawning point
     */
    @Override
    protected void initialize(int row, int collumn) {
        for (int i = 0; i != 4; ++i) {
            setRectangle(i, new Rectangle(row, collumn -= 1, GRIDSIZE, ID));
        }
    }

    @Override
    protected boolean canMoveDown() {
        //just check the rectangle at the bottom if the Line is not rotated
        if (getRotation() == 0) {
            if (getRectangle(0).getCollumn() >= 23 || grid.isOccupied(getRectangle(0).getRow(), getRectangle(0).getCollumn() + 1)) {
                return false;
            }
        } else {
            //check every rectangel if the Line is rotated
            for (Rectangle rectangle : getAllRectangles()) {
                if (rectangle.getCollumn() >= 23 || grid.isOccupied(rectangle.getRow(), rectangle.getCollumn() + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canMoveRight() {
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
            Rectangle rectangle = getRectangle(3);
            if (rectangle.getRow() == Config.ROWS - 1 || grid.isOccupied(rectangle.getRow() + 1, rectangle.getCollumn())) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean canMoveLeft() {
        if (getRotation() == 0) {
            for (Rectangle rectangle : getAllRectangles()) {
                if (rectangle.getRow() - 1 < 0 || grid.isOccupied(rectangle.getRow() - 1, rectangle.getCollumn())) {
                    return false;
                }
            }
            return true;
        } else {
            Rectangle rectangle = getRectangle(0);
            if (rectangle.getRow() == 0 || grid.isOccupied(rectangle.getRow() - 1, rectangle.getCollumn())) {
                System.out.println(true);
                return false;
            }
        }
        return true;
    }
}
