package me.tnlorenz.tools;

import me.tnlorenz.Main;

import javax.swing.*;
import java.net.URL;

public class Select extends JTilerTool {
    public Select() {
        URL imageUrl = Main.class.getClassLoader().getResource("tools/SelectTool.png");
        assert imageUrl != null;
        ImageIcon icon = new ImageIcon(imageUrl);

        super("Select Tool", icon);
    }
}
