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
    protected static final int RED = app.color(255, 0, 0);
    protected static final int BLUE = app.color(0, 255, 255);
    protected static final int YELLOW = app.color(255, 255, 0);
    protected static final int VIOLETT = app.color(153, 0, 255);

    //***** attributes
    private int x;
    private int y;
    private int size;
    private int color;

    //***** consturctor
    protected Rectangle(int x, int y, int size, int color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    //***** protected methods
    protected void drawRect() {
        app.fill(color);
        app.rect(this.x, this.y, this.size, this.size);
    }

    //***** getter & setter
    protected void setX(int x) {
        this.x = x;
    }

    protected int getX() {
        return this.x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected int getY() {
        return this.y;
    }

    protected void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}