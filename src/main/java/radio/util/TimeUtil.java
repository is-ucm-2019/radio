package radio.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class TimeUtil {
    public static Locale locale = new Locale("es", "ES");
    private static TemporalField weekFieldISO = WeekFields.of(locale).dayOfWeek();

    private static <T extends Temporal> T firstDayOfWeekFrom(T from, Class<T> type) {
        return type.cast(from.with(weekFieldISO, 1));
    }

    public static LocalDate firstDayOfWeekFrom(LocalDate from) {
        return firstDayOfWeekFrom(from, LocalDate.class);
    }

    public static LocalDateTime firstDayOfWeekFrom(LocalDateTime from) {
        return firstDayOfWeekFrom(from, LocalDateTime.class);
    }

    public static List<LocalDate> getRemainderWeek(LocalDate starting) {
        ArrayList<LocalDate> l = new ArrayList<>();
        LocalDate current = starting.with(weekFieldISO, 7);
        while (!current.isEqual(starting)) {
            l.add(current);
            current = current.minusDays(1);
        }
        l.add(starting);
        Collections.reverse(l);

        return l;
    }

}
