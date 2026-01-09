package me.tnlorenz.tools;

import javafx.scene.image.Image;
import me.tnlorenz.Main;

import java.net.URL;

public class Pen extends JTilerTool {
    public Pen() {
        URL imageUrl = Main.class.getClassLoader().getResource("tools/PenTool.png");
        assert imageUrl != null;
        Image icon = new Image(imageUrl.toExternalForm());

        super("Pen Tool", icon);
    }
}
