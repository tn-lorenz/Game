package me.tnlorenz.tools;

import javafx.scene.image.Image;

public abstract class JTilerTool {
    protected String tooltip;
    protected Image icon;

    public JTilerTool(String tooltip, Image icon) {
        this.tooltip = tooltip;
        this.icon = icon;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public Image getIcon() {
        return this.icon;
    }
}
