package me.tnlorenz.tools;

import me.tnlorenz.Main;

import javax.swing.*;
import java.net.URL;

public class Erase extends JTilerTool {
    public Erase() {
        URL imageUrl = Main.class.getClassLoader().getResource("tools/EraserTool.png");
        assert imageUrl != null;
        ImageIcon icon = new ImageIcon(imageUrl);

        super("Eraser Tool", icon);
    }
}
