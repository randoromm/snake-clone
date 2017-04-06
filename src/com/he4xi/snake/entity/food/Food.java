package com.he4xi.snake.entity.food;

import com.he4xi.snake.Game;
import com.he4xi.snake.entity.Entity;
import com.he4xi.snake.entity.snake.Snake;
import com.he4xi.snake.graphics.Display;

import java.awt.*;
import java.util.Random;

/**
 * Class for food objects.
 * Created by Rando on 14.12.2016.
 */
public class Food extends Entity {

    public final int SIZE = 15;
    Random rndm = new Random();
    Rectangle food;
    Snake snake;

    public Food(Snake snake) {
        this.snake = snake;
        xInit = rndm.nextInt(39) * 15;
        yInit = rndm.nextInt(38) * 15; // Two "tiles" go out of frame.
        food = new Rectangle(xInit, yInit, SIZE, SIZE);
    }

    @Override
    public void render(Display display) {
        display.renderFood(food.x, food.y, SIZE);
    }

    @Override
    public void update() {
        for (Rectangle r : snake.snakeRects) {
            if (food.intersects(r)) {
                food.x = rndm.nextInt(39) * 15; // 1 tile out of frame.
                food.y = rndm.nextInt(38) * 15; // Two "tiles" go out of frame.
                snake.addRect();
                Game.score++;
            }
        }
    }

    public void reset() {
        food.x = rndm.nextInt(39) * 15; // 1 tile out of frame.
        food.y = rndm.nextInt(38) * 15; // Two "tiles" go out of frame.
    }
}
