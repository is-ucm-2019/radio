package radio.core;

import radio.util.ThemeSchedule;

import java.awt.*;
import java.util.Optional;

public class Theme implements Comparable<Theme> {
    private String name;
    private String description;
    // FIXME(borja): Optional is not serializable
    private Optional<Color> color;
    private ThemeSchedule schedule;

    public Theme(String title, String description, ThemeSchedule sch) {
        this.name = title;
        this.description = description;
        this.schedule = sch;
        this.color = Optional.empty();
    }

    public Theme(String title, String description, ThemeSchedule sch, Color c) {
        this.name = title;
        this.description = description;
        this.schedule = sch;
        this.color = Optional.of(c);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Optional<Color> getColor() {
        return color;
    }

    public ThemeSchedule getSchedule() {
        return schedule;
    }

    @Override
    public int compareTo(Theme o) {
        int sameSched = this.schedule.compareTo(o.schedule);
        if (sameSched != 0) {
            return sameSched;
        }

        int sameName = this.name.compareTo(o.name);
        if (sameName != 0) {
            return  sameName;
        }

        return this.description.compareTo(o.description);

    }
}
