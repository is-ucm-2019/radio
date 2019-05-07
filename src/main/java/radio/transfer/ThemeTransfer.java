package radio.transfer;

import radio.core.Theme;
import radio.util.ThemeSchedule;

import java.awt.Color;
import java.util.Optional;

public class ThemeTransfer {
    public final String name;
    public final String description;
    public final Optional<Color> color;
    public final ThemeSchedule schedule;

    public ThemeTransfer(String name, String description, ThemeSchedule sch) {
        this.name = name;
        this.description = description;
        this.schedule = sch;
        this.color = Optional.empty();
    }

    public ThemeTransfer(String name, String description, ThemeSchedule sch, Color c) {
        this.name = name;
        this.description = description;
        this.schedule = sch;
        this.color = Optional.of(c);
    }

    public ThemeTransfer(Theme t) {
        this.name = t.getName();
        this.description = t.getDescription();
        this.schedule = t.getSchedule();
        this.color = t.getColor();
    }
}
