package com.he4xi.snake.entity.snake;

import com.he4xi.snake.entity.Entity;
import com.he4xi.snake.graphics.Display;

/**
 * Class for snakes head.
 * Created by Rando on 14.12.2016.
 */
public class Head extends Entity{
    public int size;

    public Head(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void render(Display display) {
        display.renderHead(x, y, size);
    }

    @Override
    public void update() {
        y -= 1;
    }
}
