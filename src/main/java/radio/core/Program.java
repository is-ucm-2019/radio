package radio.core;

import java.awt.*;
import java.util.UUID;

public class Program {
    private UUID id;
    public String title;
    private String description;
    private Color color;

    public Program(String title, String description, Color color) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.color = color;
    }

    UUID getId() {
        return this.id;
    }
}
