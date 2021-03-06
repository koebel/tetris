package ui;

/**
 * Created by kathrinkoebel on 02.05.16
 */

import config.Config;
import processing.core.PApplet;

public class Grid {

    private static PApplet app = UI.getInstance();
    private static Grid grid;

    // color matrix for up to 10 colorsets test
    private int fillColor = 9;

    private int[][] colors = {
            {0, 0, 0},
            {255, 0, 0},
            {255, 255, 0},
            {0, 255, 0},
            {0, 255, 255},
            {0, 0, 255},
            {255, 0, 255},
            {255, 255, 255},
            {180, 180, 180},
            {100, 100, 100}
    };

    private int[][] matrix = new int[Config.ROWS][Config.COLlUMNS];

    public Grid() {
        System.out.println("Check");
        if(grid == null){
            grid = this;
        } else {
            throw new RuntimeException("Singelton class can only be initialized once");
        }
        // matrix initialisieren
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void drawGrid() {
        int current;
        app.background(0);
        app.fill(0);
        //app.stroke(255);
        app.stroke(colors[fillColor][0], colors[fillColor][1], colors[fillColor][2]);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0) {
                    current = matrix[i][j];
                    app.fill(colors[current][0], colors[current][1], colors[current][2]);
                } else {
                    app.fill(colors[0][0], colors[0][1], colors[0][2]);
                }
                app.rect(i * Config.GRIDSIZE + Config.BORDER_X, j * Config.GRIDSIZE + Config.BORDER_Y, Config.GRIDSIZE, Config.GRIDSIZE);
            }
        }
    }

    // method to check if there are any full rows in the grid and delete them
    // returns how many full rows have been deleted
    public int checkGrid() {
        int currentrow = Config.COLlUMNS - 1;
        int occupiedFields = 0;
        int rowsDeleted = 0;

        while (currentrow > 3) {
            // check row
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][currentrow] > 0)
                    occupiedFields++;
            }

            // if row is fully occupied shift content one row down
            // then check the same row again because content has been shifted
            if (occupiedFields == Config.ROWS) {
                shiftRow(currentrow);
                rowsDeleted++;
            }
            // if row was not full check next row
            else {
                currentrow--;
            }
            occupiedFields = 0;
        }
        return rowsDeleted;
    }

    // shifts the content of the grid one row up from the bottom to the top
    // starting with the row specified as rowindex
    public void shiftRow(int rowindex) {
        for (int i = rowindex; i > 1; i--) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = matrix[j][i - 1];
            }
        }
    }

    public void setGridValue(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public void clear() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        if (matrix[x][y] != 0) {
            for (int i = x; i < matrix[x].length; i++) {
                if (matrix[x][i] != 0 && matrix[x][0] != 0) {
                    //Game Over Test
                    app.fill(255);
                    app.textSize(100);
                    app.text("Game Over", app.width / 2 - 260, app.height / 2);
                    app.noLoop();
                    return true;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static Grid getInstance() {
        return grid;
    }

}