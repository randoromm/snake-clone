package com.he4xi.snake;

import com.he4xi.snake.entity.snake.Head;
import com.he4xi.snake.graphics.Display;
import com.he4xi.snake.input.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Main class for the game.
 * Created by Rando on 14.12.2016.
 */
public class Game extends JPanel implements Runnable {

    public static final int WIDTH = 600, HEIGHT = 600;

    private JFrame frame;
    private Thread thread;
    private Display display;
    private KeyInput keys;
    private Head snakeHead;

    private Graphics2D g2d;
    private BufferedImage image;

    private boolean running;

    public Game() {
        frame = new JFrame("Snake");
        keys = new KeyInput();
        display = new Display(WIDTH, HEIGHT);
        snakeHead = new Head(WIDTH / 2, HEIGHT / 2, 15);

        frame.addKeyListener(keys);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double tsq = 1000000000.0 / 60.0; // 1bil ns is 1s, divide it by UPS.
        double deltaTime = 0;

        int frames = 0;
        int updates = 0;

        while (running) {
            long startTime = System.nanoTime();
            deltaTime += (startTime - lastTime) / tsq;
            lastTime = startTime;
            while (deltaTime >= 1) {
                update();
                updates++;
                deltaTime--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " ,UPS: " + updates);
                frame.setTitle("Snake | " + frames + " FPS, " + updates + " UPS");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        display.render(g2d);
        snakeHead.render(display);

        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    private void update() {
        keys.update();
        snakeHead.update();
    }

    private synchronized void start() {
        running = true;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();

        thread = new Thread(this, "Display");
        thread.start();
    }

    private synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.frame.add(game);
        game.frame.pack();
        game.frame.setSize(WIDTH, HEIGHT);
        game.frame.setResizable(false);
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
