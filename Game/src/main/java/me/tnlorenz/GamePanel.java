package me.tnlorenz;

import me.tnlorenz.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    static final int FPS = 60;

    // The original tile size in pixels.
    static final int TILE_SIZE = 16;

    // How many tiles can be represented horizontally.
    static final int MAX_SCREEN_COLUMNS = 16;

    // How many tiles can be represented vertically.
    static final int MAX_SCREEN_ROWS = 12;

    // The tile scale multiplier necessary to make the tiles clearly visible on the default window size.
    public int baseScale = 3;

    // The scale multiplier from window resizing.
    public int windowScaleMultiplier = 1;

    final int PREFERRED_WIDTH = TILE_SIZE * baseScale * MAX_SCREEN_COLUMNS;
    final int PREFERRED_HEIGHT = TILE_SIZE * baseScale * MAX_SCREEN_ROWS;

    InputHandler inputHandler = new InputHandler();
    Thread gameThread;
    Player player = new Player(this, inputHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getScaledTileSize() {
        return TILE_SIZE * baseScale * windowScaleMultiplier;
    }

    public int getScreenWidth() {
        return getScaledTileSize() * MAX_SCREEN_COLUMNS;
    }

    public int getScreenHeight() {
        return getScaledTileSize() * MAX_SCREEN_ROWS;
    }

    public void updateScaleMultiplier() {
        // int widthMultiplier = this.getWidth() / (TILE_SIZE * MAX_SCREEN_COLUMNS);
        // int heightMultiplier = this.getHeight() / (TILE_SIZE * MAX_SCREEN_ROWS);
        int widthMultiplier = this.getWidth() / getScreenWidth();
        int heightMultiplier = this.getWidth() / getScreenHeight();

        // windowScaleMultiplier = Math.max(1, Math.min(widthMultiplier, heightMultiplier));
        windowScaleMultiplier = Math.max(1, Math.min(widthMultiplier, heightMultiplier));
    }

    // The Game-Loop
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // updateScaleMultiplier();
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateScaleMultiplier();

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        // g2.dispose();
    }
}
