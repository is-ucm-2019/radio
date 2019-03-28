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
        return container.getKey().getHour();
    }

    // FIXME(borja): Return fine-grained time
    public int getEndNumeric() {
        return container.getValue().getHour();
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
}
