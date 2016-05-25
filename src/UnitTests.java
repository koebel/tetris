
import org.junit.*;
import shape.*;
import ui.*;

import static org.junit.Assert.*;

/**
 * Created by yanni on 16.05.2016
 */
public class UnitTests {

    static UI ui;

    static {
        ui = new UI();
        ui.setup();
    }

    @Test
    public void moveDownTest(){
        int start = 4;
        Shape testShape = new Line(5, start);
        Grid testGrid = new Grid();

        testShape.moveVertical(testGrid);

        System.out.println("moveDownTest ------------------------------ ");
        for(Rectangle rectangle : testShape.getAllRectangles()){
            System.out.println(rectangle.getCollumn());
        }

        Rectangle[] result = testShape.getAllRectangles();
        int[] expectedResults = {start--, start--, start--, start};
        for(int i = 0; i != expectedResults.length; ++i){
            assertEquals(expectedResults[i], result[i].getCollumn());
        }

        //checks if the last Rectangle of the Shape is free
        assertEquals(false, testGrid.isOccupied(5, expectedResults[3]-1));
        assertEquals(true, testGrid.isOccupied(5, expectedResults[2]));
    }

    @Test
    public void staysAtBottomTest(){
        int start = 20;
        Shape testShape = new Line(5, start);
        Grid testGrid = new Grid();

        while (start != 24){
            testShape.moveVertical(testGrid);
            ++start;
        }

        testShape.moveVertical(testGrid);

        System.out.println("moveDownTest ------------------------------ ");
        for(Rectangle rectangle : testShape.getAllRectangles()){
            System.out.println(rectangle.getCollumn());
        }

        Rectangle[] result = testShape.getAllRectangles();
        int[] expectedResults = {--start, --start, --start, --start};
        for(int i = 0; i != expectedResults.length; ++i){
            assertEquals(expectedResults[i], result[i].getCollumn());
        }
    }

    @Test
    public void isOccupiedTest(){
        Grid testGrid = new Grid();
        testGrid.setGridValue(5, 5, 1);
        assertEquals(true, testGrid.isOccupied(5, 5));
        assertEquals(false, testGrid.isOccupied(6, 6));
    }

    @Test
    public void moveHorizontalTest(){
        int start = 5;
        Shape testShape = new Line(start, 5);
        Grid testGrid = new Grid();

        testShape.moveHorizontal(true, testGrid);

        Rectangle[] result = testShape.getAllRectangles();
        int expectedResult = --start;
        for(int i = 0; i != result.length; ++i){
            assertEquals(expectedResult, result[i].getRow());
        }

        testShape.moveHorizontal(false, testGrid);

        result = testShape.getAllRectangles();
        expectedResult = ++start;
        for(int i = 0; i != result.length; ++i){
            assertEquals(expectedResult, result[i].getRow());
        }

        testShape.rotate();
        result = testShape.getAllRectangles();
        expectedResult = ++start;
        for(int i = 0; i != result.length; ++i){
            assertEquals(expectedResult, result[i].getRow());
        }
    }

    @Test
    public void canMoveRightTest(){
        Shape testShape = new Line(9, 5);
        Grid testGrid = new Grid();

        for(Rectangle rectangle : testShape.getAllRectangles()){
            System.out.println(rectangle.getRow());
        }

        assertEquals(false, testShape.canMoveRight(testGrid));

        testShape = new Line(4, 5);
        assertEquals(true, testShape.canMoveRight(testGrid));
    }

    @Test
    public void canMoveLeftTest(){
        Shape testShape = new Line(0, 5);
        Grid testGrid = new Grid();

        for(Rectangle rectangle : testShape.getAllRectangles()){
            System.out.println(rectangle.getRow());
        }

        assertEquals(false, testShape.canMoveLeft(testGrid));

        testShape = new Line(4, 5);
        assertEquals(true, testShape.canMoveLeft(testGrid));

        testShape = new Line(1, 5);
        testShape.rotate();
        System.out.println(testShape.getAllRectangles()[0].getRow() + " " + testShape.getAllRectangles()[0].getCollumn());
        assertEquals(false, testShape.canMoveLeft(testGrid));
    }

    @Test
    public void rotationLineTest(){
        Shape testShape = new Line(5, 8);
        int[] expectedRowResults = {4, 5, 6, 7};
        int[] initalCollumnPositons = {7, 6, 5, 4};

        System.out.println("rotationLineTest ----------------------------------");
        for (Rectangle rectangle : testShape.getAllRectangles()){
            System.out.println(rectangle.getRow() + " " + rectangle.getCollumn());
        }
        testShape.rotate();
        System.out.println("rotationLineTest Result ----------------------------------");
        for (Rectangle rectangle : testShape.getAllRectangles()){
            System.out.println(rectangle.getRow() + " " + rectangle.getCollumn());
        }

        Rectangle[] results = testShape.getAllRectangles();
        for(int i = 0; i != results.length; ++i){
            assertEquals(expectedRowResults[i], results[i].getRow());
        }

        testShape.rotate();
        results = testShape.getAllRectangles();
        for(int i = 0; i != results.length; ++i){
            assertEquals(initalCollumnPositons[i], results[i].getCollumn());
        }
    }

}
