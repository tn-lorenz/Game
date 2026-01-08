package me.tnlorenz.tools;

import me.tnlorenz.Main;

import javax.swing.*;
import java.net.URL;

public class Pen extends JTilerTool {
    public Pen() {
        URL imageUrl = Main.class.getClassLoader().getResource("tools/PenTool.png");
        assert imageUrl != null;
        ImageIcon icon = new ImageIcon(imageUrl);

        super("Pen Tool", icon);
    }
}
