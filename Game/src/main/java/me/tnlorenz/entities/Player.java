package me.tnlorenz.entities;

import me.tnlorenz.GamePanel;
import me.tnlorenz.InputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    InputHandler inputHandler;

    public Player(GamePanel gamePanel, InputHandler inputHandler) {
        this.gamePanel = gamePanel;
        this.inputHandler = inputHandler;
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(inputHandler.upPressed) {
            direction = Direction.UP;
            y -= speed;
        } else if(inputHandler.downPressed) {
            direction = Direction.DOWN;
            y += speed;
        } else if(inputHandler.leftPressed) {
            direction = Direction.LEFT;
            x -= speed;
        } else if(inputHandler.rightPressed) {
            direction = Direction.RIGHT;
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case UP -> image = up1;
            case DOWN -> image = down1;
            case LEFT -> image = left1;
            case RIGHT -> image = right1;
        }

        g2.drawImage(image, x, y, gamePanel.getScaledTileSize(), gamePanel.getScaledTileSize(), null);
    }
}
