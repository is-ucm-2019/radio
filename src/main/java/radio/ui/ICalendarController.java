package radio.ui;

import java.time.LocalDate;

interface ICalendarController {
    void getEventsForWeekStartingAt(LocalDate start);
}
