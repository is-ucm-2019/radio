package radio.core;

import java.awt.*;

public class Program {
    private String title;

    public String getDescription() {
        return description;
    }

    private String description;

    public Color getColor() {
        return color;
    }

    private Color color;

    public Program(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }
}
