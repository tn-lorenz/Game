package me.tnlorenz;

import me.tnlorenz.format.JTilerSceneFormat;
import me.tnlorenz.panels.TilesPanel;
import me.tnlorenz.panels.ToolsPanel;
import me.tnlorenz.tools.Erase;
import me.tnlorenz.tools.JTilerTool;
import me.tnlorenz.tools.Pen;
import me.tnlorenz.tools.Select;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Editor extends JFrame implements ActionListener {

    private JMenuBar menu;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem newFileItem, openFileItem, saveFileItem, saveFileAsItem, exitItem;

    private JSplitPane splitPane, splitPlaneLeft;
    private JPanel tilesPanel, toolsPanel, viewPanel;

    private List<JTilerSceneFormat.Entry> sceneEntries = new ArrayList<>();
    private List<File> tileSetCollectionEntries = new ArrayList<>();

    public Editor() {
        super("JTiler Editor | 2026 (c) Tim-Niklas Lorenz");
        this.setResizable(true);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL imageUrl = Main.class.getClassLoader().getResource("JLogo.png");
        if (imageUrl != null) {
            ImageIcon image = new ImageIcon(imageUrl);
            this.setIconImage(image.getImage());
        }

        this.setJMenuBar(getBar());
        this.add(getJSplitPane());
        this.setVisible(true);
    }

    private JMenuBar getBar() {
        menu = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        newFileItem = new JMenuItem("New");
        openFileItem = new JMenuItem("Open");
        saveFileItem = new JMenuItem("Save");
        saveFileAsItem = new JMenuItem("Save As...");
        exitItem = new JMenuItem("Exit");

        newFileItem.setActionCommand("NEW_FILE");
        openFileItem.setActionCommand("OPEN_FILE");
        saveFileItem.setActionCommand("SAVE_FILE");
        saveFileAsItem.setActionCommand("SAVE_FILE_AS");
        exitItem.setActionCommand("EXIT");

        newFileItem.addActionListener(this);
        openFileItem.addActionListener(this);
        saveFileItem.addActionListener(this);
        saveFileAsItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(saveFileAsItem);
        fileMenu.add(exitItem);

        menu.add(fileMenu);
        menu.add(editMenu);
        menu.add(helpMenu);

        return menu;
    }

    private JSplitPane getJSplitPane() {
        splitPlaneLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        tilesPanel = new TilesPanel();
        toolsPanel = new ToolsPanel();
        viewPanel  = new JPanel();

        setupTools((ToolsPanel) toolsPanel);

        tilesPanel.setBackground(Color.LIGHT_GRAY);
        toolsPanel.setBackground(Color.LIGHT_GRAY);
        viewPanel.setBackground(Color.LIGHT_GRAY);

        splitPlaneLeft.setTopComponent(tilesPanel);
        splitPlaneLeft.setBottomComponent(toolsPanel);
        splitPlaneLeft.setDividerSize(4);
        splitPlaneLeft.setDividerLocation(250);

        splitPane.setLeftComponent(splitPlaneLeft);
        splitPane.setRightComponent(viewPanel);
        splitPane.setDividerSize(6);
        splitPane.setDividerLocation(250);

        return splitPane;
    }

    private void setupTools(ToolsPanel panel) {
        panel.addTool(new Pen());
        panel.addTool(new Select());
        panel.addTool(new Erase());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "NEW_FILE" -> {
                sceneEntries.clear();
                chooseFile(true);
            }
            case "OPEN_FILE" -> chooseFile(false);
            case "SAVE_FILE" -> chooseFile(true); // TODO
            case "SAVE_FILE_AS" -> chooseFile(true);
            case "EXIT" -> this.dispose();
            default -> {}
        }
    }

    private void chooseFile(boolean saveDialog) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("JTiler Files (.jts)", "jts");
        fileChooser.setFileFilter(fileFilter);

        int option = saveDialog ? fileChooser.showSaveDialog(this) : fileChooser.showOpenDialog(this);

        if(option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if(saveDialog && !file.getName().toLowerCase().endsWith(".jts")) {
                file = new File(file.getParentFile(), file.getName() + ".jts");
            }

            Path path = file.toPath();

            try {
                if(saveDialog) {
                    JTilerSceneFormat.write(path, sceneEntries);
                    System.out.println("Saved file: " + file.getAbsolutePath());
                } else {
                    sceneEntries = JTilerSceneFormat.read(path);
                    System.out.println("Loaded file: " + file.getAbsolutePath() + " (" + sceneEntries.size() + " entries)");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error handling file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
