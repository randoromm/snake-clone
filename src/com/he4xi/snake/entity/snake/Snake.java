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
    private int speed = 20; // More is slower. 1 Movement in how many updates?

    public ArrayList<Rectangle> snake = new ArrayList<>();

    public Snake(int x, int y, KeyInput input) {
        this.x = x;
        this.y = y;
        this.input = input;

        xDir = 1;
        yDir = 0;

        snake.add(new Rectangle(x, y, SIZE, SIZE));

        for (int i = 1; i <= 2; i++) {
            snake.add(new Rectangle(x - i * SIZE, y, SIZE, SIZE));
        }

    }

    public boolean isColliding() {
        int x = snake.get(0).x;
        int y = snake.get(0).y;
        if (x < 0 || x + SIZE > Game.WIDTH || y < 0 || y + SIZE > Game.HEIGHT) {
            return true;
        }

        for (int i = 2; i < snake.size(); i++) {
            if (snake.get(0).contains(snake.get(i))) return true;
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
                Rectangle temp = snake.get(0);
                Rectangle newRect = new Rectangle(temp.x + xDir * SIZE, temp.y + yDir * SIZE, SIZE, SIZE);
                for (int i = snake.size() - 1; i >= 1; i--) {
                    snake.set(i, snake.get(i - 1));
                }
                snake.set(0, newRect);
            }
        }
        if (count > 10000) count = 0;
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void render(Display display) {
        for (Rectangle rect : snake) {
            display.renderRectangle(rect.x, rect.y, SIZE);
        }
    }
}
