package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.MatrixGraph;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public static final int TILE_SIZE = 35;
    public static final Tile WALKABLE_TILE = new Tile(Color.WHITE, TILE_SIZE, TILE_SIZE);
    public static final Tile BARRIER_TILE = new Tile(Color.BLACK, TILE_SIZE, TILE_SIZE);
    public static final Tile PLAYER_TILE = new Tile(Color.GREEN, TILE_SIZE, TILE_SIZE);
    public static final Tile ENEMY_TILE = new Tile(Color.RED, TILE_SIZE, TILE_SIZE);
    public static final Tile ITEM_TILE = new Tile(Color.BLUE, TILE_SIZE, TILE_SIZE);
    private Player player;
    private ArrayList<Enemy> enemies;
    private int rows;
    private int columns;
    private Tile[][] tiles;
    private MatrixGraph graph;

    public Map(String file) {
        enemies = new ArrayList<>();
        readFile(file);
        graph = new MatrixGraph(this);
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
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

    public void readFile(String file)
    {
        player = new Player(0,1);
        Scanner reader = null;
        try
        {
            reader = new Scanner( new FileInputStream(file));

            this.columns = Integer.parseInt(reader.nextLine());
            this.rows = Integer.parseInt(reader.nextLine());
            tiles = new Tile[rows][columns];

            while (reader.hasNext())
            {
                for(int i = 0; i < this.rows;i++)
                {
                    for (int j = 0; j< this.columns; j++)
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
                            enemies.add(new Enemy(i,j));
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

    /**
     * Converts a 2D index to a Row-Ordered 1D index
     *
     * @param row row in tiles
     * @param column column in tiles
     *
     * @return Row-Ordered 1D index
     */
    public int get1DIndex(int row, int column) {
        return column + row * columns;
    }

    /**
     * Converts a Row-Ordered 1D index into a 2D index
     *
     * @param index Row-Ordered 1D index
     *
     * @return int[] containing {row,column}
     */
    public int[] get2DIndex(int index) {
        int column = index % columns;
        int row = index / columns;
        return new int[]{row,column};
    }
}
