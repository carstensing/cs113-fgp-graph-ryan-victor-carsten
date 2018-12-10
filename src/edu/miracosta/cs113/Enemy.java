package edu.miracosta.cs113;


public class Enemy extends Entity {
    private Timer timer; //Timer used to delay enemy movements
    private double speed = 0.20; // Lower is faster

    /**
     * Default Constructor
     */
    public Enemy() {
        super();
        timer = new Timer();
    }

    /**
     * Full Constructor
     *
     * @param x x position
     * @param y y position
     */
    public Enemy(int x, int y) {
        super(x,y);
        timer = new Timer();
    }

    /**
     * To be called in Game's update method.
     * Will move enemy towards the player if enough time has passed, based on Timer.
     *
     * @param map Map containing enemy
     */
    public void update(Map map) {
        float elapsedTime;
        elapsedTime = timer.getElapsedTime();
        timer.setAutoMoveTime(elapsedTime);
        if (timer.getAutoMoveTime() > speed) {
            timer.resetAutoMoveTime();
            //If Enemy is next to player, then eat / kill player
            //else moveTowardsPlayer(map)
            moveTowardsPlayer(map);
        }


    }


    /**
     * Moves towards the player based on Dijkstra's algorithm
     *
     * @param map Map containing Enemy and Player
     */
    public void moveTowardsPlayer(Map map) {
        int[] path = map.getGraph().getPath(map.get1DIndex(getX(),getY()),map.get1DIndex(map.getPlayer().getX(),map.getPlayer().getY()));
        int[] nextMove = map.get2DIndex(path[0]);
        move(nextMove[0]- getX(),nextMove[1] - getY(), map);
    }

    /**
     * Error checks for out of bounds. Can only move onto a walkable tile.
     *
     * @param dx Change in X position
     * @param dy Change in Y position
     * @param map Map containing this Entity
     */
    public void move(int dx, int dy, Map map) {
        if (getX() + dx < map.getRows() && getX() + dx > -1 && getY() + dy < map.getColumns() && getY() + dy > -1) {
            if (map.getTile(getX() + dx, getY() + dy) == Map.WALKABLE_TILE || map.getTile(getX() + dx, getY() + dy) == Map.PLAYER_TILE) {
                setX(getX() + dx);
                setY(getY() + dy);
                map.getTiles()[getX()][getY()] = Map.ENEMY_TILE;
                map.getTiles()[getX() - dx][getY() - dy] = Map.WALKABLE_TILE;
            }
        }
    }

}
