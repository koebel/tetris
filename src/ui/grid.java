package ui;

/**
 * Created by kathrinkoebel on 02.05.16
 */

import processing.core.*;

public class Grid {

    private static PApplet app = UI.getInstance();
    private static int width = 10;
    private static int height = 20;

    private int [][] matrix = new int[width][height];

    public Grid(){
        // matrix initialisieren
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void drawGrid() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]>=1) {
                    app.fill(255,0,0);
                }
                app.rect(i*UI.getSizeRect()+200, j*UI.getSizeRect()+50, UI.getSizeRect(), UI.getSizeRect());
            }
        }

    }

    public static int getGridHeight(){
        return height;
    }

    public static int getGridWidth(){
        return width;
    }

}