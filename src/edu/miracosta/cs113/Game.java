package edu.miracosta.cs113;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Game extends JFrame
{

    private int windowWidth;
    private int windowHeight;
    private String title;
    private Map map;
    private BufferedImage backBuffer;

    //Input
    private KeyManager keyManager;

    public static void main(String[] args)
    {
        Game game = new Game("Run Away", 500 ,300);
        game.run();
        System.exit(0);
    }

    public Game(String title, int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.title = title;
        keyManager = new KeyManager();
        this.map = new Map(10,10);
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

        bbg.setColor(Color.green);
        for (int i = 0; i < map.getWidth(); i ++) {
            for (int j = 0; j < map.getHeight(); j ++) {
                current = map.getTile(i,j);
                bbg.setColor(current.getColor());
                bbg.fillRect(i * (windowWidth / map.getWidth()), j * (windowHeight / map.getHeight()), windowWidth / map.getWidth() - 2, windowHeight / map.getHeight() - 2);
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
