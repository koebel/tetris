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

    /*
    private int [][] colors = new int [maxColors][3];
    // black
    setGridColor(0, 0, 0, 0);
    // white
    setGridColor(1, 255, 255, 255);
    // red
    setGridColor(2, 255, 0, 0);
    // yellow
    setGridColor(3, 255, 255, 0);
    // green
    setGridColor(4, 0, 255, 0);
    // cyan
    setGridColor(5, 0, 255, 255);
    // blue
    setGridColor(6, 0, 0, 255);
    // pink
    setGridColor(7, 255, 0, 255);
    // grey
    setGridColor(8, 120, 120, 120);
    */

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
