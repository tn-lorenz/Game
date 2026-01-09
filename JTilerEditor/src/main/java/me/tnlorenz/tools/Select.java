package me.tnlorenz.tools;

import javafx.scene.image.Image;
import me.tnlorenz.Main;

import java.net.URL;

public class Select extends JTilerTool {
    public Select() {
        URL imageUrl = Main.class.getClassLoader().getResource("tools/SelectTool.png");
        assert imageUrl != null;
        Image icon = new Image(imageUrl.toExternalForm());

        super("Select Tool", icon);
    }
}
