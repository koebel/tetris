package ui;

/**
 * Created by kathrinkoebel on 02.05.16
 */

import config.Config;
import processing.core.*;

public class Grid {

    private static PApplet app = UI.getInstance();
    public static Grid grid;

    // color matrix for up to 10 colorsets
    private int maxColors = 10;

    private int [][] colors = {   {0, 0, 0},
                                 {255, 0, 0},
                                 {0, 255, 0},
                                 {0, 0, 255}
                                 };

    private int [][] matrix = new int[Config.ROWS][Config.COLlUMNS];

    public Grid(){
        grid = this;
        // matrix initialisieren
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void drawGrid() {
        int current;
        app.background(0);
        app.fill(0);
        app.stroke(255);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j]>0) {
                    current = matrix [i][j];
                    //app.fill(255,0,0);
                    app.fill(colors[current][0],colors[current][1],colors[current][2]);
                } else {
                    //app.fill(0);
                    app.fill(colors[0][0],colors[0][1],colors[0][2]);

                }
                app.rect(i*Config.GRIDSIZE+Config.BORDER_X, j*Config.GRIDSIZE+Config.BORDER_Y, Config.GRIDSIZE, Config.GRIDSIZE);
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
                shiftRow (currentrow);
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
    public void shiftRow(int rowindex){
        for (int i = rowindex; i>1; i--) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = matrix[j][i - 1];
            }
        }
    }

    public void setGridValue(int x, int y, int value){
        int copy = matrix[x][y];
        try{
            matrix[x][y] = value;
        } catch (ArrayIndexOutOfBoundsException e){
            matrix[x][y] = copy;
        }
    }

    public boolean isOccupied(int x, int y){
        if(matrix[x][y] != 0){
            return true;
        } else {
            return false;
        }
    }

    public void setGridColor (int pos, int r, int g, int b){
        if(pos >=0 && pos < maxColors){
            colors[pos][0] = r;
            colors[pos][1] = g;
            colors[pos][2] = b;
        }
    }

}
