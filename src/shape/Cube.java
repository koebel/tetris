package shape;

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
     * @param startX x coordinate of spawning point
     * @param startY y coordinate of spawning point
     */
    @Override
    protected void initialize(int startX, int startY) {
        setRectangle(0, new Rectangle(startX, startY, GRIDSIZE, Rectangle.RED));
        setRectangle(1, new Rectangle(startX, startY - GRIDSIZE, GRIDSIZE, Rectangle.RED));
        setRectangle(2, new Rectangle(startX + GRIDSIZE, startY, GRIDSIZE, Rectangle.RED));
        setRectangle(3, new Rectangle(startX + GRIDSIZE, startY - GRIDSIZE, GRIDSIZE, Rectangle.RED));
    }
}
