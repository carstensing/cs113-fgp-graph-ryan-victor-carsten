package edu.miracosta.cs113;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JFrame
{
    private int windowWidth;
    private int windowHeight;
    private Map map;
    private BufferedImage backBuffer;

    //Level variables
    public static final int LEVEL_DURATION = 5; // seconds
    private int level = 1;
    private boolean addEnemy = false;
    private boolean gameOver = false;

    public static void main(String[] args)
    {
        Game game = new Game("Run Away");
        game.run();
        System.exit(0);
    }

    public Game(String title) {
        super();
        setTitle(title);
        this.map = new Map("DefaultMap.txt");
        this.windowWidth = map.getColumns() * Map.TILE_SIZE + 15; // added constant for centering
        this.windowHeight = map.getRows() * Map.TILE_SIZE + 38; // added constant for centering
        backBuffer = new BufferedImage(windowWidth, windowHeight,BufferedImage.TYPE_INT_RGB);
        frameInit();
    }

    public void run()
    {
        this.initialize();
        boolean isRunning = true;
        int fps = 60;
        double timePerFrame = 1000000000 / fps;
        long startOfFrame;
        long endOfFrame = System.nanoTime();

        long frameTimer = 0;
        int frameCounter = 0;

        long startOfSecond = 0;

        int levelTimer = 0;

        while(isRunning) {
            startOfFrame = System.nanoTime();

            if (frameCounter == 0) {
                startOfSecond = System.nanoTime();
            }

            if (frameTimer == 0) {
                this.update();
                this.draw();
            }

            frameTimer += (startOfFrame - endOfFrame);
            endOfFrame = startOfFrame;

            if (frameTimer >= timePerFrame) {
                frameTimer = 0;
                frameCounter++;
            }

            if (System.nanoTime() - startOfSecond >= 1000000000) {
                //System.out.println(frameCounter);
                frameCounter = 0;

                levelTimer++;
                if(levelTimer >= LEVEL_DURATION && !gameOver) {
                    addEnemy = true;
                    levelTimer = 0;
                }
            }
        }
    }

    public void initialize()
    {
        setSize(windowWidth,windowHeight);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        this.addKeyListener(new KeyListener() {
            Player player = map.getPlayer();
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    player.move(1, 0, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                    player.move(-1, 0, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    player.move(0, -1, map);

                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    player.move(0, 1, map);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);
    }

    public void update()
    {
        if(addEnemy) {
            map.getEnemies().add(new Enemy(map.getRows()/2,map.getColumns()/2));
            level++;
            addEnemy = false;
        }

        for (Enemy enemy: map.getEnemies()) {
            enemy.update(map);
            if(enemy.getX() == map.getPlayer().getX() && enemy.getY() == map.getPlayer().getY() ) {
                this.gameOver = true;
            }
        }
    }

    public void draw()
    {
        Graphics bbg = backBuffer.getGraphics();
        Tile current;

        if(!this.gameOver) {
            setTitle("Level " + level);
            for (int i = 0; i < map.getRows(); i ++) {
                for (int j = 0; j < map.getColumns(); j ++) {
                    current = map.getTile(i,j);
                    bbg.setColor(current.getColor());
                    bbg.fillRect(j * Map.TILE_SIZE, i * Map.TILE_SIZE, Map.TILE_SIZE - 1, Map.TILE_SIZE - 1);
                }
            }
        }
        else {
            setTitle("You reached level " + level + "!");

            bbg.setFont(new Font("TimesRoman", Font.PLAIN, 161));
            bbg.setColor(Color.BLACK);
            bbg.fillRect(0,(this.windowHeight/2) - 120, this.windowWidth ,this.windowHeight/4 + 30);

            bbg.setFont(new Font("TimesRoman", Font.PLAIN, 161));
            bbg.setColor(Color.ORANGE);
            bbg.drawString("GAME OVER", 20, (this.windowHeight/2) +22);

            bbg.setFont(new Font("TimesRoman", Font.PLAIN, 160));
            bbg.setColor(Color.RED);
            bbg.drawString("GAME OVER", 20, (this.windowHeight/2) +20);

        }

        getContentPane().getGraphics().drawImage(backBuffer,0,0,this);
    }

    public int getWindowWidth()
    {
        return windowWidth;
    }

    public int getWindowHeight()
    {
        return windowHeight;
    }
}
