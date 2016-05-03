package shape;

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
        final int[] rect1 = {getRectangle(1).getX(), getRectangle(1).getY()};
        if (getRotation() % 2 == 0) {
            int xMovement = -GRIDSIZE;
            for (Rectangle rectangle : getAllRectangles()) {
                rectangle.setY(rect1[1]);
                rectangle.setX(rect1[0] + xMovement);
                xMovement += GRIDSIZE;
            }
        } else {
            int yMovement = -GRIDSIZE;
            for (Rectangle rectangle : getAllRectangles()) {
                rectangle.setY(rect1[1] + yMovement);
                rectangle.setX(rect1[0]);
                yMovement += GRIDSIZE;
            }
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
        for (int i = 0; i != 4; ++i) {
            setRectangle(i, new Rectangle(startX, startY -= GRIDSIZE, GRIDSIZE, Rectangle.BLUE));
        }
    }
}
