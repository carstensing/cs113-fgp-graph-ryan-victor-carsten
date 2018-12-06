package edu.miracosta.cs113;


public class Entity {
    private int x;
    private int y;

    public Entity(){
        this.x = 0;
        this.y = 0;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy, Map map) {
        if (x + dx < map.getWidth() && x + dx > -1 && y + dy < map.getHeight() && y + dy > -1) {
            if (map.getTile(x + dx, y + dy) == Map.WALKABLE_TILE) {
                this.x = x + dx;
                this.y = y + dy;
                map.getTiles()[x][y] = Map.PLAYER_TILE;
                map.getTiles()[x - dx][y - dy] = Map.WALKABLE_TILE;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
