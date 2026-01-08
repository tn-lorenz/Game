package me.tnlorenz;

import javax.swing.*;
import java.awt.*;

public class Main {
    static void main() {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Cool Game!");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setMinimumSize(new Dimension(gamePanel.PREFERRED_WIDTH, gamePanel.PREFERRED_HEIGHT));

        window.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int newWidth = window.getContentPane().getWidth();
                int newHeight = window.getContentPane().getHeight();

                double aspectRatio = (double) gamePanel.PREFERRED_WIDTH / gamePanel.PREFERRED_HEIGHT;

                if (newWidth < newHeight * aspectRatio) {
                    newHeight = (int) (newWidth / aspectRatio);
                } else {
                    newWidth = (int) (newHeight * aspectRatio);
                }

                window.getContentPane().setPreferredSize(new java.awt.Dimension(newWidth, newHeight));
                window.pack();

                gamePanel.updateScaleMultiplier();
                gamePanel.repaint();
            }
        });

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
