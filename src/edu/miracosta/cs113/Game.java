package edu.miracosta.cs113;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Game extends JFrame
{
    private int fps = 300;
    private boolean isRunning;
    private int windowWidth = 500;
    private int windowHeight = 500;
    private BufferedImage backBuffer;

    public static void main(String[] args)
    {
        Game brickBreaker = new Game();
        brickBreaker.run();
        System.exit(0);
    }

    public void run()
    {
        this.initialize();

        while (isRunning)
        {
            long time = System.currentTimeMillis();

            update();
            draw();

            time = (1000 / fps) - (System.currentTimeMillis() - time);

            if (time > 0)
            {
                try {
                    Thread.sleep(time);
                }
                catch (InterruptedException e)
                {
                }
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
