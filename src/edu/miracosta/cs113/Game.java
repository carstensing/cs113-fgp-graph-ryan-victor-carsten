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

    }

    public void update()
    {

    }

    public void draw()
    {
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();
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
