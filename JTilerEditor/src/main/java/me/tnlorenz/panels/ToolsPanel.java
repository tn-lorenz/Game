package me.tnlorenz.panels;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import me.tnlorenz.tools.JTilerTool;

import java.util.ArrayList;
import java.util.List;

public class ToolsPanel extends FlowPane {

    private final List<JTilerTool> toolCollection = new ArrayList<>();

    public ToolsPanel() {
        setHgap(6);
        setVgap(6);
    }

    public void addTool(JTilerTool tool) {
        toolCollection.add(tool);

        Image image = tool.getIcon();
        ImageView view = new ImageView(image);
        view.setFitWidth(64);
        view.setFitHeight(64);
        view.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(view);
        button.setPrefSize(64, 64);
        button.setTooltip(new Tooltip(tool.getTooltip()));

        getChildren().add(button);
    }

    public List<JTilerTool> getToolCollection() {
        return toolCollection;
    }
}
