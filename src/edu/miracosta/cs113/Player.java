package edu.miracosta.cs113;

public class Player extends Entity {
    public Player(int x, int y) {
        super(x,y);
    }

    public void move(int dx, int dy, Map map) {
        if (getX() + dx < map.getRows() && getX() + dx > -1 && getY() + dy < map.getColumns() && getY() + dy > -1) {
            if (map.getTile(getX() + dx, getY() + dy) == Map.WALKABLE_TILE) {
                setX(getX() + dx);
                setY(getY() + dy);
                map.getTiles()[getX()][getY()] = Map.PLAYER_TILE;
                map.getTiles()[getX() - dx][getY() - dy] = Map.WALKABLE_TILE;
            }
        }
    }
}
