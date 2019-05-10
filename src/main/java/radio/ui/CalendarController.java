package radio.ui;

import java.time.LocalDate;

interface CalendarController {
    void getEventsForWeekStartingAt(LocalDate start);
}
