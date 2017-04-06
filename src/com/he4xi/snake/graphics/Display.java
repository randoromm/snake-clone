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

    public void renderSnakeRect(int x, int y, int size) {
        g2d.setColor(new Color(0xA3C31F));
        g2d.fillRect(x, y, size, size);

        g2d.setColor(new Color(0x3E7424));
        g2d.drawRect(x, y, size, size);
    }

    public void renderFood(int x, int y, int size) {
        g2d.setColor(new Color(0xFF3DC5));
        g2d.fillOval(x, y, size, size);

        g2d.setColor(new Color(0x97004C));
        g2d.drawOval(x, y, size, size);
    }

    private void drawBackground() {
        g2d.setColor(new Color(0x323232));
        g2d.fillRect(0, 0, width, height);
    }

    private void clear() {
        g2d.clearRect(0, 0, Game.WIDTH, Game.HEIGHT); // Clear the screen.
    }
}
