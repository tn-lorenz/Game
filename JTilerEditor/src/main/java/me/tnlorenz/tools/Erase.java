package me.tnlorenz.tools;

import javafx.scene.image.Image;
import me.tnlorenz.Main;

import java.net.URL;

public class Erase extends JTilerTool {
    public Erase() {
        URL imageUrl = Main.class.getClassLoader().getResource("tools/EraserTool.png");
        assert imageUrl != null;
        Image icon = new Image(imageUrl.toExternalForm());

        super("Eraser Tool", icon);
    }
}
