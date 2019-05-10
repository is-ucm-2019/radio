package radio.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ThemeSchedule implements Comparable<ThemeSchedule> {
    private BroadcastTime internal;

    public ThemeSchedule(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException {
        LocalTime startTime = LocalTime.of(0,0);
        LocalTime endTime = LocalTime.of(23, 59);
        this.internal = new BroadcastTime(LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime));
    }

    @Override
    public int compareTo(ThemeSchedule o) {
        return internal.compareTo(o.internal);
    }

    public boolean overlaps(ThemeSchedule o) {
        return internal.overlaps(o.internal);
    }

    public boolean shouldAffect(BroadcastTime br) {
        return internal.overlaps(br);
    }

    public LocalDate getStart() {
        return internal.getStart().toLocalDate();
    }

    public LocalDate getEnd() {
        return internal.getEnd().toLocalDate();
    }
}
