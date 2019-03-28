package radio.ui;

import java.time.LocalDate;

public interface CalendarController {
    void getEventsForWeekStartingAt(LocalDate start);
}
