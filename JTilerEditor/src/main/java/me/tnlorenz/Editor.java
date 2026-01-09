package me.tnlorenz;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.SplitPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import me.tnlorenz.format.JTilerSceneFormat;
import me.tnlorenz.panels.TilesPanel;
import me.tnlorenz.panels.ToolsPanel;
import me.tnlorenz.tools.Erase;
import me.tnlorenz.tools.Pen;
import me.tnlorenz.tools.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Editor {

    private final List<JTilerSceneFormat.Entry> sceneEntries = new ArrayList<>();

    public Editor() {
        Platform.startup(this::init);
    }

    private void init() {
        Stage stage = new Stage();

        stage.setTitle("JTiler Editor | 2026 (c) Tim-Niklas Lorenz");
        stage.setWidth(900);
        stage.setHeight(600);

        URL iconUrl = getClass().getClassLoader().getResource("JLogo.png");
        if (iconUrl != null) {
            stage.getIcons().add(new Image(iconUrl.toExternalForm()));
        }

        BorderPane root = new BorderPane();
        root.setTop(createMenuBar(stage));
        root.setCenter(createSplitPane());

        stage.setScene(new Scene(root));
        stage.show();
    }

    private MenuBar createMenuBar(Stage stage) {
        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");
        MenuItem saveFileAs = new MenuItem("Save As...");
        MenuItem exit = new MenuItem("Exit");

        newFile.setOnAction(_ -> {
            sceneEntries.clear();
            chooseFile(stage, true);
        });

        openFile.setOnAction(_ -> chooseFile(stage, false));
        saveFile.setOnAction(_ -> chooseFile(stage, true));
        saveFileAs.setOnAction(_ -> chooseFile(stage, true));
        exit.setOnAction(_ -> Platform.exit());

        Menu fileMenu = new Menu("File", null, newFile, openFile, saveFile, saveFileAs, exit);
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");

        return new MenuBar(fileMenu, editMenu, helpMenu);
    }

    private SplitPane createSplitPane() {
        TilesPanel tilesPanel = new TilesPanel();
        ToolsPanel toolsPanel = new ToolsPanel();
        Pane viewPanel = new Pane();

        toolsPanel.addTool(new Pen());
        toolsPanel.addTool(new Select());
        toolsPanel.addTool(new Erase());

        SplitPane leftSplit = new SplitPane(tilesPanel, toolsPanel);
        leftSplit.setOrientation(javafx.geometry.Orientation.VERTICAL);
        leftSplit.setDividerPositions(0.6);

        SplitPane mainSplit = new SplitPane(leftSplit, viewPanel);
        mainSplit.setDividerPositions(0.28);

        return mainSplit;
    }

    private void chooseFile(Stage stage, boolean saveDialog) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JTiler Files", "*.jts")
        );

        File file = saveDialog ? chooser.showSaveDialog(stage) : chooser.showOpenDialog(stage);

        if (file == null) return;

        if (saveDialog && !file.getName().toLowerCase().endsWith(".jts")) {
            file = new File(file.getParentFile(), file.getName() + ".jts");
        }

        Path path = file.toPath();

        try {
            if (saveDialog) {
                JTilerSceneFormat.write(path, sceneEntries);
                System.out.println("Saved file: " + file.getAbsolutePath());
            } else {
                sceneEntries.clear();
                sceneEntries.addAll(JTilerSceneFormat.read(path));
                System.out.println("Loaded file: " + file.getAbsolutePath() + " (" + sceneEntries.size() + " entries)");
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.setHeaderText("Error handling file");
            alert.showAndWait();
        }
    }
}
