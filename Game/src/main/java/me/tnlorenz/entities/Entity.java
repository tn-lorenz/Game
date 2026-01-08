package me.tnlorenz.entities;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    // TODO: add Idle
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
