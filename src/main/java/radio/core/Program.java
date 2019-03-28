package radio.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private String title;
    private String description;
    private Color color;
    private List<Broadcast> broadcasts;

    public Program(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.broadcasts = new ArrayList<>();
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

    public List<Broadcast> getBroadcasts() {
        return broadcasts;
    }
}
