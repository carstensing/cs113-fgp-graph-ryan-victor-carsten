package edu.miracosta.cs113;


public class Enemy extends Entity {
    Timer timer;

    public Enemy() {
        super();
        timer = new Timer();
    }

    public Enemy(int x, int y) {
        super(x,y);
        timer = new Timer();
    }

    public void update(Map map) {
        float elapsedTime;
        elapsedTime = timer.getElapsedTime();
        timer.setAutoMoveTime(elapsedTime);
        if (timer.getAutoMoveTime() > 0.20) {
            timer.resetAutoMoveTime();
            //If Enemy is next to player, then eat / kill player
            //else moveTowardsPlayer(map)
            moveTowardsPlayer(map);
        }


    }

    public void moveTowardsPlayer(Map map) {
        int[] path = map.getGraph().getPath(map.get1DIndex(getX(),getY()),map.get1DIndex(map.getPlayer().getX(),map.getPlayer().getY()));
        int[] nextMove = map.get2DIndex(path[0]);
        move(nextMove[0]- getX(),nextMove[1] - getY(), map);
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
