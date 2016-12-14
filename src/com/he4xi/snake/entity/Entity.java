package com.he4xi.snake.entity;

import com.he4xi.snake.graphics.Display;

/**
 * Template class for all entities.
 * Created by Rando on 14.12.2016.
 */
public abstract class Entity {
    public int x, y; // X and Y location of entity.
    private boolean removed;


    public void update() {

    }

    public void render(Display display) {

    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
