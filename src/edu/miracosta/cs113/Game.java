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
    private String title;
    private Map map;
    private BufferedImage backBuffer;

    public static void main(String[] args)
    {
        Game game = new Game("Run Away");
        game.run();
        System.exit(0);
    }

    public Game(String title, int windowWidth, int windowHeight) {
        super();
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.title = title;
        backBuffer = new BufferedImage(windowWidth, windowHeight,BufferedImage.TYPE_INT_RGB);
    }

    public Game(String title) {
        super();
        this.title = title;
        this.map = new Map();
        this.windowWidth = map.getWidth() * Map.TILE_SIZE;
        this.windowHeight = map.getHeight() * Map.TILE_SIZE;
        backBuffer = new BufferedImage(windowWidth, windowHeight,BufferedImage.TYPE_INT_RGB);
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
                System.out.println(frameCounter);
                frameCounter = 0;
            }
        }
    }

    public void initialize()
    {
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        this.addKeyListener(new KeyListener() {
            Player player = map.getPlayer();
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    player.move(0, 1, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                    player.move(0, -1, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    player.move(-1, 0, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    player.move(1, 0, map);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void update()
    {

    }

    public void draw()
    {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();
        Tile current;
        bbg.setColor(Color.BLACK);
        bbg.drawRect(0,0,windowWidth,windowHeight);

        for (int i = 0; i < map.getWidth(); i ++) {
            for (int j = 0; j < map.getHeight(); j ++) {
                current = map.getTile(i,j);
                bbg.setColor(current.getColor());
                bbg.fillRect(i * current.getWidth(), j * current.getHeight(), current.getWidth() - 1, current.getHeight() - 1);
            }
        }
        g.drawImage(backBuffer,0,0,this);
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
