package edu.miracosta.cs113;

public class Entity {
    private int x;
    private int y;

    public void move(int dx, int dy, Map map) {
        if (x + dx < map.getWidth() && x + dx > 0 && y + dy < map.getHeight() && y + dy > 0) {
            if (map.getTile(x + dx, y + dy) != Map.BARRIER_TILE) {
                map.getTiles()[x + dx][y + dy] = Map.PLAYER_TILE;
                map.getTiles()[x][y] = Map.WALKABLE_TILE;
                this.x = x + dx;
                this.y = y + dy;
            }
        }
    }
}
