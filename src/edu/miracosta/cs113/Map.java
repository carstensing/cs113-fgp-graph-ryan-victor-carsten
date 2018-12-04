package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.MatrixGraph;

public class Map {
    private int width;
    private int height;
    private Tile[][] tiles;
    private MatrixGraph graph;

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
