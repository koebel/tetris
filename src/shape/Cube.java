package shape;

import config.Config;

/**
 * Created by yanni on 03.05.2016, updated by kathrin on 5.6.2016
 * this class creates a cube with the size of 2x2 with 4 Rectangles
 */

public class Cube extends Shape {

    private static final int ID = 3;

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
        setRectangle(0, new Rectangle(row, collumn, GRIDSIZE, ID));
        setRectangle(1, new Rectangle(row + 1, collumn, GRIDSIZE, ID));
        setRectangle(2, new Rectangle(row, collumn -1, GRIDSIZE, ID));
        setRectangle(3, new Rectangle(row + 1, collumn-1, GRIDSIZE, ID));
    }

    @Override
    public boolean canMoveRight() {
        // only rectangles at the right side (1 & 3) need to be checked
        if (getRectangle(1).getRow() >= Config.ROWS-1 || grid.isOccupied(getRectangle(1).getRow()+ 1, getRectangle(1).getCollumn())) {
             return false;
         }
        if (getRectangle(3).getRow() >= Config.ROWS-1 || grid.isOccupied(getRectangle(3).getRow()+ 1, getRectangle(3).getCollumn())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canMoveLeft() {
        // only rectangles at the left side (0 & 2) need to be checked
        if (getRectangle(0).getRow() - 1 < 0 || grid.isOccupied(getRectangle(0).getRow() -1, getRectangle(0).getCollumn())) {
            return false;
        }
        if (getRectangle(2).getRow() - 1 < 0 || grid.isOccupied(getRectangle(2).getRow() - 1, getRectangle(2).getCollumn())) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean canMoveDown(){
        // only rectangles at the bottom (0 & 1) need to be checked
        if (getRectangle(0).getCollumn() >= Config.COLlUMNS-1 || grid.isOccupied(getRectangle(0).getRow(), getRectangle(0).getCollumn()+1)) {
            return false;
        }
        if (getRectangle(1).getCollumn() >= Config.COLlUMNS-1 || grid.isOccupied(getRectangle(1).getRow(), getRectangle(1).getCollumn()+1)){
        return false;
        }
        return true;
    }
}
