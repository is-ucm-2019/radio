package radio.core;

import radio.transfer.ThemeTransfer;
import radio.util.ThemeSchedule;

public class Theme extends PersistentObject<String> implements Comparable<Theme> {
    private String name;
    private String description;
    private ThemeSchedule schedule;

    public Theme(String title, String description, ThemeSchedule sch) {
        this.name = title;
        this.description = description;
        this.schedule = sch;
    }

    public Theme(ThemeTransfer tr) {
        this.name = tr.name;
        this.description = tr.description;
        this.schedule = tr.schedule;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
            return sameName;
        }

        return this.description.compareTo(o.description);

    }

    @Override
    String getKey() {
        return name;
    }
}
