/**
 * Created by kathrinkoebel on 02.05.16.
 */
import processing.core.PApplet;

public class grid extendes PApplet {
    int width = 10;
    int height = 20;
    int [][] matrix = new int[width][height];

    public void setup() {

    }

    // matrix initialisieren
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[i][j] = 0;
        }
    }

    public void draw() {
        background(255);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j]>=1)
                fill(255,0,0);
                rect(i*ui.rectSize, j*ui.rectSize, rectSize, rectSize);
            }
        }

    }

}