package me.tnlorenz.tools;

import javax.swing.*;

public abstract class JTilerTool {
    protected String tooltip;
    protected ImageIcon icon;

    public JTilerTool(String tooltip, ImageIcon icon) {
        this.tooltip = tooltip;
        this.icon = icon;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }
}
