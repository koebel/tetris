package ui;

/**
 * Created by kathrinkoebel on 02.05.16
 */

import config.Config;
import processing.core.*;
import java.util.TreeMap;

public class Grid {

    private static PApplet app = UI.getInstance();
    public static Grid grid;

    private int [][] matrix = new int[Config.ROWS][Config.COLlUMNS];
    private TreeMap<Integer, String> coordinates;

    public Grid(){
        grid = this;
        coordinates = new TreeMap<>();
        // matrix initialisieren
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void drawGrid() {
        app.background(0);
        app.fill(0);
        app.stroke(255);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]>=1) {
                    app.fill(255,0,0);
                } else {
                    app.fill(0);
                }
                app.rect(i*Config.GRIDSIZE+Config.BORDER_X, j*Config.GRIDSIZE+Config.BORDER_Y, Config.GRIDSIZE, Config.GRIDSIZE);
            }
        }
    }

    public void setGridValue(int x, int y, int value){
        matrix[x][y] = value;
    }

    public boolean isOccupied(int x, int y){
        if(matrix[x][y] != 0){
            return true;
        } else {
            return false;
        }
    }

}