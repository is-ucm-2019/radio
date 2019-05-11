package radio.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Program extends PersistentObject<String> {

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

    public void addBroadcast(Broadcast b) {
        this.broadcasts.add(b);
    }

    public void removeBroadcast(Broadcast b) {
        this.broadcasts.remove(b);
    }

    @Override
    String getKey() {
        return title;
    }
}
