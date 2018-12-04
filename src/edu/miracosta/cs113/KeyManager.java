package edu.miracosta.cs113;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;
    public int upKey, downKey, leftKey, rightKey;
    public long upHold = 0, downHold = 0, leftHold = 0, rightHold = 0;

    public KeyManager() {
        keys = new boolean[256];
        upKey = KeyEvent.VK_W;
        downKey = KeyEvent.VK_S;
        leftKey = KeyEvent.VK_A;
        rightKey = KeyEvent.VK_D;
    }

    public void tick() {
        up = keys[upKey];
        down = keys[downKey];
        left = keys[leftKey];
        right = keys[rightKey];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

        //System.out.println("keyCode = " + e.getKeyCode());

        if(up) {
            upHold++;
        }
        if(down) {
            downHold++;
        }
        if(left) {
            leftHold++;
        }
        if(right) {
            rightHold++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

        if(e.getKeyCode() == upKey) {
            upHold = 0;
        }
        else if(e.getKeyCode() == downKey) {
            downHold = 0;
        }
        else if(e.getKeyCode() == leftKey) {
            leftHold = 0;
        }
        else if(e.getKeyCode() == rightKey) {
            rightHold = 0;
        }
    }
}

