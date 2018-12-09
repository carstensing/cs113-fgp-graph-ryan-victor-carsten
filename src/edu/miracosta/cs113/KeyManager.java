package edu.miracosta.cs113;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public int upKey, downKey, leftKey, rightKey;

    public KeyManager() {
        keys = new boolean[256];
        upKey = KeyEvent.VK_W;
        downKey = KeyEvent.VK_S;
        leftKey = KeyEvent.VK_A;
        rightKey = KeyEvent.VK_D;
    }

    public boolean isKeyDown(int keyCode) {
        if (keyCode > -1 && keyCode < keys.length) {
            return keys[keyCode];
        } else {
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() > -1 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() > -1 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }

    }
}

