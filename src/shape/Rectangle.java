package shape;

import processing.core.PApplet;
import ui.UI;

/**
 * Created by yanni on 02.05.2016
 * creates a rectangle which is used to build any shape
 * this class cannot be accessed outside of the package
 */

public class Rectangle {

    //***** static attributes
    private static PApplet app = UI.getInstance();

    //***** attributes
    public final int SHAPE_ID;
    private int x;
    private int y;
    private int size;
    private int row;
    private int collumn;

    //***** consturctor
    protected Rectangle(int x, int y, int size, int ID) {
        this.SHAPE_ID = ID;
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = x;
        this.collumn = y;
    }

    //***** getter & setter
    protected void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getRow(){
        return this.row;
    }

    protected void setRow(int row){
        this.row = row;
    }

    public int getCollumn(){
        return this.collumn;
    }

    protected void setCollumn(int collumn){
        this.collumn = collumn;
    }

}