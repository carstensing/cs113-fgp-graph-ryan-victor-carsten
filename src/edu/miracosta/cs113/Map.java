package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.MatrixGraph;

import java.awt.*;

public class Map {
    public static final Tile WALKABLE_TILE = new Tile(Color.WHITE);
    public static final Tile BARRIER_TILE = new Tile(Color.BLACK);
    public static final Tile PLAYER_TILE = new Tile(Color.GREEN);
    public static final Tile ENEMY_TILE = new Tile(Color.RED);
    private int width;
    private int height;
    private Tile[][] tiles;
    private MatrixGraph graph;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        randomizeMap();
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void randomizeMap() {
        for (int i = 0; i < width; i ++) {
            for (int j = 0; j < height; j ++) {
                if (Math.random() * width < width / 5) {
                    tiles[i][j] = BARRIER_TILE;
                }else {
                    tiles[i][j] = WALKABLE_TILE;
                }
            }
        }
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

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public MatrixGraph getGraph() {
        return graph;
    }

    public void setGraph(MatrixGraph graph) {
        this.graph = graph;
    }
}
