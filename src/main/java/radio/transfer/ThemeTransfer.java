package radio.transfer;

import radio.core.Theme;
import radio.util.ThemeSchedule;

import java.awt.Color;
import java.time.LocalDate;

public class ThemeTransfer {
    public final String name;
    public final String description;
    public final ThemeSchedule schedule;

    public ThemeTransfer(String name, String description, ThemeSchedule sch) {
        this.name = name;
        this.description = description;
        this.schedule = sch;
    }

    public ThemeTransfer(String name, String description, LocalDate start, LocalDate end) {
        this.name = name;
        this.description = description;
        this.schedule = new ThemeSchedule(start, end);
    }

    public ThemeTransfer(String name, String description, ThemeSchedule sch, Color c) {
        this.name = name;
        this.description = description;
        this.schedule = sch;
    }

    public ThemeTransfer(Theme t) {
        this.name = t.getName();
        this.description = t.getDescription();
        this.schedule = t.getSchedule();
    }
}
