package com.he4xi.snake.graphics;

import com.he4xi.snake.Game;

import java.awt.*;

/**
 * Class to deal with rendering.
 * Created by Rando on 14.12.2016.
 */
public class Display {
    private int width, height;
    private Graphics2D g2d;

    public Display(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void render(Graphics2D g2d) {
        this.g2d = g2d;
        clear();
        drawBackground();
    }

    public void renderHead(int x, int y, int size) {
        g2d.setColor(Color.black);
        g2d.fillRect(x, y, size, size);
    }

    private void drawBackground() {
        g2d.setColor(new Color(0xA0FF1F));
        g2d.fillRect(0, 0, width, height);
    }

    private void clear() {
        g2d.clearRect(0, 0, Game.WIDTH, Game.HEIGHT); // Clear the screen.
    }
}
