package edu.miracosta.cs113;

public class Player extends Entity {

    private boolean useItem = false;

    /**
     * Default Constructor
     */
    public Player() {
        super();
    }
    /**
     * Full Constructor
     *
     * @param x x position
     * @param y y position
     */
    public Player(int x, int y) {
        super(x,y);
    }

    /**
     * Move Function for player. Error checking done to ensure the player does not move out of bounds.
     * Player can only move if new location is a walkable tile, as defined in the Map class.
     *
     * @param dx Change in X position
     * @param dy Change in Y position
     * @param map Map containing this Entity
     */
    public void move(int dx, int dy, Map map) {
        if (getX() + dx < map.getRows() && getX() + dx > -1 && getY() + dy < map.getColumns() && getY() + dy > -1) {
            if (map.getTile(getX() + dx, getY() + dy) == Map.WALKABLE_TILE || map.getTile(getX() + dx, getY() + dy) == Map.ITEM_TILE) {
                if(map.getTile(getX() + dx, getY() + dy) == Map.ITEM_TILE) {
                    useItem = true;
                }
                setX(getX() + dx);
                setY(getY() + dy);
                map.getTiles()[getX()][getY()] = Map.PLAYER_TILE;
                map.getTiles()[getX() - dx][getY() - dy] = Map.WALKABLE_TILE;
            }
        }
    }

    public boolean getUseItem() {
        return useItem;
    }

    public void setUseItem(boolean useItem) {
        this.useItem = useItem;
    }
}
