package radio.core;

import java.awt.Color;

public class Program {
    private String title;
    private String description;
    private Color color;

    public Program(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }
}
