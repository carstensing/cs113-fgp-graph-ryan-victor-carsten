package edu.miracosta.cs113;


public class Enemy extends Entity {

    public Enemy() {
        super();
    }

    public Enemy(int x, int y) {
        super(x,y);
    }

    public void moveTowardsPlayer(Map map) {
        int[] path = map.getGraph().getPath(map.get1DIndex(getX(),getY()),map.get1DIndex(map.getPlayer().getX(),map.getPlayer().getY()));
        int[] nextMove = map.get2DIndex(path[0]);
        map.getTiles()[getX()][getY()] = Map.WALKABLE_TILE;
        this.setX(nextMove[0]);
        this.setY(nextMove[1]);
        map.getTiles()[getX()][getY()] = Map.ENEMY_TILE;
    }

    public void move(int dx, int dy, Map map) {
        if (getX() + dx < map.getRows() && getX() + dx > -1 && getY() + dy < map.getColumns() && getY() + dy > -1) {
            if (map.getTile(getX() + dx, getY() + dy) == Map.WALKABLE_TILE) {
                setX(getX() + dx);
                setY(getY() + dy);
                map.getTiles()[getX()][getY()] = Map.ENEMY_TILE;
                map.getTiles()[getX() - dx][getY() - dy] = Map.WALKABLE_TILE;
            }
        }
    }

}
