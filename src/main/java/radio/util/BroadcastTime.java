package radio.util;

import java.time.LocalDateTime;
import java.util.AbstractMap;

public class BroadcastTime implements Comparable<BroadcastTime> {
    private AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime> container;

    public BroadcastTime(LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException();
        }

        // Times can't span more than one day
        if (end.getDayOfMonth() != end.getDayOfMonth()) {
            throw new IllegalArgumentException();
        }

        this.container = new AbstractMap.SimpleEntry<>(start, end);
    }

    public LocalDateTime getStart() {
        return container.getKey();
    }

    public LocalDateTime getEnd() {
        return container.getValue();
    }

    // FIXME(borja): Return fine-grained time
    public int getStartNumeric() {
        int minutes = container.getKey().getMinute();
        int hour = container.getKey().getHour();
        if (minutes > 0) {
            ++hour;
        }

        return hour;
    }

    // FIXME(borja): Return fine-grained time
    public int getEndNumeric() {
        int minutes = container.getValue().getMinute();
        int hour = container.getValue().getHour();
        if (minutes > 0) {
            ++hour;
        }

        return hour;
    }

    public int getDayOfWeekNumeric() {
        return container.getKey().getDayOfWeek().getValue();
    }

    @Override
    public int compareTo(BroadcastTime o) {
        int sameStart = getStart().compareTo(o.getStart());
        if (sameStart == 0) {
            return getEnd().compareTo(o.getEnd());
        }

        return sameStart;
    }

    public boolean overlaps(BroadcastTime o) {
        LocalDateTime otherStart = o.getStart();
        LocalDateTime otherEnd = o.getEnd();

        LocalDateTime ourStart = this.getStart();
        LocalDateTime ourEnd = this.getEnd();

        // otherStart \in [ourStart, ourEnd] \/ otherEnd \in [ourStart, ourEnd];
        boolean overlapStart = nonStrictAfter(otherStart, ourStart) && nonStrictBefore(otherStart, ourEnd);
        boolean overlapEnd = nonStrictAfter(otherEnd, ourStart) && nonStrictBefore(otherEnd, ourEnd);

        return overlapStart || overlapEnd;
    }

    private boolean nonStrictAfter(LocalDateTime a, LocalDateTime b) {
        return (a.isAfter(b) || a.isEqual(b));
    }

    private boolean nonStrictBefore(LocalDateTime a, LocalDateTime b) {
        return (a.isBefore(b) || a.isEqual(b));
    }

    public String toString() {
        return "<start=" + getStart().toString() + ", end=" + getEnd().toString() + ">";
    }
}
