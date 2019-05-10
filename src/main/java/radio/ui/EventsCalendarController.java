package radio.ui;

import java.time.LocalDate;

public class EventsCalendarController implements ICalendarController {
    private MainController parentController;

    EventsCalendarController(MainController cont) {
        this.parentController = cont;
    }

    // TODO(borja): Implement
    @Override
    public void getEventsForWeekStartingAt(LocalDate start) {
        throw new UnsupportedOperationException();
    }
}
