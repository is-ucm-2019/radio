package radio.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

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

}
