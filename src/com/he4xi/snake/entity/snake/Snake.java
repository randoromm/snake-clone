package com.he4xi.snake.entity.snake;

import com.he4xi.snake.Game;
import com.he4xi.snake.entity.Entity;
import com.he4xi.snake.graphics.Display;
import com.he4xi.snake.input.KeyInput;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class for snakes head.
 * Created by Rando on 14.12.2016.
 */
public class Snake extends Entity {

    public KeyInput input;
    public int xDir, yDir;
    public final int SIZE = 15;
    private int count = 0;
    private int speed = 15; // More is slower. 1 Movement in how many updates?
    private boolean growing;

    public ArrayList<Rectangle> snakeRects = new ArrayList<>();

    public Snake(int xInit, int yInit, KeyInput input) {
        this.xInit = xInit;
        this.yInit = yInit;
        this.input = input;

        xDir = 0;
        yDir = 0;

        snakeRects.add(new Rectangle(this.xInit, this.yInit, SIZE, SIZE));
    }

    public boolean isColliding() {
        int x = snakeRects.get(0).x;
        int y = snakeRects.get(0).y;
        if (x < 0 || x + SIZE > Game.WIDTH || y < 0 || y + SIZE > Game.HEIGHT) {
            // Multiplying by 3 because 2 tiles go out of frame.
            return true;
        }

        for (int i = 2; i < snakeRects.size(); i++) {
            if (snakeRects.get(0).contains(snakeRects.get(i))) return true;
        }

        return false;
    }

    private void getDirectionsFromInput() {
        if (input.up && yDir != 1) {
            yDir = -1;
            xDir = 0;
        }
        if (input.down && yDir != -1) {
            yDir = 1;
            xDir = 0;
        }
        if (input.left && xDir != 1) {
            yDir = 0;
            xDir = -1;
        }
        if (input.right && xDir != -1) {
            yDir = 0;
            xDir = 1;
        }
    }

    public void move() {
        count++;
        if (isColliding()) {
            xDir = 0;
            yDir = 0;
        } else {
            getDirectionsFromInput();

            if (count % speed == 0) {
                Rectangle temp = snakeRects.get(0);
                Rectangle last = snakeRects.get(snakeRects.size() - 1);
                Rectangle newRect = new Rectangle(temp.x + xDir * SIZE, temp.y + yDir * SIZE, SIZE, SIZE);
                for (int i = snakeRects.size() - 1; i >= 1; i--) {
                    snakeRects.set(i, snakeRects.get(i - 1));
                }
                snakeRects.set(0, newRect);

                if (growing) {
                    snakeRects.add(last);
                    growing = false;
                }
            }
        }

        // Every 20 seconds.
        if (count > 900){
            if (speed >= 4) {
                speed--;
            }
            count = 0;
        }
    }

    @Override
    public void update() {

        move();
    }

    @Override
    public void render(Display display) {
        for (Rectangle rect : snakeRects) {
            display.renderSnakeRect(rect.x, rect.y, SIZE);
        }
    }

    public void addRect() {
        growing = true;
    }

    public void reset() {
        snakeRects.clear();
        xDir = 0;
        yDir = 0;
        speed = 15;

        snakeRects.add(new Rectangle(this.xInit, this.yInit, SIZE, SIZE));
    }
}
