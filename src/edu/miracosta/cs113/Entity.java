package edu.miracosta.cs113;

public class Entity {
    private int x;
    private int y;

    public void move(int dx, int dy, Map map) {
        if (x + dx < map.getWidth() && x + dx > 0 && y + dy < map.getHeight() && y + dy > 0) {

        }
    }
}
