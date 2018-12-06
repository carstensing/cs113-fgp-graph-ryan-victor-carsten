package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.MatrixGraph;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    public static final int TILE_SIZE = 20;
    public static final Tile WALKABLE_TILE = new Tile(Color.WHITE, TILE_SIZE, TILE_SIZE);
    public static final Tile BARRIER_TILE = new Tile(Color.BLACK, TILE_SIZE, TILE_SIZE);
    public static final Tile PLAYER_TILE = new Tile(Color.GREEN, TILE_SIZE, TILE_SIZE);
    public static final Tile ENEMY_TILE = new Tile(Color.RED, TILE_SIZE, TILE_SIZE);
    private Player player;
    private Enemy[] enemies;

    private int width;
    private int height;
    private Tile[][] tiles;
    private MatrixGraph graph;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        player = new Player(0,1);
        randomizeMap();
    }
    public Map()
    {
            readFile("C:\\Users\\victor\\Documents\\GitHub\\cs113-fgp-graph-ryan-victor-carsten\\src\\edu\\miracosta\\cs113\\Map\\DefaultMap.txt");

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
        tiles[player.getX()][player.getY()] = PLAYER_TILE;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy[] getEnemies() {
        return enemies;
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

    public  void readFile(String file)
    {
        player = new Player(0,1);;
        Scanner reader = null;
        try
        {
            reader = new Scanner( new FileInputStream(file));

            this.height = Integer.parseInt(reader.nextLine());
            this.width = Integer.parseInt(reader.nextLine());
            tiles = new Tile[width][height];

            while (reader.hasNext())
            {
                for(int i = 0; i < this.width;i++)
                {
                    for (int j = 0; j< this.height; j++)
                    {
                        int value = reader.nextInt();

                        if(value == 0)
                        {
                            tiles[i][j] = WALKABLE_TILE;
                        }
                        else if(value == 1)
                        {
                            tiles[i][j] = BARRIER_TILE;
                        }
                        else if(value == 2)
                        {
                            player.setX(i);
                            player.setY(j);
                            tiles[i][j] = PLAYER_TILE;
                        }
                        else
                        {
                            tiles[i][j] = ENEMY_TILE;
                        }

                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
