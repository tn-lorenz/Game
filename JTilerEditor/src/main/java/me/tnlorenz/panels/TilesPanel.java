package me.tnlorenz.panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TilesPanel extends JPanel implements ActionListener {

    private final JLabel infoLabel;
    private final JButton selectFileButton;

    public TilesPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        infoLabel = new JLabel("No textures loaded.");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        selectFileButton = new JButton("Select file");
        selectFileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectFileButton.addActionListener(this);

        this.add(Box.createVerticalGlue());
        this.add(infoLabel);
        this.add(Box.createRigidArea(new Dimension(0, 8)));
        this.add(selectFileButton);
        this.add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("PNG Image", "png", "PNG");
            fileChooser.setFileFilter(fileFilter);
            fileChooser.showOpenDialog(this);
        }
    }
}
