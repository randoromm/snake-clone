package com.he4xi.snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for keyboard inputs.
 * Created by Rando on 14.12.2016.
 */
public class KeyInput implements KeyListener{
    private boolean[] keys = new boolean[145]; // Create list for most keys (144 is NumLock, should be enough).
    public boolean up, down, left, right;

    public void update() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
