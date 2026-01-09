package me.tnlorenz.panels;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class TilesPanel extends VBox {

    private final Label infoLabel;
    private final Button selectFileButton;

    public TilesPanel() {
        setAlignment(Pos.CENTER);
        setSpacing(8);

        infoLabel = new Label("No textures loaded.");
        selectFileButton = new Button("Select file");

        selectFileButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PNG Image", "*.png")
            );
            chooser.showOpenDialog(getScene().getWindow());
        });

        getChildren().addAll(infoLabel, selectFileButton);
    }
}
