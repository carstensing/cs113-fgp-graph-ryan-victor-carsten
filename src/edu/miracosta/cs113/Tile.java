package edu.miracosta.cs113;

import java.awt.*;

public class Tile {
    private Color color;
    private int width;
    private int height;

    public Tile(Color color) {
        this.color = color;
        this.width = 5;
        this.height = 5;
    }

    public Tile(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
