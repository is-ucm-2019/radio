package radio.ui;

import java.time.LocalDate;

public class PlanningCalendarController implements CalendarController {

    private MainController parentController;

    PlanningCalendarController(MainController cont) {
        this.parentController = cont;
    }

    @Override
    public void getEventsForWeekStartingAt(LocalDate start) {
        this.parentController.core.loadPlanningInfo(start);
    }
}
