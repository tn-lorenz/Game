package me.tnlorenz.panels;

import me.tnlorenz.tools.JTilerTool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToolsPanel extends JPanel {

    private final List<JTilerTool> toolCollection = new ArrayList<>();

    public ToolsPanel() {
        this.setLayout(new FlowLayout());

        /*for (int i = 0; i < toolCollection.size(); i++) {
            JTilerTool tool = toolCollection.get(i);
            JButton button = new JButton(tool.getIcon());
            button.setToolTipText(tool.getTooltip());
            this.add(button);
        }*/
    }

    public void addTool(JTilerTool tool) {
        toolCollection.add(tool);

        ImageIcon raw = tool.getIcon();
        Image scaled = raw.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);

        JButton button = new JButton(icon);

        button.setToolTipText(tool.getTooltip());
        button.setPreferredSize(new Dimension(64, 64));
        button.setMinimumSize(new Dimension(32, 32));
        button.setMaximumSize(new Dimension(128, 128));

        this.add(button);
        this.revalidate();
        this.repaint();
    }

    public List<JTilerTool> getToolCollection() {
        return toolCollection;
    }
}
