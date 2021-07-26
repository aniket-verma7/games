package com.application.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Grid {
    private int i,j,w,h;
    private Rectangle rectangle;
    private boolean clicked;
    public Grid(int i, int j, int w, int h) {
        this.i = i;
        this.j = j;
        this.w = w;
        this.h = h;
        this.clicked = false;
        setRectangleProperty();
    }

    private void setRectangleProperty()
    {
        this.rectangle = new Rectangle(i*w,j*h,w,h);
        this.rectangle.setFill(Color.LIGHTYELLOW);
        this.rectangle.setOpacity(2);
        this.rectangle.setStroke(Color.BLACK);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void reset() {
        this.rectangle.setFill(Color.LIGHTYELLOW);
        this.rectangle.setOpacity(2);
        this.rectangle.setStroke(Color.BLACK);
    }

    public boolean equals(Paint color) {

        return rectangle.getFill() == color;
    }
}

