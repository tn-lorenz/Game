package me.tnlorenz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    static final int DEFAULT_LEFT = 37;
    static final int DEFAULT_UP = 38;
    static final int DEFAULT_RIGHT = 39;
    static final int DEFAULT_DOWN = 40;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // TODO: Set-up base for key binds and settings

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case DEFAULT_UP: upPressed = true; break;
            case DEFAULT_DOWN: downPressed = true; break;
            case DEFAULT_LEFT: leftPressed = true; break;
            case DEFAULT_RIGHT: rightPressed = true; break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case DEFAULT_UP -> upPressed = false;
            case DEFAULT_DOWN -> downPressed = false;
            case DEFAULT_LEFT -> leftPressed = false;
            case DEFAULT_RIGHT -> rightPressed = false;
        }
    }

    public enum Keys {
        UP(DEFAULT_UP, 87),
        DOWN(DEFAULT_DOWN, 83),
        LEFT(DEFAULT_LEFT, 65),
        RIGHT(DEFAULT_RIGHT, 68);

        private final int keyCode;
        private final int alternateKeyCode;

        Keys(int keyCode, int alternateKeyCode) {
            this.keyCode = keyCode;
            this.alternateKeyCode = alternateKeyCode;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public int getAlternateKeyCode() {
            return alternateKeyCode;
        }
    }
}
